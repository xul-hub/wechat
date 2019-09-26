package com.mybatis.controler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.mybatis.entity.model.VoiceDTO;

@Controller
@RequestMapping("baidu")
public class BaiduVoiceController {
	
	private static final String appId = "xxxxxx";
	private static final String apiKey = "xxxxxxxxxxxxxx";
	private static final String secretKey = "xxxxxxxxxxxxxxxxxxxxxxx";
	
	@RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
	public String index() {
		return "voice"; 
	}
	
	@RequestMapping(value = "/read", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public VoiceDTO voiceSynthesis(String str,Model model) throws UnsupportedEncodingException {
		
		// 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(appId, apiKey, secretKey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        
        if(str.getBytes().length > 1024) {
        	return new VoiceDTO(null, "fail");
        }
        
        // 设置可选参数
    	HashMap<String, Object> options = new HashMap<String, Object>();
	    	options.put("tex", str);
	        options.put("spd", "5");
	        options.put("pit", "5");
	        options.put("vol", "5");
	        options.put("per", "1");
        
        
//        tex	String	合成的文本，使用UTF-8编码，		
//        请注意文本长度必须小于1024字节					是
//        cuid	String	用户唯一标识，用来区分用户，填写机器 MAC 地址或 IMEI 码，长度为60以内	否
//        spd	String	语速，取值0-9，默认为5中语速		否
//        pit	String	音调，取值0-9，默认为5中语调		否
//        vol	String	音量，取值0-15，默认为5中音量		否
//        per	String	发音人选择, 0为女声，1为男声，
//        3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女	否

        
        TtsResponse res = client.synthesis(str, "zh", 1, options);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, "F:\\tomcat-9.0.7\\webapps\\voice\\" + name + ".mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }
        return new VoiceDTO(name + ".mp3", "success");
	}
}
