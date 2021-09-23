package com.api.controller;

import com.api.domain.ResultResponse;
import com.api.domain.bean.GzFollow;
import com.api.service.GzFollowService;
import com.api.service.daylyService.DaylyService;
import com.api.util.DateUtil;
import com.api.util.ResponseUtil;
import com.api.util.ShareExcelEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李显琪 on 2021/2/12.
 */

@RestController
@RequestMapping("/file")
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private GzFollowService gzFollowService;


    @PostMapping("/upload")
    public ResultResponse upload(@RequestParam(value = "file") MultipartFile file) {

        //获取文件名
        String originalFileName = file.getOriginalFilename();
        //获取后缀
        String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));
        InputStream is = null;
        Workbook wb = null;
        // 读取excel文件
        String errorMessage = "";
        if (file.isEmpty()) {
            errorMessage = "您导入的excel为空，请填入值后重新导入";
            return ResponseUtil.getErrorWebResponse(errorMessage);
        } else try {
            is = file.getInputStream();
            if (fileType.equals(".xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals(".xlsx")) {
                wb = new XSSFWorkbook(is);
            }
            //读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);
            Row firstRow = sheet.getRow(0);
            int cellNum = firstRow.getPhysicalNumberOfCells();
            //确定所需要数据所在的列
            Map<String, Integer> cellIndexMap = new HashMap<>();
            for (int i = 0; i < cellNum; i++) {
                String cellName = firstRow.getCell(i).toString();
                String code = ShareExcelEnum.getCode(cellName);
                if (!StringUtils.isEmpty(code)) {
                    cellIndexMap.put(code, i);
                }
            }
            //遍历sheet页中的行信息放入List中
            List<GzFollow> gzFollowList = new ArrayList<>();
            for (Row row : sheet) {
                GzFollow record = getRowRecode(row, cellIndexMap);
                if (null != record) {
                    gzFollowList.add(record);
                }
            }
            gzFollowService.compareAndInsert(gzFollowList);
            return ResponseUtil.getSuccessWebResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseUtil.getErrorWebResponse(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private GzFollow getRowRecode(Row row, Map<String, Integer> cellIndexMap) {
        if (null == row.getCell(cellIndexMap.get("industry")) || row.getRowNum() == 0) {
            return null;
        }
        GzFollow gzFollow = new GzFollow();
        gzFollow.setSymbol(row.getCell(cellIndexMap.get("symbol")).getStringCellValue());
        gzFollow.setName(row.getCell(cellIndexMap.get("name")).getStringCellValue());
        gzFollow.setIndustry(row.getCell(cellIndexMap.get("industry")).getStringCellValue());
        gzFollow.setDate(DateUtil.getDay());
        return gzFollow;

    }

}
