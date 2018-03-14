package com.zm.utils;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	static Logger log = Logger.getLogger(RegexUtil.class);
	public static boolean isMoblie(HttpServletRequest request) {
		//String userAgent = request.getHeader("user-agent");
		boolean isMoblie = true;
		Pattern pattern = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?(Build)?/");  
        Matcher matcher = pattern.matcher(request.getHeader("User-Agent"));  
        String model = null;  
        if (matcher.find()) {  
            model = matcher.group(1).trim();  
            log.info("通过userAgent解析出机型：" + model);
            isMoblie = false;
        }  
        log.info("是否为手机：" + (isMoblie?"是":"否"));
/*		String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
				"opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
				"nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
				"docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
				"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
				"wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
				"pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
				"240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
				"blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
				"kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
				"mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
				"prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
				"smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
				"voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
				"Googlebot-Mobile" };
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
					isMoblie = true;
					break;
				}
			}
		}*/
		return isMoblie;
	}
	
	public static boolean isPhone(Object mobiles){
		Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$");
		Matcher m = p.matcher(mobiles.toString());  
		return m.matches();  
	}
	public static boolean isNotPhone(Object mobiles){
		return !isPhone(mobiles);  
	}
	
	public static boolean isEmail(String email){  
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");     
		        Matcher m = pattern.matcher(email);     
		        return m.matches();     
		    } 
	//验证字符串非空
	public static boolean isNotBlank(String str) {
		if (str == null) {
			return false;
		} else if ("".equalsIgnoreCase(str.trim())) {
			return false;
		} else if ("null".equalsIgnoreCase(str.trim())) {
			return false;
		}
		return true;
	}
	//验证字符串为空
	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		} else if ("".equalsIgnoreCase(str.trim())) {
			return true;
		}
		return false;
	}
	//验证密码是否为空字符串，是否开头为空
	public static boolean validatePassword(String password){
		boolean flag = false;
		for(int i=0; i < password.length(); i++){ 
			 if(password.startsWith(" ")){
				 flag = false;
			 }else if (password.trim().isEmpty()){
					 flag = false;
	            }else{
	               flag= true;
	            }
	       }
			return flag;
	}
	
	/**
	 * BigDecimal乘法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal num1,BigDecimal num2){
		     BigDecimal a3 = new BigDecimal(""+100);
		     BigDecimal r = num2.divide(a3,4, BigDecimal.ROUND_HALF_UP).setScale(4,
			            BigDecimal.ROUND_HALF_UP);
		     BigDecimal targetNum = num1.multiply(r).setScale(2, BigDecimal.ROUND_HALF_UP);
			return targetNum;
			
	}
	/**
	 * BigDecimal化成百分比
	 * @param num
	 * @return
	 */
	public static String percent(BigDecimal num){
		   BigDecimal a = new BigDecimal(""+100);
		   BigDecimal r = num.multiply(a).divide(a,4, BigDecimal.ROUND_HALF_UP).setScale(4,
			            BigDecimal.ROUND_HALF_UP);
		   NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用 
		   percent.setMaximumFractionDigits(2); //百分比小数点最多2位 
		   String targetNum = percent.format(r.doubleValue());
			return targetNum;
			
		}
	//用正则表达式判断字符串是否为数字
    public static boolean isNumeric(String str) {
        String regEx = "^[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }
    
}
