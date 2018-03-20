package com.zm.utils;

public class SafeSQL {

	private static String inject_str = " exec|execute|insert|select|delete|update|drop|create|grant|chr|mid|count|master|truncate|" +
            "char|declare|sitename|net user|xp_cmdshell|table|from|use|group_concat|column_name|" +
            "information_schema.columns|table_schema|union|where|and|or|order|by|" +
            "like|'|-|+|,|;|*|%|//|/|#";//过滤掉的sql关键字，可以手动添加

	public static boolean safeSQL(String str) {
		String[] inj_stra = inject_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.toLowerCase().indexOf(" " + inj_stra[i] + " ") >= 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean safeSQL(String... strs) {
		String[] inj_stra = inject_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			for(String str:strs){
				if (str.toLowerCase().indexOf(" " + inj_stra[i] + " ") >= 0) {
					return false;
				}
			}
		}
		return true;
	}
	
}
