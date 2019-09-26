package com.mybatis.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class SignUtil {

	public static String getSign(String noncestrs, String jsapi_tickets, String timestamps, String urls) {
		String[] arr = new String[] { "jsapi_ticket=" + jsapi_tickets, "noncestr=" + noncestrs,"timestamp=" + timestamps, "url=" + urls };
		Arrays.sort(arr);
		String string1 = null;
		for (int i = 0; i < arr.length; i++) {
			if (string1 == null) {
				string1 = arr[i] + "&";
			} else {
				string1 += arr[i] + "&";
			}
		}
		string1 = string1.substring(0, string1.length() - 1);
		// System.out.println(string1);
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(string1.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return tmpStr;
	}

	/**
	 * 将字节数组转换为十六进制字符�?
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符�?
	 * 
	 * @param mByte
	 *            *
	 * @return
	 **/
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}

	public static String signString(String jsapi_ticket, String url, String nonce_str, String timestamp) {
		Map<String, String> ret = sign(jsapi_ticket, url, nonce_str, timestamp);
		return ret.get("signature");
	}

	public static Map<String, String> sign(String jsapi_ticket, String url, String nonce_str, String timestamp) {
		Map<String, String> ret = new HashMap<String, String>();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有�?
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		// System.out.println(string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
