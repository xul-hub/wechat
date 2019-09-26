package com.mybatis.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.mybatis.service.ObjStoreService;

@SuppressWarnings("deprecation")
@Service("objStoreServiceImpl")
@Transactional
public class ObjStoreServiceImpl implements ObjStoreService{
	
	private static String ENDPOINT = null;
	private static String ACCESSKEYID = null;
	private static String ACCESSKEYSECRET = null;
	private static OSSClient ossClient;
	
	static {
		ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
		ACCESSKEYID = "LTAIdHzkQHBdKW8Q";
		ACCESSKEYSECRET = "etNNcmzLW4ECy10isG330qmZVsQYtP";
		ossClient = new OSSClient(ENDPOINT, ACCESSKEYID,ACCESSKEYSECRET);
	}
	
	@Override
	public Map<String, Object> uploadObjByByte(String bucketName, String objectName, ByteArrayInputStream bis) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		try {
			ossClient.putObject(bucketName, objectName, bis);
			ossClient.shutdown();
			map.put("success", "ok");
			return map;
		} catch (OSSException e) {
			e.printStackTrace();
			map.put("error", "fail");
			return map;
		} catch (ClientException e) {
			e.printStackTrace();
			map.put("error", "fail");
			return map;
		}
	}

	@Override
	public Map<String, Object> uploadObjByInputStream(String bucketName, String objectName, InputStream is) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		try {
			ossClient.putObject(bucketName, objectName, is);
			ossClient.shutdown();
			map.put("success", "ok");
			return map;
		} catch (OSSException e) {
			e.printStackTrace();
			map.put("error", "fail");
			return map;
		} catch (ClientException e) {
			e.printStackTrace();
			map.put("error", "fail");
			return map;
		}
	}

	@Override
	public Map<String, Object> uploadObjByFile(String bucketName, String objectName, File file) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		try {
			ossClient.putObject(bucketName, objectName, file);
			ossClient.shutdown();
			map.put("success", "ok");
			return map;
		} catch (OSSException e) {
			e.printStackTrace();
			map.put("error", "fail");
			return map;
		} catch (ClientException e) {
			e.printStackTrace();
			map.put("error", "fail");
			return map;
		}
	}

}
