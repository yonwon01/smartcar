package com.bigdata2017.smartcar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenStatusLog {

	public static void main(String[] args) {
		String toDay =  getToDate();
		int carCount = 1;

		if(args != null  && args.length > 1) {
			toDay = args[0];
		}
		if(args != null && args.length > 1) {
			carCount = Integer.parseInt(args[1]);
		}
		
		for(int i = 1; i <= carCount ;i++) {
			new GenStatusLogThread( getCarNum(i), toDay ).run();
		}
	}


	public static String getCarNum(int num) {
		String[] carNumPrefix = {"A", "B" , "C" , "D" , "E" , "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; 
		String prefixNum = carNumPrefix[randomRange(0, 25)] ;

		DecimalFormat format = new DecimalFormat("0000");
		String carNum = format.format(num);

		return prefixNum + carNum;
	}


	public static int randomRange(int n1, int n2) {  
		return (int)((Math.random() * (n2 - n1 + 1)) + n1);
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
