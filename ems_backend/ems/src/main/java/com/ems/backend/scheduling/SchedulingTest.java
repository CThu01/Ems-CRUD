package com.ems.backend.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@EnableAsync
@Slf4j
@Component
public class SchedulingTest {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
//	@Scheduled(fixedRate = 5000 )
//	private void shcedlingTask() {
//		log.info("Schedule Task : " + dateFormat.format(new Date()));
//		try {
//			Thread.sleep(6000);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	@Async
//	@Scheduled(fixedRate = 5000 )
	public void asyncSchedulingTest() {
		log.info("Schedule Task : " + dateFormat.format(new Date()));
		try {
			Thread.sleep(6000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
