package com.benqzl.socket;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Crypt {
	int key = 9000;

	public int getKey() {
		return key;
	}
	
	public Crypt(){
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		this.key = Integer.parseInt(sdf.format(dt));
		System.out.println(key);
	}

	public int generationKey(int oldKey) {
		while (key < 100000 && key != oldKey) {
			key = (int) (Math.random() * 1000000);
			System.out.println(key);
		}
		return key;
	}

	String encrypt(String str) {
		byte[] buff = new byte[0];
		try {
			buff = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] buffer = new byte[buff.length];
		int i = 0;
		for (byte b : buff) {
			buffer[i] = (byte) (b ^ key);
			i++;
		}
		str = byteToHexString(buffer);
		return str;
	}

	String decrypt(String str) {
		byte[] buff = hexStringToByte(str);
		byte[] buffer = new byte[buff.length];
		int i = 0;
		for (byte b : buff) {
			buffer[i] = (byte) (b ^ key);
			i++;
		}
		try {
			str = new String(buffer,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	private String byteToHexString(byte[] buff) {
		String hex = "", str = "";
		for (int i = 0; i < buff.length; i++) {
			hex = Integer.toHexString(buff[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			str += hex.toUpperCase();
		}
		return str;
	}

	private byte[] hexStringToByte(String str) {
		int len = str.length() / 2;
		byte[] buff = new byte[len];
		char[] hexChars = str.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			buff[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return buff;
	}

	private byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		Crypt crypt = new Crypt();
		String msg = "8F5ECFCBEA3BEA8AEACB7A9ACAEAFA8AEA8A1AEACBEE4B5E2BEF2FCE0BEA3BEADAFAFAFADAFA1A0B4F8ACACA9B4ADFCFCA9B4F8FBAFAAB4A9ACACA1AEFFA9FCACAAA1FCBEB5BEEDF0F4FCBEA3BEABA9A8AFB4A9A0B4ABA8B9A9ABA3ADA1A3ADADBEB5B";
		//msg = crypt.encrypt(msg);
		//System.out.println(msg);
		msg = crypt.decrypt(msg);
		System.out.println(msg);
		/*buff = msg.getBytes("UTF-8");
		buff = crypt.decrypt(buff);
		msg = new String(buff, "UTF-8");
		System.out.println(msg);
		crypt.generationKey(0);*/
		System.out.println(UUID.randomUUID().toString().toUpperCase());
		System.out.println(UUID.randomUUID().toString().length());
	}
}
