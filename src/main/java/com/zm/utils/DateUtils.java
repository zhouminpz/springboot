package com.zm.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * 日期处理工具类
 * @category：工具类
 */
public class DateUtils {
 
 /**
  * 
  * 日期数据格式的处理
  * @return    返回String类型的新数据
  */
 @SuppressWarnings("unused")
private static String formatData(int data){
  //如果数字小于10，数字前加0
  if(data < 10){   
   return "0"+data;
  }else{
   return data+"";
  }
  
 }
 /**
  * 
  * 判断是否是闰年
  * @return    返回 boolean
  */
 private  static boolean  isLeap(int year){
  //如果是闰年返回true，否则为false
        if(year%4==0 && year%100!=0 ||year%400==0){
              return true;
         }
        else{
              return false;
        }
     }
 /***************************************** 
  * @功能     计算某年某周的开始日期 
  * @return  interger 
  * @throws ParseException 
  ****************************************/  
  public static String getYearWeekFirstDay(int yearNum,int weekNum) throws ParseException {  

	     Calendar cal = Calendar.getInstance();  
	     cal.set(Calendar.YEAR, yearNum);  
	     cal.set(Calendar.WEEK_OF_YEAR, weekNum);  
	     cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
	     //分别取得当前日期的年、月、日  
	     String tempYear = Integer.toString(cal.get(Calendar.YEAR));  
	     String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);  
	     String tempDay = Integer.toString(cal.get(Calendar.DATE));  
	     String tempDate = tempYear + "-" +tempMonth + "-" + tempDay;  
	     return SetDateFormat(tempDate,"yyyy-MM-dd");  



  } 
  /***************************************** 
   * @功能     计算某年某周的结束日期 
   * @return  interger 
   * @throws ParseException 
   ****************************************/  
   public static String getYearWeekEndDay(int yearNum,int weekNum) throws ParseException {  
	     Calendar cal = Calendar.getInstance();  
	     cal.set(Calendar.YEAR, yearNum);  
	     cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);  
	     cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
	     //分别取得当前日期的年、月、日  
	     String tempYear = Integer.toString(cal.get(Calendar.YEAR));  
	     String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);  
	     String tempDay = Integer.toString(cal.get(Calendar.DATE));  
	     String tempDate = tempYear + "-" +tempMonth + "-" + tempDay;  
	     return SetDateFormat(tempDate,"yyyy-MM-dd");  
   }
  
  /**
   * @return String 
   * @throws ParseException 
   */  
   public static String SetDateFormat(String myDate,String strFormat) throws ParseException  
   {  
 
           SimpleDateFormat sdf = new SimpleDateFormat(strFormat);  
   String sDate = sdf.format(sdf.parse(myDate));  
           return sDate;  
   }
   
    /**
     * 
     * 求一个月的最大一天
     * @return    返回 整型天数
     */
 @SuppressWarnings("unused")
