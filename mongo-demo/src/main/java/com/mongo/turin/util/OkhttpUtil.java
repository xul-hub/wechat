package com.mongo.turin.util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpUtil {

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
//				log.debug("Call:" + call, e);
			}

			public void onResponse(Call call, Response rep) throws IOException {
//				log.debug("Call:" + call + ",Response:" + rep);
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

}
