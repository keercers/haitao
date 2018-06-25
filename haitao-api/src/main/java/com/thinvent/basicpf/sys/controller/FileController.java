package com.thinvent.basicpf.sys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.thinvent.library.dim.TvtExceptionEnum;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.util.FastDFSClientUtils;
import com.thinvent.library.util.UploadFileUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping(value = "/file")
public class FileController {
	
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "人员--人员列表导出", notes = "导出部门人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filename", required = false, value = "文件", dataType = "string", paramType = "query")
    })
    @ResponseBody
    public String uploadImage(String part, @RequestParam("file") MultipartFile file) throws ThinventBaseException, IOException {
        try {
            if (StringUtils.isNotEmpty(part)) {
                return UploadFileUtil.storeAsDateFormatByPart(file, part);
            } else {
                Path path = UploadFileUtil.store(file);
                return path.toString();
            }
        } catch (Exception e) {
            log.error("upLoadImage error: ", e);
            throw new ThinventBaseException(TvtExceptionEnum.FILE_STORE_FAULSE.getIndex(), TvtExceptionEnum.FILE_STORE_FAULSE.getName());
        }
    }
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ApiOperation(value = "文件--文件上传到FastDFS", notes = "文件上传到FastDFS")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filename", required = false, value = "文件", dataType = "string", paramType = "query")
    })
    @ResponseBody
    public String uploadFile(String part, @RequestParam("file") MultipartFile uploadFile) throws ThinventBaseException, IOException {
    	if (uploadFile != null) {
            String oName = uploadFile.getOriginalFilename();
            String extName = oName.substring(oName.indexOf('.') + 1);
            HashMap<String, Object> map = new HashMap<>();
            try {
                String uploadUrl = FastDFSClientUtils.upload(uploadFile.getBytes(), extName);
                map.put("success", "上传成功");
                map.put("url", "" + uploadUrl);

            } catch (IOException e) {
            	log.error("upLoadFile error: ", e);
                map.put("error", 1);
                map.put("message", "上传失败");
            }
            return JSON.toJSONString(map);
    	}
    	return "";
    }
    
    @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
    @ApiOperation(value = "文件--文件上传到FastDFS", notes = "文件上传到FastDFS")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filename", required = false, value = "文件", dataType = "string", paramType = "query")
    })
    @ResponseBody
    public String uploadFiles(@RequestParam("file") MultipartFile[] multipartfiles) throws ThinventBaseException, IOException {
    	if(null != multipartfiles && multipartfiles.length > 0){
    		List<String> urlList = Lists.newArrayList();
    		HashMap<String, Object> map = new HashMap<>();
            //遍历并保存文件  
            for(MultipartFile uploadFile : multipartfiles){
            	String oName = uploadFile.getOriginalFilename();
                String extName = oName.substring(oName.indexOf('.') + 1);
                String uploadUrl = FastDFSClientUtils.upload(uploadFile.getBytes(), extName);
                if(uploadUrl != null){
                	urlList.add(uploadUrl);
                }
            }
            if (urlList.size() == multipartfiles.length) {
            	 map.put("success", "上传成功");
                 map.put("url", urlList);
            } else {
            	map.put("error", 1);
                map.put("message", "上传失败");
            }
            return JSON.toJSONString(map);
        }  
    	return "";
    }
    
    @GetMapping(value = "/deleteFile")
    @ApiOperation(value = "文件--文件从FastDFS删除", notes = "文件--文件从FastDFS删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", required = false, value = "url", dataType = "string", paramType = "query")
    })
    @ResponseBody
    public String deleteFile(String fileId) throws ThinventBaseException, IOException {
    	if (fileId != null) {
    		HashMap<String, Object> map = new HashMap<>();
            try {
                FastDFSClientUtils.delete(null, fileId);
                map.put("success", "删除成功");

            } catch (Exception e) {
            	log.error("deleteFile error: ", e);
                map.put("error", 1);
                map.put("message", "删除失败");
            }
            return JSON.toJSONString(map);
    	}
    	return "";
    }

    @GetMapping(value = "/deleteFiles")
    @ApiOperation(value = "文件--文件从FastDFS删除", notes = "文件--文件从FastDFS删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileIds", required = false, value = "urls", dataType = "string", paramType = "query")
    })
    @ResponseBody
    public String deleteFiles(String fileIds) throws ThinventBaseException, IOException {
    	JSONArray fileIdList = JSON.parseArray(fileIds);
    	if(null != fileIdList && fileIdList.size() > 0){
    		List<String> remList = Lists.newArrayList();
    		HashMap<String, Object> map = new HashMap<>();
            //遍历并删除文件  
            for(Object fileId : fileIdList){
               int rtMessage = FastDFSClientUtils.delete(null, fileId.toString());
                if(rtMessage == 0){
                	remList.add(Integer.toString(rtMessage));
                }
            }
            if (remList.size() == fileIdList.size()) {
            	 map.put("success", "删除成功");
                 map.put("url", remList);
            } else {
            	map.put("error", 1);
                map.put("message", "删除失败");
            }
            return JSON.toJSONString(map);
        }  
    	return "";
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\file\\test1.xls");
        InputStream inputStream = new FileInputStream(file);
        HSSFWorkbook workBook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workBook.getSheetAt(0);
        sheet.getRow(0).getCell(0);
        workBook.close();
    }

}
