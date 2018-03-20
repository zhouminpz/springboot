package com.zm.utils;

public class SafeXSS {

	private static String xss_str = "'|script|<|>|iframe|eval";//关键字可手动添加

	public static boolean safeXSS(String str) {
		String[] inj_stra = xss_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.toLowerCase().indexOf(inj_stra[i]) >= 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean safeXSS(String... strs) {
		String[] inj_stra = xss_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			for(String str:strs){
				if (str.toLowerCase().indexOf(inj_stra[i]) >= 0) {
					return false;
				}
			}
		}
		return true;
	}
}
