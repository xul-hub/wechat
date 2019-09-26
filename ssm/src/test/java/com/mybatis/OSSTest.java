package com.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aliyun.oss.OSSClient;
import com.hoyatod.wechatclient.interfaces.SaasWechatGoodsInterface;
import com.hoyatod.wechatclient.protocol.wechat.SearchWechatGoodsRespData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring-mvc.xml" })
public class OSSTest {

	private static String ENDPOINT = null;
	private static String ACCESSKEYID = null;
	private static String ACCESSKEYSECRET = null;
	
	static {
		ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
		ACCESSKEYID = "LTAIdHzkQHBdKW8Q";
		ACCESSKEYSECRET = "etNNcmzLW4ECy10isG330qmZVsQYtP";
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
//		String endpoint = "http://oss-cn-beijing.aliyuncs.com";
		// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//		String accessKeyId = "LTAIdHzkQHBdKW8Q";
//		String accessKeySecret = "etNNcmzLW4ECy10isG330qmZVsQYtP";

		// 创建OSSClient实例。
		OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);

		// 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
		ossClient.putObject("xuliang20180420", "voice/test.mp3", new File("F:\\tomcat-9.0.7\\webapps\\voice\\test.mp3"));

		// 关闭OSSClient。
		ossClient.shutdown();
		System.out.println("success");
	}
	
}
















