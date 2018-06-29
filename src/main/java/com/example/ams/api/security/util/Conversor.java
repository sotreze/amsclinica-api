package com.example.ams.api.security.util;

public class Conversor {
	
	public static void main(String[] args) {

		
		String isBoolean = "true";
		boolean convertedIsBoolean = Boolean.parseBoolean(isBoolean);
		System.out.println("Convert String to boolean: " + convertedIsBoolean);

		String isNumberBoolean = "1";
		boolean convertedIsNumberBoolean = Boolean.parseBoolean(isNumberBoolean);
		System.out.println("Convert String Number to boolean: " + convertedIsNumberBoolean);

		String isInvalidBoolean = "-abc";
		boolean convertedIsInvalidBoolean = Boolean.parseBoolean(isInvalidBoolean);
		System.out.println("Convert String string to boolean: " + convertedIsInvalidBoolean);
		
	    boolean b1 = true;
	    String str = String.valueOf(b1);
	    System.out.println("boolean b1 in string form is " + str);

	}

}
