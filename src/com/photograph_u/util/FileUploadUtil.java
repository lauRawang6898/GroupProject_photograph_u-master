package com.photograph_u.util;

import com.photograph_u.exception.MyFileUploadException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUploadUtil {
    public static void upload(HttpServletRequest request, String savePath, int fieldCount, int fileCount) {
        Map<String, Object> parameterMap = new HashMap<>();
        List<String> fileList = new ArrayList<>();
        FileOutputStream out = null;
        InputStream in = null;
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new MyFileUploadException("Invalid setting");
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(16 * 1024);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(5 * 1024 * 1024);
        upload.setFileSizeMax(700 * 1024);
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        List<FileItem> itemList;
        try {
            itemList = upload.parseRequest(request);
        } catch (FileUploadException e) {
            throw new MyFileUploadException("File uploading failed", e);
        }
        if (itemList.size() > (fieldCount + fileCount)) {
            throw new MyFileUploadException("Exceed file count");
        }
        for (FileItem item : itemList) {
            if (item.isFormField()) {
                try {
                    parameterMap.put(item.getFieldName(), item.getString("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new MyFileUploadException("Unsupported form", e);
                }
            } else {
                String fileName = item.getName();
                if (!"".equals(fileName)) {
                    String dotExtendName = fileName.substring(fileName.lastIndexOf("."));
                    boolean allow = false;
                    String[] allowType = {".jpg", ".png", ".bmp", ".gif"};
                    for (String type : allowType) {
                        if (type.equals(dotExtendName))
                            allow = true;
                    }
                    if (!allow) {
                        throw new MyFileUploadException("Non support file type");
                    }
                    String saveName = CommUtils.getUuidCode() + dotExtendName;
                    File saveFile = new File(savePath + saveName);
                    try {
                        out = new FileOutputStream(saveFile);
                        in = item.getInputStream();
                        int len;
                        while ((len = in.read()) != -1) {
                            out.write(len);
                        }
                        out.flush();
                        fileList.add(saveName);
                        parameterMap.put("files", fileList);
                    } catch (Exception e) {
                        throw new MyFileUploadException("File uploading exception ", e.getCause());
                    } finally {
                        try {
                            if (out != null) {
                                out.close();
                            }
                            if (in != null) {
                                in.close();
                            }
                        } catch (IOException e) {
                            throw new MyFileUploadException("File uploading exception", e);
                        }
                    }

                }
            }
        }
        request.setAttribute("parameterMap", parameterMap);
    }
}
