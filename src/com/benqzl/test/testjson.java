package com.benqzl.test;

//import com.alibaba.fastjson.JSONObject;

public class testjson {
	public static void main(String[] args){
	//	String aa= "{'datatype':'trend','data':[{[{'time':'2016-05-10 10:21:00','value':'353.273438'},{'time':'2016-05-10 10:21:30','value':'353.617188'},{'time':'2016-05-10 10:22:27','value':'354.757813'},{'time':'2016-05-10 10:22:34','value':'353.617188'},{'time':'2016-05-10 10:23:35','value':'353.273438'},{'time':'2016-05-10 10:24:35','value':'353.507813'},{'time':'2016-05-10 10:27:36','value':'353.273438'}]},{[{'time':'2016-05-10 10:21:00','value':'0.178528'},{'time':'2016-05-10 10:25:02','value':'0.178528'}]},{[{'time':'2016-05-10 10:21:00','value':'440.747650'},{'time':'2016-05-10 10:21:03','value':'440.980377'},{'time':'2016-05-10 10:21:05','value':'440.947174'},{'time':'2016-05-10 10:21:08','value':'440.481628'},{'time':'2016-05-10 10:21:10','value':'439.384216'},{'time':'2016-05-10 10:21:12','value':'440.481628'},{'time':'2016-05-10 10:21:15','value':'440.780914'},{'time':'2016-05-10 10:21:18','value':'440.714447'},{'time':'2016-05-10 10:21:25','value':'440.182312'},{'time':'2016-05-10 10:21:27','value':'440.514893'},{'time':'2016-05-10 10:21:30','value':'440.182312'},{'time':'2016-05-10 10:21:32','value':'439.384216'},{'time':'2016-05-10 10:21:35','value':'439.683533'},{'time':'2016-05-10 10:21:38','value':'440.282104'},{'time':'2016-05-10 10:21:41','value':'440.514893'},{'time':'2016-05-10 10:21:44','value':'439.484009'},{'time':'2016-05-10 10:21:47','value':'441.246521'},{'time':'2016-05-10 10:21:49','value':'442.576630'},{'time':'2016-05-10 10:21:52','value':'441.213257'},{'time':'2016-05-10 10:21:55','value':'440.448364'},{'time':'2016-05-10 10:21:57','value':'439.683533'},{'time':'2016-05-10 10:22:00','value':'440.182312'},{'time':'2016-05-10 10:22:03','value':'440.149078'},{'time':'2016-05-10 10:22:06','value':'439.849762'},{'time':'2016-05-10 10:22:09','value':'440.647919'},{'time':'2016-05-10 10:22:11','value':'440.714447'},{'time':'2016-05-10 10:22:14','value':'440.149078'},{'time':'2016-05-10 10:22:16','value':'440.415100'},{'time':'2016-05-10 10:22:19','value':'440.980377'},{'time':'2016-05-10 10:22:21','value':'440.215576'},{'time':'2016-05-10 10:22:23','value':'440.481628'},{'time':'2016-05-10 10:22:26','value':'441.213257'},{'time':'2016-05-10 10:22:28','value':'439.982758'},{'time':'2016-05-10 10:22:31','value':'440.514893'},{'time':'2016-05-10 10:22:34','value':'442.343903'},{'time':'2016-05-10 10:22:37','value':'441.978088'},{'time':'2016-05-10 10:22:42','value':'441.246521'},{'time':'2016-05-10 10:22:50','value':'440.182312'},{'time':'2016-05-10 10:22:52','value':'440.481628'},{'time':'2016-05-10 10:22:55','value':'440.980377'},{'time':'2016-05-10 10:22:57','value':'439.384216'},{'time':'2016-05-10 10:22:59','value':'440.514893'},{'time':'2016-05-10 10:23:02','value':'440.182312'},{'time':'2016-05-10 10:23:04','value':'440.980377'},{'time':'2016-05-10 10:23:08','value':'440.681183'},{'time':'2016-05-10 10:23:11','value':'439.949554'},{'time':'2016-05-10 10:23:13','value':'440.481628'},{'time':'2016-05-10 10:23:16','value':'440.980377'},{'time':'2016-05-10 10:23:22','value':'439.118195'},{'time':'2016-05-10 10:23:24','value':'440.847443'},{'time':'2016-05-10 10:23:26','value':'440.481628'},{'time':'2016-05-10 10:23:29','value':'440.681183'},{'time':'2016-05-10 10:23:32','value':'441.778534'},{'time':'2016-05-10 10:23:35','value':'441.346252'},{'time':'2016-05-10 10:23:38','value':'439.118195'},{'time':'2016-05-10 10:23:40','value':'440.182312'},{'time':'2016-05-10 10:23:43','value':'440.415100'},{'time':'2016-05-10 10:23:48','value':'439.317719'},{'time':'2016-05-10 10:23:50','value':'439.484009'},{'time':'2016-05-10 10:23:54','value':'441.246521'},{'time':'2016-05-10 10:23:59','value':'440.681183'},{'time':'2016-05-10 10:24:02','value':'441.346252'},{'time':'2016-05-10 10:24:04','value':'440.614716'},{'time':'2016-05-10 10:24:09','value':'441.512543'},{'time':'2016-05-10 10:24:12','value':'440.714447'},{'time':'2016-05-10 10:24:15','value':'439.949554'},{'time':'2016-05-10 10:24:18','value':'439.617004'},{'time':'2016-05-10 10:24:20','value':'440.514893'},{'time':'2016-05-10 10:24:23','value':'439.683533'},{'time':'2016-05-10 10:24:28','value':'438.951935'},{'time':'2016-05-10 10:24:30','value':'440.448364'},{'time':'2016-05-10 10:24:33','value':'440.714447'},{'time':'2016-05-10 10:24:35','value':'440.415100'},{'time':'2016-05-10 10:24:38','value':'439.916290'},{'time':'2016-05-10 10:24:40','value':'440.315369'},{'time':'2016-05-10 10:24:43','value':'440.647919'},{'time':'2016-05-10 10:24:46','value':'439.916290'},{'time':'2016-05-10 10:24:49','value':'439.184662'},{'time':'2016-05-10 10:24:54','value':'439.916290'},{'time':'2016-05-10 10:24:57','value':'440.215576'},{'time':'2016-05-10 10:25:00','value':'439.982758'},{'time':'2016-05-10 10:25:03','value':'441.678802'},{'time':'2016-05-10 10:25:10','value':'440.115814'},{'time':'2016-05-10 10:25:12','value':'440.714447'},{'time':'2016-05-10 10:25:14','value':'440.481628'},{'time':'2016-05-10 10:25:18','value':'440.381836'},{'time':'2016-05-10 10:25:20','value':'440.448364'},{'time':'2016-05-10 10:25:22','value':'439.650269'},{'time':'2016-05-10 10:25:25','value':'439.384216'},{'time':'2016-05-10 10:25:27','value':'440.980377'},{'time':'2016-05-10 10:25:29','value':'440.947174'},{'time':'2016-05-10 10:25:31','value':'441.246521'},{'time':'2016-05-10 10:25:34','value':'440.182312'},{'time':'2016-05-10 10:25:36','value':'441.046997'},{'time':'2016-05-10 10:25:39','value':'440.481628'},{'time':'2016-05-10 10:25:41','value':'440.315369'},{'time':'2016-05-10 10:25:43','value':'439.916290'},{'time':'2016-05-10 10:25:48','value':'441.745270'},{'time':'2016-05-10 10:25:51','value':'440.681183'},{'time':'2016-05-10 10:25:54','value':'440.315369'},{'time':'2016-05-10 10:26:02','value':'440.115814'},{'time':'2016-05-10 10:26:05','value':'439.849762'},{'time':'2016-05-10 10:26:08','value':'441.412750'},{'time':'2016-05-10 10:26:10','value':'440.947174'},{'time':'2016-05-10 10:26:13','value':'440.814178'},{'time':'2016-05-10 10:26:15','value':'441.213257'},{'time':'2016-05-10 10:26:18','value':'440.980377'},{'time':'2016-05-10 10:26:20','value':'440.481628'},{'time':'2016-05-10 10:26:22','value':'439.982758'},{'time':'2016-05-10 10:26:25','value':'440.182312'},{'time':'2016-05-10 10:26:29','value':'439.018402'},{'time':'2016-05-10 10:26:34','value':'441.246521'},{'time':'2016-05-10 10:26:36','value':'440.381836'},{'time':'2016-05-10 10:26:39','value':'440.747650'},{'time':'2016-05-10 10:26:41','value':'440.947174'},{'time':'2016-05-10 10:26:43','value':'441.146729'},{'time':'2016-05-10 10:26:46','value':'441.446014'},{'time':'2016-05-10 10:26:49','value':'440.149078'},{'time':'2016-05-10 10:26:51','value':'441.446014'},{'time':'2016-05-10 10:26:54','value':'440.182312'},{'time':'2016-05-10 10:26:57','value':'440.514893'},{'time':'2016-05-10 10:27:00','value':'439.916290'},{'time':'2016-05-10 10:27:02','value':'440.481628'},{'time':'2016-05-10 10:27:05','value':'439.384216'},{'time':'2016-05-10 10:27:08','value':'441.213257'},{'time':'2016-05-10 10:27:10','value':'440.415100'},{'time':'2016-05-10 10:27:12','value':'440.448364'},{'time':'2016-05-10 10:27:15','value':'440.415100'},{'time':'2016-05-10 10:27:17','value':'441.246521'},{'time':'2016-05-10 10:27:19','value':'442.476898'},{'time':'2016-05-10 10:27:22','value':'441.246521'},{'time':'2016-05-10 10:27:25','value':'441.512543'},{'time':'2016-05-10 10:27:27','value':'440.149078'},{'time':'2016-05-10 10:27:30','value':'440.947174'},{'time':'2016-05-10 10:27:33','value':'440.448364'},{'time':'2016-05-10 10:27:36','value':'440.348572'},{'time':'2016-05-10 10:27:39','value':'439.783295'},{'time':'2016-05-10 10:27:42','value':'440.315369'},{'time':'2016-05-10 10:27:45','value':'439.883026'},{'time':'2016-05-10 10:27:47','value':'440.115814'},{'time':'2016-05-10 10:27:49','value':'439.018402'},{'time':'2016-05-10 10:27:53','value':'440.647919'},{'time':'2016-05-10 10:27:56','value':'440.614716'},{'time':'2016-05-10 10:27:58','value':'440.448364'},{'time':'2016-05-10 10:28:01','value':'439.916290'},{'time':'2016-05-10 10:28:03','value':'441.279724'},{'time':'2016-05-10 10:28:06','value':'439.949554'},{'time':'2016-05-10 10:28:14','value':'440.481628'},{'time':'2016-05-10 10:28:17','value':'439.982758'},{'time':'2016-05-10 10:28:19','value':'440.115814'},{'time':'2016-05-10 10:28:24','value':'440.714447'}]}]}";
		//JSONObject obj = JSONObject.parseObject(aa);
	}
}
