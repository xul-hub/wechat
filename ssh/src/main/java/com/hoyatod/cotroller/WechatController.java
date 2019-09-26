package com.hoyatod.cotroller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoyatod.dao.impl.CommonDAO;
import com.hoyatod.entity.WechatKeyWord;
import com.hoyatod.util.JsonMessage;
import com.hoyatod.util.JsonPage;
import com.hoyatod.wechat.WechatAPIService;
import com.hoyatod.wechat.WechatData;
import com.hoyatod.wechat.WechatUtil;
import com.mysql.jdbc.StringUtils;

@Controller
public class WechatController {
	
	private static Logger log = Logger.getLogger(WechatController.class);
	private final String prefix = "/projects/h5/wechat/";
	
	@Autowired
	private CommonDAO dao;
	
	@RequestMapping(value = "/checkWechatAccess", method = { RequestMethod.GET, RequestMethod.POST })
	public void checkWechatAccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String xml = WechatAPIService.getXmlData(request);         
			String result = "";
		    String echostr = request.getParameter("echostr");  //判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回
		    System.out.println("echostr:"+echostr);
			if (echostr != null && echostr.length() > 1) {
				result = echostr;
			} else {
				System.out.println("********************接入成功******************");
				System.out.println("xm:" + xml);
				WechatData wechatData = WechatAPIService.resolveXmlData(xml);
				if(wechatData != null) {
					String event = wechatData.getEvent();//事件
					String msgType = wechatData.getMsgType();
					if(!StringUtils.isNullOrEmpty(event)) {
						if(event.equals("subscribe")) {//订阅
							String hello ="欢迎加入兴业大家庭！\n[1] 兴业银行大额存单，您资产配置的首选佳品！：<a href='https://mp.weixin.qq.com/s/V4CrQUY62gR9Myny3QZg7A'>—>点击这里</a>\n[2] 兴油卡加油优惠福利不间断：\n<a href='https://mp.weixin.qq.com/s/ur1qsr_Z_COR-SSULPMHXQ'>—>点击这里</a>\n[3] 办理兴业银行信用卡，快人一步！：<a href='https://wm.cib.com.cn/html/webapp/entrance/home.html?id=cd02abdc8a964cc2b587b64087dc5344'>—>点击这里</a>";
							result = WechatUtil.formatXmlAnswer(wechatData.getFromUserName(), wechatData.getToUserName(), hello);
						}else if (event.equals("unsubscribe")) {
							System.out.println("取消关注了..........");
							result = WechatUtil.formatXmlAnswer(wechatData.getFromUserName(), wechatData.getToUserName(), "Good bye!");
						}
					}else if(!StringUtils.isNullOrEmpty(msgType)) {
						if (msgType.equals("text")) { //接受文本
							String content = wechatData.getContent();
							System.out.println("回复文本消息");
							List<WechatKeyWord> list = dao.list("from WechatKeyWord", 0, 0);
							for (WechatKeyWord wechatKeyWord : list) {
								String keyWord = wechatKeyWord.getKeyWord();
								if(keyWord != null && keyWord.equals(content)) {
									result = WechatUtil.formatXmlAnswer(wechatData.getFromUserName(), wechatData.getToUserName(), wechatKeyWord.getContent());
								}
							}
						}else if (msgType.equals("image")) {//接受图片
							System.out.println("回复图片消息");
							result = WechatUtil.formatXmlAnswer(wechatData.getFromUserName(), wechatData.getToUserName(), "图片信息！");
						}else if (msgType.equals("voice")) {//接受语音
							System.out.println("回复语音消息");
							result = WechatUtil.formatXmlAnswer(wechatData.getFromUserName(), wechatData.getToUserName(), "语音消息！");
						}else if (msgType.equals("video")) {//接受视频
							System.out.println("回复视频消息");
							result = WechatUtil.formatXmlAnswer(wechatData.getFromUserName(), wechatData.getToUserName(), "视频消息！");
						}else if (msgType.equals("music")) {//接受音乐
							System.out.println("回复音乐消息");
							result = WechatUtil.formatXmlAnswer(wechatData.getFromUserName(), wechatData.getToUserName(), "音乐消息！");
						}else if (event.equals("news")) {//接受图文
							System.out.println("回复图文消息");
							result = WechatUtil.formatXmlAnswer(wechatData.getFromUserName(), wechatData.getToUserName(), "图文消息！");
						}
					}
				}
			}
			OutputStream os = response.getOutputStream();
			os.write(result.getBytes("UTF-8"));
			os.flush();
			os.close();
		} catch (Exception e) {
			log.error("系统异常", e);
		}
	}
	
	@RequestMapping(value = "/consoleWechatAccess", method = { RequestMethod.GET, RequestMethod.POST })
	public String consoleWechatAccess(HttpServletRequest request, HttpServletResponse response){
		return prefix + "index";
	}
	
	@RequestMapping(value = "/findWechatKeyWordListPage", method = RequestMethod.GET)
	public @ResponseBody JsonMessage getPagedCompany(HttpServletRequest request,HttpServletResponse response) {
		String page_str = request.getParameter("page");
		String rows_str = request.getParameter("rows");
		int page = request.getParameter("page") != null?Integer.parseInt(page_str) : 1;
		int rows = request.getParameter("rows") != null?Integer.parseInt(rows_str) : 20;
		String keyword = request.getParameter("keyword");
		
		int firstResult = JsonPage.getStartOfPage(page, rows);
		String str_sql = "from WechatKeyWord where 1=1 ";
		String str_count = "select count(id) from WechatKeyWord where 1=1 ";
		if(!StringUtils.isNullOrEmpty(keyword)) {
			str_sql+=" and keyword like '" + "%" + keyword + "%' " + " order by id desc";
			str_count+=" and keyword like '" + "%" + keyword + "%' ";
		}
		int count_sum = dao.count(str_count);
		JsonMessage jsonInfo = new JsonMessage();
		if(count_sum<1) {
			JsonPage jsonPage = new JsonPage();
			jsonInfo.setTotal(jsonPage.getTotalCount());
			jsonInfo.setRows(jsonPage.getResult());
			return jsonInfo;
		}
		List<WechatKeyWord> list = dao.list(str_sql, firstResult, rows);
		JsonPage jsonPage = new JsonPage(JsonPage.getStartOfPage(page, rows),count_sum, rows, list);
		jsonInfo.setTotal(jsonPage.getTotalCount());
		jsonInfo.setRows(jsonPage.getResult());
		return jsonInfo;
	}
	
	@RequestMapping(value = "/addKeyWord",method = RequestMethod.POST,produces = "application/json") 
	public @ResponseBody String addKeyWord(String keyWord,String content,HttpServletRequest request, HttpServletResponse response){
		if(StringUtils.isNullOrEmpty(keyWord)) {
			return "1";
		}else if(keyWord.trim().length() > 20) {
			return "2";
		}else if(StringUtils.isNullOrEmpty(content)) {
			return "3";
		}else if(content.trim().length() > 1000) {
			return "4";
		}
		WechatKeyWord wechatKey = new WechatKeyWord();
		wechatKey.setKeyWord(keyWord);
		wechatKey.setContent(content);
		dao.save(wechatKey);
		return "0";
	}
	
	@RequestMapping(value = "/deleteKeyWord/{keyId}",method =RequestMethod.POST,produces = "application/json")
	public @ResponseBody String deleteObj(@PathVariable("keyId") Integer keyId,HttpServletRequest request) {
		if(keyId == null) return "0";
		List<WechatKeyWord> list = dao.list("from WechatKeyWord where id = '" + keyId + "'", 0, 1);
		WechatKeyWord kObj = list == null || list.size() == 0 ? null : list.get(0);
		if(kObj == null) return "1";
		dao.delete(kObj);
		return "2";
	}
	
	@RequestMapping(value = "/updateKeyWord",method =RequestMethod.POST)
	public @ResponseBody String updateKeyWord(Integer keyId,String keyWord,String content,HttpServletRequest request) {
		if(keyId == null) return "0";
		List<WechatKeyWord> list = dao.list("from WechatKeyWord where id = '" + keyId + "'", 0, 1);
		WechatKeyWord kObj = list == null || list.size() == 0 ? null : list.get(0);
		if(kObj == null) return "1";
		
		kObj.setKeyWord(keyWord);
		kObj.setContent(content);
		dao.saveOrUpdate(kObj);
		return "2";
	}
}
































