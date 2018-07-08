package com.fourzeroeight.crm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Key;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileUtil {
    static Key key;
    public static String KEYS;
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    @Value("${com.sxito.custom.keys}")
    public void setWindowsPath(String keys) {
        KEYS = keys;
    }

    public static Key getKey() {
        if( key == null){
            try {
                KeyGenerator _generator = KeyGenerator.getInstance("DES");
                _generator.init(new SecureRandom(KEYS.getBytes()));
                key = _generator.generateKey();
                _generator = null;
            } catch (Exception e) {

            }
        }
        return key;
    }

    public static Map<String, Object> encrypt(MultipartFile multipartFile, String saveDirName, String ip){
        String saveDirName2 = CommonUtil.getUploadFilePath() + "/upload/" + saveDirName + "/" + UploadUtil.getDays();
        File folder = new File(saveDirName2);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", false);
        if (!multipartFile.isEmpty()) {
            Cipher cipher = null;
            OutputStream out = null;
            InputStream is = null;
            CipherInputStream cis = null;
            try {
                cipher = Cipher.getInstance("DES");
                cipher.init(Cipher.ENCRYPT_MODE,getKey());

                is = multipartFile.getInputStream();
                String fileName = multipartFile.getOriginalFilename();
                fileName = UploadUtil.getFileNameToAvoidRepeat(folder, fileName);
                map.put("path", "/" + UploadUtil.getDays() + "/" + fileName);
                fileName = saveDirName2 + "/" + fileName;
                logger.info("ip:{} ;upload-save-Path:{}" ,ip , fileName);
                out = new FileOutputStream(fileName);
                cis = new CipherInputStream(is, cipher);

                byte[] buffer = new byte[1024];
                int r;
                while ((r = cis.read(buffer)) > 0) {
                    out.write(buffer, 0, r);
                }
                map.put("status", true);
            } catch (Exception e) {
                map.put("status", false);
                e.printStackTrace();
            }finally {
                if (cis != null) {
                    try {
                        cis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return map;
    }

    public static void decrypt(String odexName, String saveName,HttpServletResponse response){


        File downloadFile = new File(CommonUtil.getUploadFilePath() + "/upload/" +saveName + odexName);
        long fileLength = downloadFile.length();

        InputStream is = null;
        //CipherOutputStream cos = null;
        OutputStream out = null;
        byte b[] = new byte[1024];
        response.setHeader("Accept-Ranges", "bytes");
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, getKey());
            String fileName = new String(downloadFile.getName().getBytes("utf-8"),"ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType( CommonUtil.setContentType(downloadFile.getName()));
            response.addHeader("Content-Length", String.valueOf(fileLength));
            is = new FileInputStream(downloadFile);

            CipherInputStream cis = new CipherInputStream(is, cipher);
            out = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) >= 0) {
                out.write(buffer, 0, r);
            }
            out.flush();
            out.close();
            cis.close();
            is.close();
//            cos = new CipherOutputStream(response.getOutputStream(), cipher);
//            byte[] buffer = new byte[1024];
//            int n = 0;
//            while ((n = is.read(b, 0, 1024)) != -1) {
//                cos.write(b, 0, n);
//            }
//            cos.flush();
        } catch (Exception e) {

        } finally {
//            if (cos != null) {
//                try {
//                    cos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//
//                }
//            }
        }
    }

}
