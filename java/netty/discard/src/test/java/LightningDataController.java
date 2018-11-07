package com.gds.contents.data;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gds.cmmn.dao.CommonDAO;
import com.gds.cmmn.util.DateUtil;
import com.gds.cmmn.util.FileUtil;
import com.gds.cmmn.util.MakeCSVUtills;
import com.gds.cmmn.util.StringUtil;
import com.gds.contents.cmmn.handler.SessionHandler;
import com.gds.contents.cmmn.vo.MemberVO;
import com.gds.contents.data.service.CommonDataDAO;
import com.gds.contents.data.service.CommonDataService;
import com.gds.contents.data.service.LightningDataService;

import egovframework.rte.fdl.property.impl.EgovPropertyServiceImpl;


/**
 * @Class Name : LightningDataController.java
 * @Description : Lightning Data Controller Class
 * @Modification Information
 * @
 * @  수정일                  수정자                       수정내용
 * @ ---------	---------	-------------------------------
 * @ 2018.09.10 한예진                       생성
 *
 * @author 한예진
 * @since 2018.09.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by SELab All right reserved.
 */


@Controller
public class LightningDataController {

    private static Logger logger = Logger.getLogger(LightningDataController.class);

    @Autowired
    private CommonDataService       commonDataService;

    @Resource(name="lightningService")
    private LightningDataService lightningService;

    @Resource(name = "propertiesService")
    private EgovPropertyServiceImpl propertiesService;

    @Autowired
    private CommonDAO               commonDao;

    @Autowired
    private SessionHandler          sessionHandler;

    /**
     * 낙뢰 자료 조회
     *
     * @param req
     * @param res
     * @param modelMap
     * @param params
     * @return
     */
    @RequestMapping(value = "/data/lightning/lightningRltmList.do")
    public String selectLightningDataList(HttpServletRequest req, HttpServletResponse res, ModelMap modelMap, @RequestParam Map<String, Object> params){

        params.put("pagingYn", "Y");
        lightningService.selectLightningDataList(modelMap, params);

        modelMap.putAll(params);
        return "/contents/data/lightning/lightningRltmList";
    }

    /**
     * 낙뢰 자료 다운로드
     *
     * @param req
     * @param res
     * @param modelMap
     * @param params
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/data/lightning/downloadDataCSV.do")
    public void csvFileDownload(HttpServletRequest req, HttpServletResponse res, ModelMap modelMap, @RequestParam Map<String, Object> params) throws IOException{

        String engHeader = "";
        String korHeader = "시간,위도,경도,낙뢰 종류,낙뢰 강도";

        String isSample = (String) params.get("isSample");
        if (isSample == null || isSample.equals("N")){
            params.put("pagingYn", "N");
        } else if(isSample.equals("Y")){
            params.put("firstIndex", 1);
            params.put("lastIndex", 24);
            params.put("pagingYn", "Y");
        }

        ArrayList<String> strList = lightningService.selectLightningDataDownloadList(modelMap, params);

        params.put("engHeader", engHeader);
        params.put("korHeader", korHeader);
        params.put("strList", strList);

        String startLat = (String)params.get("startLat");
        String startLon = (String)params.get("startLon");
        String str = startLat.split("\\.")[0] + startLon.split("\\.")[0];
        if(str.length() > 10){
            str = str.substring(0, 10);
        }

        params.put("stnIds", "_" + str);

        sendCSVFile(req, res, modelMap, params);
    }

    /**
     * 낙뢰 지도 영역 선택
     *
     * @param req
     * @param res
     * @param modelMap
     * @param params
     * @return
     */
    @RequestMapping(value = "/data/lightning/mapLayerPopup.do")
    public String mapLayerPopup(HttpServletRequest req, HttpServletResponse res, ModelMap modelMap, @RequestParam Map<String, Object> params){
        return "/contents/data/lightning/mapLayerPopup";
    }

    /**
     * 낙뢰 파일셋 조회
     *
     * @param req
     * @param res
     * @param modelMap
     * @param params
     * @return
     */
    @RequestMapping(value = "/data/lightning/lightningList.do")
    public String selectLightningList(HttpServletRequest req, HttpServletResponse res, ModelMap modelMap, @RequestParam Map<String, Object> params){

        return "/contents/data/lightning/lightningList";
    }

    private void sendCSVFile(HttpServletRequest req, HttpServletResponse res, ModelMap modelMap, @RequestParam Map<String, Object> params) throws IOException{

        MemberVO member = sessionHandler.getCurrentSessionInfo();
        if (member == null) {
            params.put("loginId", "guest");
            member = (MemberVO) commonDao.selectObject("common.selectLogin", params);
        }

        logger.debug("member : " + member.getMberSn());

        if (member != null && !StringUtil.isEmptyStr(member.getMberSn())) {

            PrintWriter out = res.getWriter();

            if(!FileUtil.getExistDirectory(propertiesService.getString("infofile.temp.dir"))){
                FileUtil.createNewDirectory(propertiesService.getString("infofile.temp.dir"));
            }

            String tempDir = propertiesService.getString("infofile.temp.dir") + File.separator + DateUtil.getYyyymmddhhmmss();

            String engHeader = (String) params.get("engHeader");
            String korHeader = (String) params.get("korHeader");

            MakeCSVUtills.makeCSV(korHeader, tempDir);
            long start = System.currentTimeMillis();

            ArrayList<String> strList = (ArrayList<String>) params.get("strList");

            long end = System.currentTimeMillis();

            MakeCSVUtills.makeCSV_List(strList, engHeader, tempDir);
            logger.info("##  소요시간(초.0f) : " + (end - start) / 1000.0f + "초");

            if(!StringUtil.isEmptyObj(params.get("dataReqstSn"))){

                File f = new File(tempDir + ".csv");
                if (f.exists()) {
                    params.put("fileSizeMg", f.length() * 1/1000);
                    commonDataService.updateFileSizeOri(modelMap, params);
                }
            } else {
                params.put("memberVO", member);
                params.put("sviceSe", params.get("serviceSe"));
                params.put("fileSizeMgs", "0");
                params.put("processSt", "G00203");

                params.put("reqstPurposeCd", "F00408"); // TODO : 비회원 기본 활용도 코드 : [학술/연구]

                File f = new File(tempDir + ".csv");
                if (f.exists()) {
                    params.put("fileSizeKb", f.length() * 1/1000);
                }

                params.put("filesetSns", params.get("stnIds"));
                params.put("filesetDtlSns", params.get("elementCds"));
                commonDataService.insertRequests(modelMap, params);
            }

            if(!StringUtil.isEmptyObj(params.get("dataReqstFileSn"))){

                File f = new File(tempDir + ".csv");
                if (f.exists()) {
                    params.put("fileSizeMg", f.length() * 1/1000);
                    commonDataService.updateFileSize(modelMap, params);
                }
            }

            if(out != null) {
                out.print(tempDir + ".csv");
                out.flush();
                out.close();
            }
        }
    }
}
