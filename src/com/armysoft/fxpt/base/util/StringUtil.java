package com.armysoft.fxpt.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static String getImageSrc(String img){
		 Matcher   matcher=Pattern.compile("src=\"([^\"]+)\"").matcher(img);   
		 while (matcher.find()){   
			 String   group   =   matcher.group(1);  
			 return group;	
		 }
		 return "";
	}
}
