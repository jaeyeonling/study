package com.gds.contents.data;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.util.WebUtils;

import com.gds.cmmn.dao.CommonDAO;
import com.gds.cmmn.util.CharEncoding;
import com.gds.cmmn.util.DateUtil;
import com.gds.cmmn.util.FileUtil;
import com.gds.cmmn.util.MakeCSVUtills;
import com.gds.cmmn.util.StringUtil;
import com.gds.cmmn.view.JsonView;
import com.gds.contents.cmmn.CmmnController;
import com.gds.contents.cmmn.constants.FrontConstants;
import com.gds.contents.cmmn.handler.SessionHandler;
import com.gds.contents.cmmn.vo.MemberFilesetVO;
import com.gds.contents.cmmn.vo.MemberVO;
import com.gds.contents.data.service.CommonDataService;
import com.gds.contents.data.service.WeatherReportService;

import egovframework.rte.fdl.property.impl.EgovPropertyServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class WeatherReportController {

//	private static final Log log = LogFactory.getLog(CmmnController.class);
//	private static Logger logger = Logger.getLogger(CommonDataController.class);
	
	@Autowired
	private WeatherReportService weatherReportService;
	
	//하는중
    @RequestMapping(value = "/data/weather/weatherList.do")
    public String weatherList(HttpServletRequest req, HttpServletResponse res, ModelMap modelMap, @RequestParam Map<String, Object> params) {
    	System.out.println("::::start:::");
    	/*
    	if(modelMap.get("weatherList") != null) {
    		
    	}
    	*/
    	System.out.println(":::modelMap:::"+modelMap.get("weatherList"));
    	return "/contents/data/weatherReport/wsrList";
    }
    
    //하는중
    @RequestMapping(value = "/data/weather/weatherGroupPopup.do")
    public String weatherGroupPopup(HttpServletRequest req, HttpServletResponse res, ModelMap modelMap, @RequestParam Map<String, Object> params) {
    	System.out.println("::::weatherGroupPopup:::");
    	return "/contents/data/weatherReport/elementLayerPopup3";
    }
    
    @RequestMapping(value = "/data/weather/weatherListAjax.do")
    public View weatherListAjax(HttpServletRequest req, HttpServletResponse res, ModelMap modelMap, @RequestParam Map<String, Object> params) {
    	
    	System.out.println("::::params::::"+params);
    	
    	String startDt = req.getParameter("startDt");
    	String endDt = req.getParameter("endDt");
    	String stnIds = req.getParameter("stnIds");
    	
    	params.put("startDt", startDt);
    	params.put("endDt", endDt);
    	params.put("stnIds", stnIds);
    	
    	weatherReportService.weatherList(modelMap, params);
    	
    	System.out.println("::::modelMap:::"+modelMap);
    	
    	return new JsonView();
    }
    
    
    @RequestMapping(value = "/data/weather/weatherDetail.do")
    public View weatherDetail(HttpServletRequest req, HttpServletResponse res, ModelMap modelMap, @RequestParam Map<String, Object> params) {
    	
    	String startDt = req.getParameter("startDt");
    	String endDt = req.getParameter("endDt");
    	String stnIds = req.getParameter("stnIds");
    	String mnbyFcNo = req.getParameter("mnbyFcNo");
    	
    	params.put("startDt", startDt);
    	params.put("endDt", endDt);
    	params.put("stnId", stnIds);
    	params.put("mnbyFcNo", mnbyFcNo);
    	
    	System.out.println("::::params::::"+params);
    	weatherReportService.weatherDetail(modelMap, params);
    	
    	return new JsonView();
    }
}
