package com.bigdata2017.smartcar;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenStatusLogThread extends Thread{

	Logger logger = LogManager.getLogger(this.getName());
	private String toDay;

	CarStatus carStatus = new CarStatus();

	public GenStatusLogThread(String carNum, String toDay ) {
		this.toDay = toDay;
		carStatus.setCarNum(carNum);

		System.setProperty("logFilename", "./logs/status/status_" + toDay + ".log");

		org.apache.logging.log4j.core.LoggerContext ctx = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		ctx.reconfigure();
	}

	@Override    
	public void run() {
		int count = 86400;
		
		for(int i=0; i <= count; i++) {
			if(i==0) {
				logger.info( "SmartCar Status Information" + ",CarNum" + ",TireFL" + ",TireFR" + ",TireBL" + ",TireBR" + ",LightFL" + ",LightFR" + ",LightBL" + ",LightBR" + ",EngineInfo" + ",BreakInfo" + ",BatteryInfo");
			}
			
			logger.info(
					toDay +
					getSecToTime(i)	  + "," + 
					carStatus.getCarNum() + "," +
					carStatus.getTireFL() + "," + 
					carStatus.getTireFR() + "," + 
					carStatus.getTireBL() + "," +
					carStatus.getTireBR() + "," +
					carStatus.getLightFL() + "," +
					carStatus.getLightFR() + "," +
					carStatus.getLightBL() + "," +
					carStatus.getLightBR() + "," +
					carStatus.getEngineInfo() + "," +
					carStatus.getBreakInfo() + ","+
					carStatus.getBatteryInfo());
			i +=3;
		}
	}

	public String getSecToTime(int inSec) {

		String time = String.valueOf(inSec/3600);
	
		if(time.length() == 1){
			time = "0" + time;
		}
		String min = String.valueOf(inSec%3600/60);
		
		if(min.length() == 1){
			min = "0" + min;
		}
		
		String sec = String.valueOf(inSec%3600%60%60);
		if(sec.length() == 1){
			sec = "0" + sec;
		}

		return time + min + sec;
	}
	
	
	public static String getToDate() {
		return new SimpleDateFormat( "yyyyMMdd" ).format( new Date( System.currentTimeMillis() ) );
	}
}
