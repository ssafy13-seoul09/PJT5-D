package com.ssafy.util;


public class SearchUtil {
	
	public static boolean KMP(String text, String pattern) {
	    int[] lps = buildLPS(pattern);
	    int i = 0, j = 0;

	    while (i < text.length()) {
	        if (text.charAt(i) == pattern.charAt(j)) {
	            i++;
	            j++;
	            if (j == pattern.length()) return true;
	        } else {
	            if (j != 0) {
	                j = lps[j - 1];
	            } else {
	                i++;
	            }
	        }
	    }

	    return false;
	}

	public static int[] buildLPS(String pattern) {
	    int[] lps = new int[pattern.length()];
	    int len = 0;
	    int i = 1;

	    while (i < pattern.length()) {
	        if (pattern.charAt(i) == pattern.charAt(len)) {
	            len++;
	            lps[i++] = len;
	        } else {
	            if (len != 0) {
	                len = lps[len - 1];
	            } else {
	                lps[i++] = 0;
	            }
	        }
	    }

	    return lps;
	}
   
}
