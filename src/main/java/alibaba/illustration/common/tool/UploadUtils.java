package alibaba.illustration.common.tool;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;
 
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
 
/**
 * 上传图片工具类
 */
public class UploadUtils {
    public static final String rootPath = "F:\\upload\\illustration";
    public static final String handlerPath="/upload/";
    public static String uploadImage(MultipartFile multipartFile){
        try {
            String newFilename = getNewFileName(multipartFile);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = dateFormat.format(new Date());
            //上传到哪个路径下
            multipartFile.transferTo(getTargetFile(rootPath,datePath,newFilename));
            //组装url并存放于结果集中
//            return String.valueOf(targetFilename);//返回文件路径
            return handlerPath+datePath+"/"+newFilename;//资源映射路径
        } catch (IOException e) {
            e.printStackTrace();
            return "失败！";
        }
    }


    public static List<String> uploadImages(MultipartFile[] list){
        List<String> resList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String datePath = dateFormat.format(new Date());
        for(MultipartFile file:list){
            try {
                String newFilename = getNewFileName(file);
                //上传到哪个路径下
                file.transferTo(getTargetFile(rootPath,datePath,newFilename));
                //组装url并存放于结果集中
                resList.add(handlerPath + datePath + "/" + newFilename);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return resList;
    }

    public static File getTargetFile(String rootPath, String datePath, String fileName){
        //上传到哪个路径下
        File targetPath = new File(rootPath,datePath);
        if (!targetPath.exists()){
            targetPath.mkdirs();
        }
        File targetFile = new File(targetPath, fileName);
        return targetFile;
    }

    public static String getNewFileName(MultipartFile file){
        String realfilename = file.getOriginalFilename();
        String imgSuffix = realfilename.substring(realfilename.lastIndexOf("."));
        return UUID.randomUUID() +imgSuffix;
    }
 
}