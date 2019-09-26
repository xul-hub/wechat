package com.mybatis.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * oss文件上传接口
 * 
 * @author xul
 *
 */
public interface ObjStoreService {
	
	/**
	 * TODO 字符字节数组上传
	 * @param bucketName
	 * @param objectName
	 * @param bis
	 * @return Map<String, Object>
	 */
	public Map<String, Object> uploadObjByByte(String bucketName,String objectName,ByteArrayInputStream bis);
	
	/**
	 * TODO 流式上传
	 * @param bucketName
	 * @param objectName
	 * @param is
	 * @return Map<String, Object>
	 */
	public Map<String, Object> uploadObjByInputStream(String bucketName,String objectName,InputStream is);
	
	/**
	 * TODO 文件类型上传 
	 * @param bucketName
	 * @param objectName
	 * @param file
	 * @return Map<String, Object>
	 */
	public Map<String, Object> uploadObjByFile(String bucketName,String objectName,File file);
	
}
