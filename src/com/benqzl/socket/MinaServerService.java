package com.benqzl.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.benqzl.pojo.manage.Content;
import com.benqzl.pojo.manage.OAContent;
import com.benqzl.pojo.manage.Watch;
import com.benqzl.pojo.production.Aqyxts;
import com.benqzl.pojo.production.Defect;
import com.benqzl.pojo.production.DefectItems;
import com.benqzl.pojo.production.Report;
import com.benqzl.pojo.production.UnitHisTimeDate;
//import com.benqzl.pojo.system.Systimedate;
//import com.benqzl.pojo.system.Unittimedate;
import com.benqzl.service.manage.ContentService;
import com.benqzl.service.manage.OAContentService;
import com.benqzl.service.manage.WatchService;
import com.benqzl.service.production.AqyxtsService;
import com.benqzl.service.production.DefectItemsService;
import com.benqzl.service.production.DefectService;
import com.benqzl.service.production.ReportService;
import com.benqzl.service.production.UnitHisTimeDateService;
import com.benqzl.service.system.TimedateService;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

@Service("minaServerService")
@Scope("singleton")
public class MinaServerService {
	private Logger logger = Logger.getLogger(MinaServerService.class);

	static boolean flag = true;
	@Resource
	MessageQueue messageQueue;
	@Resource
	UnitHisTimeDateService unitHisTimeDateService;
	@Resource
	TimedateService timedateService;
	IoAcceptor acceptor;

	/*
	 * 导入新闻service
	 */
	@Autowired
	private ContentService contentService;

	/**
	 * 导入值班表
	 */
	@Autowired
	private WatchService watchService;

	@Autowired
	private AqyxtsService aqyxtsService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private OAContentService oaContentService;

	@Autowired
	private DefectService defectService;

	@Autowired
	private DefectItemsService defectItemsService;

	Thread htThread;
	Thread sqThread;

	@Value("${mina.ip}")
	private String ip;

	@Value("${mina.port}")
	private String port;

	@Value("${mina.bufferSize}")
	private String bufferSize;

	@Value("${mina.decoderMaxLineLength}")
	private String decoderMaxLineLength;

	@Value("${mina.encoderMaxLineLength}")
	private String encoderMaxLineLength;

	@Value("${mina.readIDETime}")
	private String readIDETime;

