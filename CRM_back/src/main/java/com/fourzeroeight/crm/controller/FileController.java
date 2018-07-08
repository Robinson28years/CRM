package com.fourzeroeight.crm.controller;

import com.fourzeroeight.crm.bean.Message;
import com.fourzeroeight.crm.utils.CommonUtil;
import com.fourzeroeight.crm.utils.DownloadUtil;
import com.fourzeroeight.crm.utils.UploadUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by lzp on 2017/6/18.
 */
@RestController
public class FileController {

    @CrossOrigin
    @RequestMapping(value = "/api/public/upload/{dirname}")
    public Message upload(@RequestParam MultipartFile file, @PathVariable(name = "dirname") String save) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (file.isEmpty()) {
            return new Message(0,"文件为空",null);
        }else{
            Map map = UploadUtil.singleFileUpload(file,save, CommonUtil.getIpAddr(request));
            if((Boolean) map.get("status")){
                return new Message(1,"保存成功", "http://127.0.0.1:8080/public/download/"+ save + "?filename="+map.get("path"));
            }else{
                return new Message(0,"保存失败",null);
            }
        }
    }

    @RequestMapping(value = "/api/public/download/{dirname}")
    public void chatdownFile(
            @RequestParam(value = "filename") String filename,@PathVariable(name = "dirname") String save,
            HttpServletResponse response,
            HttpServletRequest request) {
        DownloadUtil.down(filename,save,request,response);

    }



}
