package com.mybatis.bdtj.login.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang.ArrayUtils;

import com.mybatis.bdtj.login.entity.AppConstans;

public class HttpClientUtils {
	
	

	/**
	 * 仅用于登陆的http post请求，登陆返回结果需要解压，然后才可以返回String或�?�obj
	 * @param url
	 * @param bytes
	 * @param timeout
	 * @param encode
	 * @return
	 * @author shy
	 * @date 2016-11-15 下午01:26:48
	 */
	public static String doHttpPost(String url, byte[] bytes, int timeout, String encode){
		HttpClient httpClient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager());
		httpClient.getParams().setContentCharset(encode);
		PostMethod postMethod = new PostMethod(url);
		
		
		InputStream inputStream = new ByteArrayInputStream(bytes, 0, bytes.length);
		RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, bytes.length, "text/json; charset=utf-8");
		postMethod.setRequestEntity(requestEntity);
        postMethod.addRequestHeader("Content-Type", "text/json; charset=utf-8");
 		postMethod.addRequestHeader("uuid", AppConstans.UUID);
 		postMethod.addRequestHeader("account_type", "1");		//默认是百度账�?

		try {
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			
			int num = httpClient.executeMethod(postMethod);
			//System.out.println(num);
			if (num == AppConstans.SC_OK) {
				//System.out.println(postMethod.getResponseBodyAsString());
				InputStream in = postMethod.getResponseBodyAsStream();
		        try {
		            byte[] b = new byte[8];
		            if (in.read(b) != 8) {
		                throw new ClientInternalException("Server response is invalid.");
		            }
		            if (b[1] != 0) {
		                throw new ClientInternalException("Server returned an error code: " + b[0]+b[1]+b[2]+b[3]+b[4]+b[5]+b[6]+b[7]);
		            }
		            int total = 0, k = 0;
		            b = new byte[AppConstans.MAX_MSG_SIZE];
		            while (total < AppConstans.MAX_MSG_SIZE) {
		                k = in.read(b, total, AppConstans.MAX_MSG_SIZE - total);
		                if (k < 0)
		                    break;
		                total += k;
		            }
		            if (total == AppConstans.MAX_MSG_SIZE) {
		                throw new ClientInternalException("Server returned message too large.");
		            }
		            byte[] zip = ArrayUtils.subarray(b, 0, total);
		            zip = GZipUtil.unGzip(zip);
		            //System.out.println(JacksonUtil.str2Obj(new String(zip, "UTF-8"), DoLoginResponse.class));
		            //return JacksonUtil.str2Obj(new String(zip, "UTF-8"), DoLoginResponse.class);
		            return new String(zip, "UTF-8");
		        } catch (IOException e) {
		            throw new ClientInternalException(e);
		        } finally {
		            if (in != null) {
		                try {
		                    in.close();
		                } catch (IOException e) {
		                    throw new ClientInternalException(e);
		                }
		            }
		        }
			}
		} catch (Exception e) {
			//logger.error(e.getMessage(), e);
		} finally {
			postMethod.releaseConnection();
		}
		return "";
	}
	
	
	
}