private static int maxDay(int year,int month)
 {
  //初始化12个月份的天数
        int[] months={31,0,31,30,31,30,31,31,30,31,30,31};
        //如果不是2月则返回该月份的天数
        if(month!=2){         
           return months[month-1];
         }
         else{
              //如果是闰年返回为29天，否则28天     
           if(isLeap(year)){      
           return 29;
           }
           else{       
            return 28;
          }
      }      
   }
   /**
    * 
    * 比较两个字符串的日期的大小
    * @return    返回 boolean
    */
 @SuppressWarnings("unused")
 private static boolean compareToDate(String dateFrom,String dateTo){
        //如果开始日期小于等于结束日期返回为true,否则为false
     if(dateFrom.compareTo(dateTo) <= 0){         
        return true;
       }
       return false;
      }
  /**
   * 
   * 由日历获取星期几
   * @return    返回整型星期 1：星期日；2：星期一；3：星期二；4：星期三；5：星期四；6：星期五；7：星期六；
   */
 @SuppressWarnings("unused")
 private static int getWeekValue(int year,int month,int day){
    //实例化Calendar 
   Calendar now = Calendar.getInstance(); 
   //设置某年某月某日的参数
   now.set(Calendar.YEAR, year);
   now.set(Calendar.MONTH, month-1);
   now.set(Calendar.DATE, day);
   return now.get(Calendar.DAY_OF_WEEK);
  }
    public enum DateFormatType {   
         /**  
          * 格式为：yyyy-MM-dd HH:mm:ss  
          */  
         DATE_FORMAT_STR("yyyy-MM-dd HH:mm:ss"),   
         /**  
          * 格式为：yyyyMMddHHmmss  
          */  
         SIMPLE_DATE_TIME_FORMAT_STR("yyyyMMddHHmmss"),   
   
         /**  
          * 格式为：yyyy-MM-dd  
          */  
         SIMPLE_DATE_FORMAT_STR("yyyy-MM-dd"),   
         
         SIMPLE_DATE_FORMAT_STR1("yyyyMMdd"),  
         SIMPLE_DATE_FORMAT_STR2("yyyy年MM月dd日"),  
         /**  
          * 格式为：yyyy/MM/dd  
          */  
         SIMPLE_DATE_FORMAT_VIRGULE_STR("yyyy/MM/dd"),   
   
         /**  
          * 格式为：HH:mm:ss  
          */  
         HOUR_MINUTE_SECOND("HH:mm:ss"),   
   
         /**  
          * 格式为：HH:mm  
          */  
         HOUR_MINUTE("HH:mm");   
   
         private final String value;   
   
         DateFormatType(String formatStr) {   
             this.value = formatStr;   
         }   
   
         public String getValue() {   
             return value;   
         }   
     }   
 
    /**  
     * 获取当前时间日期的字符串   
     */  
    public static String getCurrentDateStr(DateFormatType dateFormatType) {   
        Date date = getCurrentDate();   
        return (String) OpearationDate(date, dateFormatType.getValue());   
    }   
  
    /**  
     * 时间、日期格式化成字符串  
     */  
    public static String formatDate(Date date, DateFormatType dateFormatType) {   
        return (String) OpearationDate(date, dateFormatType.getValue());   
    }   
  
    /**  
     * 从字符串解析成时间、日期  
     */  
    public static Date parseDate(String dateStr, DateFormatType dateFormatType) {   
        return (Date) OpearationDate(dateStr, dateFormatType.getValue());   
    }   
  
    /**  
     * 获取当前系统时间(原始格式)  
     */  
    public static Date getCurrentDate() {   
        Date date = new Date(System.currentTimeMillis());   
        return date;   
    }   
    /**  
     * 获取当前系统时间(原始格式)  
     */  
    public static long getCurrentTime() {   
    	return  System.currentTimeMillis();  
    } 
    /**  
     * 获取当前日期的年、月、日、时、分、秒  
     */  
    public static int getCurrentTime(TimeFormatType timeFormatType) {   
        return getTime(getCurrentDate(), timeFormatType);   
    }   
  
    /**  
     * 获取指定日期的年、月、日、时、分、秒  
     */  
    public static int getTime(Date date, TimeFormatType timeFormatType) {   
        try {   
            Calendar c = Calendar.getInstance();   
            c.setTime(date);   
            int type = timeFormatType.getValue();   
            int i = c.get(type);   
            return type == 2 ? i + 1 : i;   
        } catch (Exception e) {   
            throw new RuntimeException("获取失败", e);   
        }   
    }   
  
    /**  
     * 获取指定日期的毫秒数  
     */  
    public static long getMillis(Date date) {   
        Calendar c = Calendar.getInstance();
        c.setTime(date);   
        return c.getTimeInMillis();   
    }   
  
    /**  
     * 日期相加、减操作  
     *   
     * 所返回结果单位为:天数  
     */  
    public static int operationDate(Date date, Date diffDate, DateOperationType dateOperationType) {   
        long add = getMillis(date) + getMillis(diffDate);   
        long diff = getMillis(date) - getMillis(diffDate);   
        return (int) ((dateOperationType.getValue() ? add : diff) / (24 * 3600 * 1000));   
    }   
  
    /**  
     * 日期月份相加、减操作  
     */  
    public static Date operationDateOfMonth(Date date, int month, DateOperationType dateOperationType) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        c.add(Calendar.MONTH, dateOperationType.getValue() ? month : month - (month * 2));   
        return c.getTime();   
    }   

    /**  
     * 日期天数相加、减操作  
     */  
    public static Date operationDateOfDay(Date date, int day, DateOperationType dateOperationType) {   
       /* Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        long millis = c.getTimeInMillis();   
        long millisOfday = day * 24 * 3600 * 1000;   
        long sumMillis = dateOperationType.getValue() ? (millis + millisOfday) : (millis - millisOfday);   
        c.setTimeInMillis(sumMillis);   
        return c.getTime();   */
    	
    	 Calendar c = Calendar.getInstance();   
         c.setTime(date);   
//         long millis = c.getTimeInMillis();   
//         long millisOfday = day * 24 * 3600 * 1000;   
//         long sumMillis = dateOperationType.getValue() ? (millis + millisOfday) : (millis - millisOfday);   
//         c.setTimeInMillis(sumMillis);
         day =  dateOperationType.getValue() ? day : -day;   
         c.add(Calendar.DATE, day);
         return c.getTime(); 
    }   
  
    private static Object OpearationDate(Object object, String formatStr) {   
        if (object == null || null == formatStr || "".equals(formatStr)) {   
            throw new RuntimeException("参数不能为空");   
        }   
        SimpleDateFormat format = new SimpleDateFormat(formatStr);   
        try {   
            if (object instanceof Date)   
                return format.format(object);   
            else  
                return format.parse(object.toString());   
        } catch (Exception e) {   
            throw new RuntimeException("操作失败", e);   
        }   
  
    }   
    /** 
    * 格式化日期时间 
    *  
    * @param date 
    * @param pattern 
    *            格式化模式，详见{@link SimpleDateFormat}构造器 
    *            <code>SimpleDateFormat(String pattern)</code> 
    * @return 
    */  
   public static String formatDatetime(Date date, String pattern) {  
       SimpleDateFormat customFormat =  new SimpleDateFormat ();
       customFormat.applyPattern(pattern);  
       return customFormat.format(date);  
   }  
    /** 
     * 获得当前月的最后一天 
     * <p> 
     * HH:mm:ss为0，毫秒为999 
     *  
     * @return 
     */  
    public static Date lastDayOfMonth() {  
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零  
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零  
        cal.set(Calendar.MINUTE, 0);// m置零  
        cal.set(Calendar.SECOND, 0);// s置零  
        cal.set(Calendar.MILLISECOND, 0);// S置零  
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1  
        cal.set(Calendar.MILLISECOND, -1);// 毫秒-1  
        Date day = cal.getTime();
        return day;  
    }  
  
    /** 
     * 获得当前月的第一天 
     * <p> 
     * HH:mm:ss SS为零 
     *  
     * @return 
     */  
    public static Date firstDayOfMonth() {  
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		Date day = cal.getTime();
		return day;
    }  
    
    public static int  getDayOfIntFormat(Date date,String pattern){
		String dateStr = formatDatetime(date,pattern);
		return Integer.parseInt(dateStr);
    }
    public enum DateOperationType {   
  
        /**  
         * 加操作  
         */  
        ADD(true),   
  
        /**  
         * 减操作  
         */  
        DIFF(false);   
  
        private final boolean value;   
  
        DateOperationType(boolean operation) {   
            this.value = operation;   
        }   
  
        public boolean getValue() {   
            return value;   
        }   
    }   
  
    public enum TimeFormatType {   
  
        YEAR(1), MONTH(2), DAY(5), HOUR(11), MINUTE(12), SECOND(13);   
        private final int value;   
  
        TimeFormatType(int formatStr) {   
            this.value = formatStr;   
        }   
  
        public int getValue() {   
            return value;   
        }   
    }   
    /***************************************** 
     * @功能     计算指定日期某年的第几周 
     * @return  String yyyyU 
     * @throws ParseException 
     ****************************************/  
     public static String getWeekNumAndYearByDay(Date curDate)  {  
     	return getWeekNumAndYearByDay(curDate,true)+"";  
     }  
     
     public static Integer getWeekNumAndYearByDay(Date curDate ,boolean fillzero)  {  
 	    Calendar calendar = Calendar.getInstance();  
 	    calendar.setFirstDayOfWeek(Calendar.MONDAY);  
 	    //calendar.setMinimalDaysInFirstWeek(7); 
 	    calendar.setTime(curDate);  
 	    int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
 	    int yeat = calendar.get(Calendar.YEAR);
 	    String week = (iWeekNum<10 && fillzero?"0":"")+iWeekNum;
 	    return Integer.valueOf(yeat+""+week);  
     } 
     /**  
      * 计算两个日期之间相差的天数  
      * @param smdate 较小的时间 
      * @param bdate  较大的时间 
      * @return 相差天数 
      * @throws ParseException  
      */    
     public static int daysBetween(Date smdate,Date bdate) throws ParseException {    
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
         smdate=sdf.parse(sdf.format(smdate));  
         bdate=sdf.parse(sdf.format(bdate));  
         Calendar cal = Calendar.getInstance();    
         cal.setTime(smdate);    
         long time1 = cal.getTimeInMillis();                 
         cal.setTime(bdate);    
         long time2 = cal.getTimeInMillis();         
         long between_days=(time2-time1)/(1000*3600*24);  
         return Integer.parseInt(String.valueOf(between_days));           
     }  
     
     public static String getCurrentTimeMin() {   
     	return  getCurrentTime()/1000+"";  
     } 
     public static Date stringToDate(String dateStirng) throws ParseException{
// 		String time = dateStirng+" 00:00:00";
 		String time = dateStirng;
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		Date date = sdf.parse(time);
 		return date;
 	}
     public static int minuteBetween(Date smdate,Date bdate) throws ParseException{
    	 SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 long from = simpleFormat.parse(simpleFormat.format(smdate)).getTime();  
    	 long to = simpleFormat.parse(simpleFormat.format(bdate)).getTime();  
    	 int minutes = (int) ((to - from)/(1000 * 60));
    	 return minutes;
     }
     public static void main(String[] args) {
        	try {	
        		String test = "2017-09-20 19:35:44";
        		String testa = "2017-09-20 19:32:44";
        		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		minuteBetween(simpleFormat.parse(test),simpleFormat.parse(testa));
     		} catch (Exception e) {    			
     			e.printStackTrace();
     		}
     }
}

