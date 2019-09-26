package com.mybatis.bdtj.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
* @ClassName: BdtjTimeTask.java
* @Description: 百度统计获取站点列表
*
* @version: v1.0.0
* @author: xul
* @date: 2019年1月17日 上午11:36:44 
*/
@Component
public class BdtjTimeTask {
		
	/*@Scheduled(cron="0/40 * * * * ?")   //每10秒执行一次   
	public void BdtjTask() {
		System.out.println("start..........");
		System.out.println("task--time---" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}*/
}
