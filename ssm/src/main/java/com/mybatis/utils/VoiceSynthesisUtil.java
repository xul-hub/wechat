package com.mybatis.utils;

import java.util.HashMap;
import java.util.Map;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;

public class VoiceSynthesisUtil {
	
	private static String appId = null;
	private static String apiKey = null;
	private static String secretKey = null;
	
	//个人账号
	static {
		appId = "9478315";
		apiKey = "QoEGWdSA3NrBBGMntEaKGvev";
		secretKey = "qDgsC41zHeo8sUGpb0GAGMFsaVuhWHIC";
	}
	
	/**
	 * @param textContent 文本内容
	 * @return Map<String, Object>
	 */
	public Map<String, Object> VoiceSynthesis(String textContent){
		
		Map<String, Object> map = new HashMap<String, Object>();
	    try {
			AipSpeech client = new AipSpeech(appId, apiKey, secretKey);
			/**
			 * 可选：设置网络连接参数
			 */
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);
			
			if(textContent.getBytes().length > 1024) {
				map.put("errmsg", -1); //文本过长
				return map;
			}
			
			// 设置可选参数
			HashMap<String, Object> options = new HashMap<String, Object>();
				options.put("tex", textContent);
			    options.put("spd", "5");
			    options.put("pit", "5");
			    options.put("vol", "5");
			    options.put("per", "1");//男声为1 女声为0
			TtsResponse res = client.synthesis(textContent, "zh", 1, options);
			byte[] data = res.getData();
			map.put("data", data); //音频数据 Byte[]数组
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errmsg", 9); //系统异常
			return map;
		}
	}
	
	
}
