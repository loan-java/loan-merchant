package com.mod.loan.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类，所有cookie都默认放在根目录下
 * @author wugy
 *  2016年9月20日下午12:59:06
 *
 */
public class CookieUtils
{

    private static String STORAGE_ENCODING = "UTF-8";
	/**
	 * 
	 * 功能描述：根据cookie键值获取value
	 * @param request
	 * @param key cookie键值
	 */
	public static String getCookieValue(HttpServletRequest request,String key){
		Map<String, String> cookies = getCookies(request);
		return cookies.get(key);
	}
	/**
	 * 
	 * 功能描述：获取所有cookies
	 * @param request
	 */
	public static Map<String, String> getCookies(HttpServletRequest request){
		Map<String, String> cookies=new HashMap<String, String>();
		Cookie[] _cookies = request.getCookies();
		if (_cookies==null||_cookies.length==0) {
			return cookies;
		}
		try {
			for (Cookie cookie : _cookies) {
				cookies.put(cookie.getName(),  URLDecoder.decode(cookie.getValue(), STORAGE_ENCODING));
			}
		} catch (UnsupportedEncodingException uee) {
			// TODO: handle exception
			uee.printStackTrace();
		}
		return cookies;
	}

	/**
	 * 移除cookie
	 * @param response
	 * @param name
	 */
    public static void removeCookie(HttpServletRequest request,HttpServletResponse response,String key,String domain)
    {
    	Cookie[] cookies = request.getCookies();
    	if (cookies==null||cookies.length==0) {
			return;
		}
    	for (Cookie cookie : cookies) {
    		if (cookie.getName().equals(key)) {  
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setDomain(domain);
                response.addCookie(cookie);  
                break;  
            }  
		}
    }

    public static void removeCookie(HttpServletRequest request,HttpServletResponse response,String key)
    {
    	Cookie[] cookies = request.getCookies();
    	if (cookies==null||cookies.length==0) {
			return;
		}
    	for (Cookie cookie : cookies) {
    		if (cookie.getName().equals(key)) {  
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);  
                break;  
            }  
		}
    }
    /**
     * 
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param domian
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue,String domian)
    {
        try
        {
            Cookie theCookie = new Cookie(URLEncoder.encode(cookieName, STORAGE_ENCODING), URLEncoder.encode(null==cookieValue?"":cookieValue, STORAGE_ENCODING));
            theCookie.setPath("/");
            theCookie.setDomain(domian);
            response.addCookie(theCookie);
        }
        catch(UnsupportedEncodingException uee)
        {
            uee.printStackTrace();
        }
    }
    /**
     * 增加cookie
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue)
    {
        try
        {
            Cookie theCookie = new Cookie(URLEncoder.encode(cookieName, STORAGE_ENCODING), URLEncoder.encode(null==cookieValue?"":cookieValue, STORAGE_ENCODING));
            theCookie.setPath("/");
            response.addCookie(theCookie);
        }
        catch(UnsupportedEncodingException uee)
        {
            uee.printStackTrace();
        }
    }
    /**
     * 增加cookie
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage,String domian)
    {
        try
        {
            Cookie theCookie = new Cookie(URLEncoder.encode(cookieName, STORAGE_ENCODING), URLEncoder.encode(null==cookieValue?"":cookieValue, STORAGE_ENCODING));
            theCookie.setMaxAge(cookieMaxage);
            theCookie.setPath("/");
            response.addCookie(theCookie);
        }
        catch(UnsupportedEncodingException uee)
        {
            uee.printStackTrace();
        }
    }
}
