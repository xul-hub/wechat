package com.mybatis.bdtj.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
* @ClassName: BdtjAPIUtil.java
* @Description: 百度统计APi数据接口
*
* @version: v1.0.0
* @author: xul
* @date: 2019年1月16日 上午10:22:38 
*/
public class BdtjAPIUtil {
	
	/**
	 * 
	* @Description: 獲取站點詳細數據 调用次数一周 50000次 建议设置定时器
	*
	* @param: 站點id 查詢時間段
	* @return：FlowDTO對象
	*
	* @version: v1.0.0
	* @author: xul
	* @date: 2019年1月16日 下午4:53:12
	 */
	public static FlowDTO getData (String siteId,String start_date,String end_date) {
		
			JSONObject header = new JSONObject();
			header.put("username",Constant.USERNAME);        				
			header.put("password",Constant.PASSWORD);		
			header.put("token",Constant.TOKEN); 
			header.put("account_type",Constant.ACCOUNT_TYPE);
			
			JSONObject body = new JSONObject();
			body.put("siteId",siteId);        				
			body.put("method",Constant.METHOD);  				
			body.put("start_date",start_date); 
			body.put("end_date",end_date);
			body.put("metrics",Constant.METRICS);
			
			JSONObject parms = new JSONObject();
			parms.put("header", header);
			parms.put("body", body);
			
			String datas = OkhttpUtil.doPostHttpRequest(Constant.DATA, parms.toString());
			com.alibaba.fastjson.JSONObject parseObject = JSON.parseObject(datas);
			
			com.alibaba.fastjson.JSONObject bodys = parseObject.getJSONObject("body");
			JSONArray jsonArrayDatas = bodys.getJSONArray("data");
			
			if(jsonArrayDatas == null) {
				return new FlowDTO(-1);
			}       
			
			com.alibaba.fastjson.JSONObject jsonData = com.alibaba.fastjson.JSONObject.parseObject(jsonArrayDatas.get(0).toString());
			com.alibaba.fastjson.JSONObject jsonResult = jsonData.getJSONObject("result");
			
			JSONArray p = jsonResult.getJSONArray("pageSum").getJSONArray(0);
			
			FlowDTO flowDTO = new FlowDTO(0);
			
			if(p.getString(0).equals("--")) flowDTO.setPv(0);
			else flowDTO.setPv((Integer)p.get(0));
			
			if(p.getString(0).equals("--")) flowDTO.setPu(0);
			else flowDTO.setPu((Integer)p.get(1));
			
			if(p.getString(0).equals("--")) flowDTO.setIp(0);
			else flowDTO.setIp((Integer)p.get(2));
			
			if(p.getString(0).equals("--")) flowDTO.setBounce_ratio(0);
			else flowDTO.setBounce_ratio((Integer)p.get(3));
			
			if(p.getString(0).equals("--")) flowDTO.setAvg_visit_time(0);
			else flowDTO.setAvg_visit_time((Integer)p.get(4));
			
			if(p.getString(0).equals("--")) flowDTO.setAvg_visit_pages(0);
			else flowDTO.setAvg_visit_pages((Integer)p.get(5));
			
			return flowDTO;
	}
	
	public static void main(String[] args) {
//		System.out.println(getData("xxxxxxxxxx","20191001","20191007"));
	}
	
	/**
	 * 
	* @Description: 獲取站點列表
	*
	* @return：List 或者為 null 站点ID 列表
	*
	* @version: v1.0.0
	* @author: xul
	* @date: 2019年1月16日 下午4:51:51
	 */
	public static List<SiteListDTO> getSiteList () {
		
		JSONObject header = new JSONObject();
        header.put("username",Constant.USERNAME);        				
        header.put("password",Constant.PASSWORD);  				
        header.put("token",Constant.TOKEN);
        header.put("account_type",Constant.ACCOUNT_TYPE);
        JSONObject parms = new JSONObject();
        parms.put("header", header);
        
        String siteListData = OkhttpUtil.doPostHttpRequest(Constant.SITELIST, parms.toString());
        com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(siteListData);
        com.alibaba.fastjson.JSONObject body = jsonObj.getJSONObject("body");
        JSONArray jsonBody = body.getJSONArray("data");
        if(jsonBody == null) return null;
        
        com.alibaba.fastjson.JSONObject siteList = com.alibaba.fastjson.JSONObject.parseObject(jsonBody.get(0).toString());
        if(siteList == null) return null;
        JSONArray parseArray = JSONArray.parseArray(siteList.get("list").toString());
        if(parseArray == null) return null;
        
		List<SiteListDTO> lists = new ArrayList<SiteListDTO>();
		
		for (int i = 0; i < parseArray.size(); i++) {
			SiteListDTO siteLists = new SiteListDTO();
			com.alibaba.fastjson.JSONObject parseObject = JSON.parseObject(parseArray.get(i).toString());
			siteLists.setCreate_time((String) parseObject.get("create_time"));
			siteLists.setDomain((String) parseObject.get("domain"));
			siteLists.setSite_id((int) parseObject.get("site_id"));
			siteLists.setStatus((int) parseObject.get("status"));
			lists.add(siteLists);
		}
		return lists;
	}
}
