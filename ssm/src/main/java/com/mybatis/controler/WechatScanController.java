package com.mybatis.controler;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mybatis.constant.Constants;
import com.mybatis.entity.ShareConfig;
import com.mybatis.utils.JsSignUtil;
import com.mybatis.utils.SignUtil;
import com.mybatis.utils.WechatTools;

@Controller
public class WechatScanController {
	
	@RequestMapping(value = "/index" , method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/index1" , method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model) {
		String url = "http://cc78ef1e.ngrok.io/ssm/index";
		Map<String, String> sign = JsSignUtil.sign(url);
		model.addAttribute("nonceStr", sign.get("nonceStr"));
		model.addAttribute("timestamp", sign.get("timestamp"));
		model.addAttribute("signature", sign.get("signature"));
		return "index";
	}
	
	@RequestMapping(value = "/tape",method = {RequestMethod.GET,RequestMethod.POST})
	public String voice(HttpServletRequest request ,HttpServletResponse response,Model model) {
		try {
			ShareConfig config = new ShareConfig();
			config.setAppId(Constants.APPID);
			config.setUrl("http://302765d1.ngrok.io/ssm/tape");  //”Ú√˚
			config.setNonceStr(UUID.randomUUID().toString().replace("-", "").substring(0, 10)); 
			config.setTimestamp(""+(System.currentTimeMillis() / 1000));
			config.setSignature(SignUtil.signString(WechatTools.getTicket(),config.getUrl(),config.getNonceStr(), config.getTimestamp()));
			
			model.addAttribute("config", config);
			
			return "v";
		} catch (Exception e) {
			return null;
		}
	}
}
