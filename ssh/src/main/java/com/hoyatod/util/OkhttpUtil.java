package com.hoyatod.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpUtil {

	private static final Logger log = Logger.getLogger(OkhttpUtil.class);
	private static final OkHttpClient httpClient = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=UTF-8");
	public static final MediaType MediaTypeFlie = MediaType.parse("application/json; charset=UTF-8");

	/**
	 * 不会开启异步线程。
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Response execute(Request request) throws IOException {
		return httpClient.newCall(request).execute();
	}

	/**
	 * 开启异步线程访问网络
	 * 
	 * @param request
	 * @param responseCallback
	 */
	public static void enqueue(Request request, Callback responseCallback) {
		httpClient.newCall(request).enqueue(responseCallback);
	}

	/**
	 * 开启异步线程访问网络, 且不在意返回结果(实现空callback)
	 * 
	 * @param request
	 */
	public static void enqueue(Request request) {
		httpClient.newCall(request).enqueue(new Callback() {
			public void onFailure(Call call, IOException e) {
				log.debug("Call:" + call, e);
			}

			public void onResponse(Call call, Response rep) throws IOException {
				log.debug("Call:" + call + ",Response:" + rep);
			}
		});
	}

	public static String doGetHttpRequest(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = execute(request);
		if (!response.isSuccessful()) {
			throw new IOException("Server Connection Failed:" + response);
		}
		return response.body().string();
	}

	public static String doPostHttpRequest(String url, String json) {
		Request request = new Request.Builder().url(url).post(RequestBody.create(JSON, json)).build();
		try {
			Response response = execute(request);
			if (!response.isSuccessful()) {
				System.out.println("Server Connection Failed:" + response);
			}
			return response.body().string();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 文件上传 OKHttpUtil
	 * 
	 * @return String
	 * 
	 */
	public static String upLoad(String url,String fileName, String path) {
		
		File file = new File(path, fileName);
		RequestBody fileBody = RequestBody.create(MediaTypeFlie, file);
		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("image", fileName, fileBody).build();
		Request request = new Request.Builder().url(url).post(requestBody).build();
		final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
		OkHttpClient okHttpClient = httpBuilder.connectTimeout(10, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).build();// 设置超时
		okHttpClient.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onFailure(Call call, IOException e) {
				log.error("uploadMultiFile() e=" + e);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				log.error("uploadMultiFile() response=" + response.body().string());
			}
			
		});
		return "success";
	}

	/**
	 * 文件下载 OKHttpUtil
	 * 
	 * @return String
	 */
	public static String downLoad() {
		return null;
	}
}
