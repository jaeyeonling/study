package com.gds.contents.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.cypher.internal.compiler.v2_1.functions.Has;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import scala.collection.generic.BitOperations.Int;

import com.gds.cmmn.dao.CommonDAO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("weatherReportService")
public class WeatherReportServiceImpl implements WeatherReportService {

	@Autowired
	private CommonDAO commonDao;
	
	@Override
	public void weatherList(ModelMap modelMap, Map<String, Object> params) {
		List<EgovMap> selectList = commonDao.selectList("weather.weatherList", params);
		Map<String, EgovMap> resultMap = new HashMap<String, EgovMap>();
		List<EgovMap> resultList = new ArrayList<EgovMap>();
		
		for (int i = 0; i < selectList.size(); i++) {
			EgovMap map = selectList.get(i);
			
			String key = map.get("fcTma").toString() + map.get("mnbyFcNo").toString() + map.get("stnId").toString();
			if (resultMap.containsKey(key)) {
				EgovMap addedMap = resultMap.get(key);

				if (Long.parseLong(addedMap.get("inpTma").toString()) < Long.parseLong((String) map.get("inpTma"))) {
	                System.out.println("바꾸기 완료 before: " + map + " / after: " + addedMap);
                    resultMap.put(key, map);
                }
			} else {
				resultMap.put(key, map);
			}
		}

		for (String key : resultMap.keySet()) {
			resultList.add(resultMap.get(key));
		}

		modelMap.put("json", resultList);
		modelMap.put("weatherList", resultList);
	}

	@Override
	public void weatherDetail(ModelMap modelMap, Map<String, Object> params) {
		params.put("stnIds", "159");
		
		System.out.println(":::params::ㅅㅂ=="+params);
		
		List<EgovMap> selectList = commonDao.selectList("weather.weatherDetail", params);
		
		Map<String, EgovMap> resultMap = new HashMap<String, EgovMap>();
		List<EgovMap> resultList = new ArrayList<EgovMap>();
		
		for (int i = 0; i < selectList.size(); i++) {
			EgovMap map = selectList.get(i);
			
			String key = map.get("fcTma").toString() + map.get("mnbyFcNo").toString() + map.get("stnId").toString();
			if (resultMap.containsKey(key)) {
				EgovMap addedMap = resultMap.get(key);

				if (Long.parseLong(addedMap.get("inpTma").toString()) < Long.parseLong((String) map.get("inpTma"))) {
	                System.out.println("바꾸기 완료 before: " + map + " / after: " + addedMap);
                    resultMap.put(key, map);
                }
			} else {
				resultMap.put(key, map);
			}
		}

		for (String key : resultMap.keySet()) {
			resultList.add(resultMap.get(key));
		}
		
		modelMap.put("json", resultList);
	}
}

