package com.bigdata2017.smartcar;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenDrivingLogThread extends Thread{

	private String toDay;

	Logger logger = LogManager.getLogger(this.getName());

	CarDriving carDriving = new CarDriving();

	public GenDrivingLogThread( String carNum, String toDay, boolean isAdd ) {
		this.toDay = toDay;
		carDriving.setCarNum(carNum);
		carDriving.setIsAdd(isAdd);
		carDriving.setAreaTypeNum(randomRange(0,5));

		System.setProperty("logFilename", "./logs/driving/driving.log");

		org.apache.logging.log4j.core.LoggerContext ctx = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		ctx.reconfigure();
	}
	

	@Override    
	public void run() {

		int count = 86400;
		for(int i=0; i <= count ; i++) {
			if(i==0) {
				logger.info("Driver Status Infomation"
						+ ",CarNum"
						
						+ ",AccStep"
						+ ",BrkStep"
						+ ",WheelStep"
						
						+ ",DirLightStep"
						+ ",Speed"
						+ ",AreaNum");
				
			}
			
			logger.info(toDay + getSecToTime(i) 
					+ "," + carDriving.getCarNum() 
					
					+ "," + carDriving.getAccStep()
					+ "," + carDriving.getBrkStep()
					+ "," + carDriving.getWheelStep()
					
					+ "," + carDriving.getDirLightStep()
					+ "," + carDriving.getSpeed() 
					+ "," + carDriving.getAreaNum());
			
			try {
				sleep(1 * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			i+=1;
		}
	}

	public String getSecToTime(int inSec) {

		String time = String.valueOf(inSec/3600);
		if(time.length() == 1) time = "0" + time;
		String min = String.valueOf(inSec%3600/60);
		if(min.length() == 1) min = "0" + min;
		String sec = String.valueOf(inSec%3600%60%60);
		if(sec.length() == 1) sec = "0" + sec;

		return time+min+sec;

	}
	
	public int randomRange(int n1, int n2) {  
		return  (int)(Math.random() * (n2 - n1 + 1)) + n1;
	} 
	
	public static String getToDate() {

		long todaytime;
		SimpleDateFormat day;
		String toDay;

		todaytime = System.currentTimeMillis(); 
		day = new SimpleDateFormat("yyyyMMdd");

		toDay =  day.format(new Date(todaytime));

		return toDay;

	}


}
