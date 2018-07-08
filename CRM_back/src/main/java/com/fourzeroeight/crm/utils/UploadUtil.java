package com.fourzeroeight.crm.utils;

import com.fourzeroeight.crm.bean.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lzp20 on 2017/4/29.
 */

public class UploadUtil {

    private static Logger logger = LoggerFactory.getLogger("upload");

    public static Map<String, Object> singleFileUpload(MultipartFile multipartFile, String saveDirName, String ip) {
        String fileName = null;
        String saveDirName2 = CommonUtil.getUploadFilePath() + "/upload/" + saveDirName + "/" + getDays();
        File folder = new File(saveDirName2);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", false);
        if (!multipartFile.isEmpty()) {
            fileName = multipartFile.getOriginalFilename();
            fileName = getFileNameToAvoidRepeat(folder, fileName);
            map.put("path", "/" + getDays() + "/" + fileName);
            map.put("status", true);
            /** 拼成完整的文件保存路径文件 **/
            fileName = saveDirName2 + "/" + fileName;

            logger.info("ip:{} upload-save-Path:{}",ip,fileName);
            File file = new File(fileName);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException e) {
                map.put("status", false);
                e.printStackTrace();
            } catch (IOException e) {
                map.put("status", false);
                e.printStackTrace();
            }
        }
        return map;

    }
    public static Message singleFileUploadBase64(String base64Data, String saveDirName, String ip) {
        String tempFileName = null;
        String saveDirName2 = CommonUtil.getUploadFilePath() + "/upload/" + saveDirName + "/" + getDays();
        File folder = new File(saveDirName2);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try{
            String dataPrix = "";
            String data = "";
            if(base64Data == null || "".equals(base64Data)){
                return new Message(0,"上传失败，上传图片数据为空",null);
            }else {
                String[] d = base64Data.split("base64,");
                if (d != null && d.length == 2) {
                    dataPrix = d[0];
                    data = d[1];
                } else {
                    return new Message(0, "上传失败，数据不合法", null);
                }
            }
            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                return new Message(0,"上传图片格式不合法",null);
            }
            tempFileName  =  getFileNameToAvoidRepeat(folder, suffix);
            String filename =  saveDirName2 + "/" + tempFileName;
            logger.info("ip:{} upload-save-Path:{}",ip,filename);
            byte[] bs = Base64Utils.decodeFromString(data);
            try{
                FileOutputStream out = new FileOutputStream(filename);
                out.write(bs);
                out.close();
            }catch(Exception ee){
                return new Message(0,"上传失败，写入文件失败",null);
            }
        }catch (Exception e) {
            return new Message(0,"上传失败，未知错误",null);
        }
        return new Message(1,saveDirName + "/" + getDays() + "/" + tempFileName,null);
    }


    /**
     * 避免上传文件将同名文件覆盖，发现同名文件时，将文件名前加上时间戳
     *
     * @param file     :目录
     * @param fileName :上传文件的文件名
     * @return
     */
    public static String getFileNameToAvoidRepeat(File file, String fileName) {
        UUID uuid = UUID.randomUUID();

        fileName = uuid.toString() +"."+ fileName.substring(fileName.lastIndexOf(".")+1);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    if (f.getName().equals(fileName)) {
                        fileName = "(" + getDays() + ")" + fileName;
                        break;
                    }
                }
            }
        }
        return fileName;

    }

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDays() {
        return simpleDateFormat.format(new Date());
    }
}