	@PostConstruct
	private void init() {
		acceptor = new NioSocketAcceptor();
		acceptor.getSessionConfig().setReadBufferSize(
				Integer.parseInt(bufferSize));
		acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE,
				Integer.parseInt(readIDETime));
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		TextLineCodecFactory factory = new TextLineCodecFactory(
				Charset.forName("UTF-8"),
				"62E7E189-3100-4910-B515-191A086C4712",
				"62E7E189-3100-4910-B515-191A086C4712");
		factory.setDecoderMaxLineLength(Integer.parseInt(decoderMaxLineLength));
		factory.setEncoderMaxLineLength(Integer.parseInt(encoderMaxLineLength));
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(factory));

		acceptor.setHandler(new MyHandler());

		try {
			/*
			 * acceptor.bind(new InetSocketAddress(ip,Integer.parseInt(port)));
			 */
			acceptor.bind(new InetSocketAddress(Integer.parseInt(port)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		htThread = new Thread(new MyRunnable());
		sqThread = new Thread(new SQLRunnable());
		htThread.start();
		sqThread.start();
	}

	@PreDestroy
	public void dispose() {
		flag = false;
		htThread.interrupt();
		sqThread.interrupt();
		acceptor.unbind();
		acceptor.getFilterChain().clear();
		acceptor.dispose();
		acceptor = null;
	}

	/**
	 * @author lyf 扫数据库
	 */
	class SQLRunnable implements Runnable {
		public void run() {
			while (flag) {
				logger.info("---------------------------------------" + flag);
				sqlRun();
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void sqlRun() {

	}

	/**
	 * @author lyf 发送心跳包
	 */
	class MyRunnable implements Runnable {

		@Override
		public void run() {
			while (flag) {
				try {
					Crypt crypt = new Crypt();
					int i = 0;
					int t = acceptor.getManagedSessions().values().size();
					for (IoSession session : acceptor.getManagedSessions()
							.values()) {
						if (messageQueue.ischange()) {
							String keys = messageQueue.readParameterKeys();
							String ids = messageQueue.readParameterIds();

							if (null != keys && !keys.isEmpty()) {
								String msg = ids + "|" + keys;
								int msgLen = msg.length();
								int msgTim = msgLen / 10000;
								int lastLen = msgLen % 10000;
								if (lastLen > 0) {
									msgTim++;
								}

								for (int n = 0; n < msgTim; n++) {
									if (n == 0) {
										session.write(crypt
												.encrypt("3235B1DE-1751-45E4-9CA1-BA59D9AFE1A4"
														+ msg.substring(0,
																10000)));
									} else if (n == (msgTim - 1)) {
										session.write(crypt
												.encrypt("7C5E0CEF-D6F7-11E6-81C9-00163E107EDC"
														+ msg.substring(
																10000 * n,
																10000*n+lastLen)));
									} else {
										session.write(crypt.encrypt("BB7F2B90-D6F7-11E6-81C9-00163E107EDC"
												+ msg.substring(10000 * n,
														10000 * (n + 1))));
									}
								}

								if (i == t) {
									messageQueue.clearParameter();
								}
							} else {
								session.write(crypt.encrypt("AAFF"));
							}
						} else {
							session.write(crypt.encrypt("AAFF"));
							continue;
						}
						i++;
					}
					messageQueue.clearParameter();
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 关键代码 执行序列化和反序列化 进行深度拷贝
	public <T> List<T> deepCopy(List<T> src) throws IOException,
			ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(
				byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();
		return dest;
	}

	class MyHandler extends IoHandlerAdapter {

		@Override
		public void exceptionCaught(IoSession session, Throwable cause)
				throws Exception {
			if (session != null) {
				session.close(true);
			}
		}

		@Override
		public void messageReceived(IoSession session, Object message)
				throws Exception {
			String msg = (String) message;
			logger.info(msg);
			Crypt crypt = new Crypt();
			// crypt.setKey(Integer.parseInt(guid));
			msg = crypt.decrypt(msg);
			logger.info(msg);
			if (msg.equals("AAFF")) {
				return;
			}
			JSONObject obj = JSONObject.parseObject(msg);
			String datatype = (String) obj.get("datatype");
			// obj = obj.getJSONObject("data");
			JSONArray arr = obj.getJSONArray("data");
			obj = null;
			String idstr = messageQueue.readParameterIds();
			// String[] ids = idstr.split(",");
			switch (datatype) {
			case "current":// 实时数据
				List<UnitHisTimeDate> hisTimeDates = new ArrayList<>();
				for (int i = 0; i < arr.size(); i++) {
					obj = arr.getJSONObject(i);
					messageQueue.currentData.put(obj.getString("key"),
							obj.getString("value"));
					UnitHisTimeDate hisTimeDate = new UnitHisTimeDate();
					hisTimeDate.setId(UUID.randomUUID().toString());
					hisTimeDate.setUnitid(obj.getString("key"));
					hisTimeDate.setPvalue(Float.parseFloat(obj
							.getString("value")));
					hisTimeDate.setCdate(new Date());
					hisTimeDates.add(hisTimeDate);
				}
				try {
					unitHisTimeDateService.insert(hisTimeDates);
				} catch (Exception e) {
					e.printStackTrace();
					logger.info(e.getMessage());
				}
				break;
			case "cpNews":
				List<Content> cpcontents = new ArrayList<>();
				for (int j = 0; j < arr.size(); j++) {
					JSONObject o = arr.getJSONObject(j);
					Content content = new Content();
					content.setId(o.getString("id"));
					content.setSort(o.getString("sort"));
					content.setColor(o.getString("color"));
					content.setContent(o.getString("content"));
					content.setCtop(o.getInteger("ctop"));
					content.setDing(o.getInteger("ding"));
					content.setName(o.getString("name"));
					content.setTitle1(o.getString("title1"));
					content.setTitle2(o.getString("title2"));
					content.setViewnum(o.getInteger("viewnum"));
					content.setCdate(o.getDate("cdate"));
					cpcontents.add(content);
				}
				contentService.insert(cpcontents);
				break;
			case "msgs":
				List<Content> msgcontents = new ArrayList<>();
				for (int j = 0; j < arr.size(); j++) {
					JSONObject o = arr.getJSONObject(j);
					Content content = new Content();
					content.setId(o.getString("id"));
					content.setSort(o.getString("sort"));
					content.setColor(o.getString("color"));
					content.setContent(o.getString("content"));
					content.setCtop(o.getInteger("ctop"));
					content.setDing(o.getInteger("ding"));
					content.setName(o.getString("name"));
					content.setTitle1(o.getString("title1"));
					content.setTitle2(o.getString("title2"));
					content.setViewnum(o.getInteger("viewnum"));
					content.setCdate(o.getDate("cdate"));
					msgcontents.add(content);
				}
				contentService.insert(msgcontents);
				break;
			case "imNews":
				List<Content> imcontents = new ArrayList<>();
				for (int j = 0; j < arr.size(); j++) {
					JSONObject o = arr.getJSONObject(j);
					Content content = new Content();
					content.setId(o.getString("id"));
					content.setSort(o.getString("sort"));
					content.setColor(o.getString("color"));
					content.setContent(o.getString("content"));
					content.setCtop(o.getInteger("ctop"));
					content.setDing(o.getInteger("ding"));
					content.setName(o.getString("name"));
					content.setTitle1(o.getString("title1"));
					content.setTitle2(o.getString("title2"));
					content.setViewnum(o.getInteger("viewnum"));
					content.setCdate(o.getDate("cdate"));
					imcontents.add(content);
				}
				contentService.insert(imcontents);
				break;
			case "zbbs":
				List<Watch> watchs = new ArrayList<>();
				for (int j = 0; j < arr.size(); j++) {
					JSONObject o = arr.getJSONObject(j);
					Watch w = new Watch();
					w.setId(o.getString("id"));
					w.setBm(o.getString("bm"));
					w.setRenyuan(o.getString("renyuan"));
					w.setRiqi(o.getDate("riqi"));
					w.setShijian(o.getDate("shijian"));
					w.setSort(o.getString("sort"));
					watchs.add(w);
				}
				watchService.insert(watchs);
				break;
			case "aqs":
				List<Aqyxts> aqyxtss = new ArrayList<>();
				for (int j = 0; j < arr.size(); j++) {
					JSONObject o = arr.getJSONObject(j);
					Aqyxts a = new Aqyxts();
					a.setRj1aqqsr(o.getDate("rj1aqqsr"));
					a.setRj2aqqsr(o.getDate("rj2aqqsr"));
					a.setRj3aqqsr(o.getDate("rj3aqqsr"));
					a.setRj4aqqsr(o.getDate("rj4aqqsr"));
					a.setRr5aqqsr(o.getDate("rr5aqqsr"));
					a.setRr6aqqsr(o.getDate("rr7aqqsr"));
					a.setJnaqqsr(o.getDate("jnaqqsr"));
					aqyxtss.add(a);
				}
				aqyxtsService.update(aqyxtss.get(0));
				break;
			case "reports":
				for (int j = 0; j < arr.size(); j++) {
					JSONObject o = arr.getJSONObject(j);
					Report report = new Report();
					report.setId(UUID.randomUUID().toString());
					report.setFdlrj1(o.getDouble("fdlrj1"));
					report.setFdlrj2(o.getDouble("fdlrj2"));
					report.setFdlrj3(o.getDouble("fdlrj3"));
					report.setFdlrj4(o.getDouble("fdlrj4"));
					report.setFdlrj5(o.getDouble("fdlrj5"));
					report.setFdlrj6(o.getDouble("fdlrj6"));

					report.setEswdl(o.getDouble("eswdl"));
					report.setFswdl(o.getDouble("fswdl"));
					report.setFswdl34(o.getDouble("fswdl34"));

					report.setXwdl(o.getDouble("xwdl"));
					report.setXwdl34(o.getDouble("xwdl34"));
					report.setXwdlrr(o.getDouble("xwdlrr"));

					report.setFhql24(o.getDouble("fhql24"));
					report.setFhql3424(o.getDouble("fhql3424"));
					report.setEhql24(o.getDouble("ehql24"));

					report.setEsrl(o.getDouble("esrl"));
					report.setGrlrr(o.getDouble("grlrr"));
					report.setRiqi(o.getDate("riqi"));

					reportService.insertSelective(report);
				}
				break;
			case "oacontents":
				List<OAContent> oaContents = new ArrayList<>();
				for (int j = 0; j < arr.size(); j++) {
					JSONObject o = arr.getJSONObject(j);
					OAContent oa = new OAContent();
					oa.setId(o.getString("id"));
					oa.setContent(o.getString("content"));
					oa.setFbdw(o.getString("fbdw"));
					oa.setFbri(o.getDate("fbri"));
					oa.setName(o.getString("name"));
					oa.setReads(o.getString("reads"));
					oa.setTitle(o.getString("title"));
					oa.setYxri(o.getDate("yxri"));
					oaContents.add(oa);
				}
				// logger.info(idstr);
				oaContentService.insert(oaContents);
				break;
			case "defects":
				List<Defect> defects = new ArrayList<>();
				for (int j = 0; j < arr.size(); j++) {
					JSONObject o = arr.getJSONObject(j);
					Defect defect = new Defect();
					defect.setId(o.getInteger("id"));
					defect.setStarttime(o.getDate("starttime"));
					defect.setEndtime(o.getDate("endtime"));
					defect.setBh(o.getString("bh"));
					defect.setFenlei(o.getString("fenlei"));
					defect.setMemo(o.getString("memo"));
					defect.setState(o.getString("state"));
					defect.setTitle(o.getString("title"));
					defect.setUnit(o.getString("unit"));
					defect.setZrbm(o.getString("zrbm"));
					defects.add(defect);
				}
				logger.info(idstr);
				defectService.insert(defects);
				defectItemsService.deleteAll();
				break;
			case "defectItems":
				List<DefectItems> defectItems = new ArrayList<>();
				for (int j = 0; j < arr.size(); j++) {
					JSONObject o = arr.getJSONObject(j);
					DefectItems items = new DefectItems();
					items.setId(o.getInteger("id"));
					items.setParentid(o.getInteger("parentid"));
					items.setOp(o.getString("op"));
					items.setUsername(o.getString("username"));
					items.setRectime(o.getDate("rectime"));
					items.setIp(o.getString("ip"));
					items.setBm(o.getString("bm"));
					items.setMemo(o.getString("memo"));
					defectItems.add(items);
				}
				logger.info(idstr);
				defectItemsService.insert(defectItems);
				break;

			}

		}

		@Override
		public void sessionIdle(IoSession session, IdleStatus status)
				throws Exception {
			if (session != null) {
				session.close(true);
			}
		}

	}
}
