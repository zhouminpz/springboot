package com.zm.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
    static Logger logger = Logger.getLogger(IPUtil.class);
    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        logger.info("x-forwarded-for: "+request.getHeader("x-forwarded-for"));
        logger.info("Proxy-Client-IP: "+request.getHeader("Proxy-Client-IP"));
        logger.info("WL-Proxy-Client-IP: "+request.getHeader("WL-Proxy-Client-IP"));
        logger.info("RemoteAddr: "+request.getRemoteAddr());
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        logger.info(">>>>>>>>>>>>>>>>"+ip);
        return StringUtils.isNotBlank(ip) ? ip.split(",")[0] : null;
    }
}
