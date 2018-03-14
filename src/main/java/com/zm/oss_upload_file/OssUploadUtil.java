package com.zm.oss_upload_file;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: zhoumin
 **/
public class OssUploadUtil {
    //log日志
    private static Logger log = Logger.getLogger(OssUploadUtil.class);
    //阿里云API的内或外网域名
    private static String ENDPOINT;
    //阿里云API的密钥Access Key ID
    private static String ACCESS_KEY_ID;
    //阿里云API的密钥Access Key Secret
    private static String ACCESS_KEY_SECRET;
    //阿里云API的bucket名称
    private static String BACKET_NAME;
    //阿里云API的文件夹名称
//    private static String FOLDER;
    //初始化属性
    static{
        ENDPOINT = OSSClientConstants.ENDPOINT;
        ACCESS_KEY_ID = OSSClientConstants.ACCESS_KEY_ID;
        ACCESS_KEY_SECRET = OSSClientConstants.ACCESS_KEY_SECRET;
        BACKET_NAME = OSSClientConstants.BACKET_NAME;
//        FOLDER = OSSClientConstants.FOLDER;
    }

    private OSSClient ossClient;
    public OssUploadUtil() {
        ossClient = new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    //文件上传
    public String upload(InputStream inputStream, String fileName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer key = new StringBuffer("data/");
        key.append(sdf.format(new Date()))
                .append("/").append(System.nanoTime()).append("-").append(fileName);
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            ossClient.putObject(BACKET_NAME, key.toString(), inputStream, objectMetadata);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getUrlByKey(key.toString());
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }else if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }else if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }else if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }else if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }else if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }else if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")){
            return "application/vnd.ms-powerpoint";
        }else if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }else if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }else{
            return "application/octet-stream";
        }
    }

    public String getUrlByKey(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(BACKET_NAME, key, expiration);
        return url == null? null:url.toString();
    }



    public String mutipartupload(MultipartFile localFile,String fileName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer key = new StringBuffer("data/");
        key.append(sdf.format(new Date()))
                .append("/").append(System.nanoTime()).append("-").append(fileName);
        long partSize = 5 * 1024 * 1024L;
        long fileLength = localFile.getSize();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }

        InputStream instream = null;
        try {
            InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(BACKET_NAME, key.toString());
            InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
            String uploadId =  result.getUploadId();

            List<PartETag> partETags = Collections.synchronizedList(new ArrayList<>());
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                // 获取文件流
                instream = localFile.getInputStream();
                // 跳到每个分块的开头
                instream.skip(startPos);

                // 创建UploadPartRequest，上传分块
                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(BACKET_NAME);
                uploadPartRequest.setKey(key.toString());
                uploadPartRequest.setUploadId(uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(partSize);
                uploadPartRequest.setPartNumber(i+1);

                UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);

                synchronized (partETags) {
                    // 将返回的PartETag保存到List中。
                    partETags.add(uploadPartResult.getPartETag());
                }
            }


            Collections.sort(partETags, new Comparator<PartETag>() {
                @Override
                public int compare(PartETag p1, PartETag p2) {
                    return p1.getPartNumber() - p2.getPartNumber();
                }
            });

            CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(BACKET_NAME,
                    key.toString(), uploadId, partETags);
            // 完成分块上传
            ossClient.completeMultipartUpload(completeMultipartUploadRequest);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (instream != null) {
                try {
                    // 关闭文件流
                    instream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "oss-cn-shanghai.aliyuncs.com" + "/" + BACKET_NAME + "/" + ossClient.getObject(BACKET_NAME, key.toString()).getKey();
    }
}
