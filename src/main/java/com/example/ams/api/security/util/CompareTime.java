package com.example.ams.api.security.util;

// Fonte: http://javaonlineguide.net/2016/12/how-to-compare-two-times-in-java-8-and-older-version.html
// Marcio JL Sousa

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class CompareTime {

	public static void main(String args[]) {

	

		String startTimeStr = JOptionPane
				.showInputDialog("Enter Start time: (HH:MM:SS)"); // Getting
																	// start
																	// time

		String endTimeStr = JOptionPane
				.showInputDialog("Enter End time: (HH:MM:SS)"); // Getting end
																// time

		// String startTimeStr ="09" + ":" + "59" + ":"+ "30";
		// String endTimeStr = "09" + ":" + "31" + ":"+ "30";

		compareTimeJava8(startTimeStr, endTimeStr);

		compareTime(startTimeStr, endTimeStr);

	}
	
	//Using Java8

	public static void compareTimeJava8(String startTimeStr, String endTimeStr) {

		LocalDate today = LocalDate.now();
		String startTimeStrT = today + " " + startTimeStr;
		String endTimeStrT = today + " " + endTimeStr;

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");

		try {

			LocalDateTime startTime = LocalDateTime.parse(startTimeStrT,
					formatter);
			LocalDateTime endTime = LocalDateTime.parse(endTimeStrT, formatter);

			Duration d = Duration.between(startTime, endTime);

			System.out.println("dur " + d.getSeconds());
			if (d.getSeconds() == 0)
				System.out.println("Both Start time and End Time are equal");
			else if (d.getSeconds() > 0)
				System.out.println("Start time is less than end time");
			else
				System.out.println("Start time is greater than end time");

		} catch (DateTimeParseException e) {
			System.out.println("Invalid Input" + e.getMessage());

		}

	}
	
	
	
	
	//Using Java Older version

	public static void compareTime(String startTimeStr, String endTimeStr) {

		Pattern p = Pattern.compile("^([0-2][0-3]):([0-5][0-9]):([0-5][0-9])$"); //Regex is used to validate time format (HH:MM:SS)

		int hhS = 0;
		int mmS = 0;
		int ssS = 0;
		
		int hhE = 0;
		int mmE = 0;
		int ssE = 0;

		Matcher m = null;

		m = p.matcher(startTimeStr);
		if (m.matches()) {
			String hhStr = m.group(1);
			String mmStr = m.group(2);
			String ssStr = m.group(3);

			hhS = Integer.parseInt(hhStr);
			mmS = Integer.parseInt(mmStr);
			ssS = Integer.parseInt(ssStr);

		}

		else {
			System.out.println("Invalid start time");
			System.exit(0);

		}

	

		m = p.matcher(endTimeStr);
		if (m.matches()) {
			String hhStr = m.group(1);
			String mmStr = m.group(2);
			String ssStr = m.group(3);

			hhE = Integer.parseInt(hhStr);
			mmE = Integer.parseInt(mmStr);
			ssE = Integer.parseInt(ssStr);

		}

		else {
			System.out.println("Invalid End time");
			System.exit(0);

		}

		

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hhS); // Start hour
		cal.set(Calendar.MINUTE, mmS); // Start Mintue
		cal.set(Calendar.SECOND, ssS); // Start second

		Time startTime = new Time(cal.getTime().getTime());
		// System.out.println("your time: "+sqlTime3);

		cal.set(Calendar.HOUR_OF_DAY, hhE); // End hour
		cal.set(Calendar.MINUTE, mmE); // End Mintue
		cal.set(Calendar.SECOND, ssE); // End second

		Time endTime = new Time(cal.getTime().getTime());

		if (startTime.equals(endTime)) {
			System.out.println("Both Start time and End Time are equal");
		} else if (startTime.before(endTime)) {
			System.out.println("Start time is less than end time");
		}

		else
			System.out.println("Start time is greater than end time");

	}

}