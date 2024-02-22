package alibaba.illustration.controller.api;

import alibaba.illustration.common.Result;
import alibaba.illustration.common.ResultUtil;
import alibaba.illustration.common.tool.UploadUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;


@Slf4j
@RestController
@Api(value = "upload", tags = "上传接口")
@RequestMapping("/api/upload")
public class UploadApi {

    @PostMapping("/image")
    public Result<String> uploadImage(@RequestParam(value = "file") MultipartFile multipartFile){
        if (multipartFile.isEmpty()){
            return ResultUtil.error(888,"文件有误！");
        }
        return ResultUtil.success(UploadUtils.uploadImage(multipartFile));
    }

    @PostMapping("/images")
    public Result<List<String>> uploadImages(@RequestParam(value = "files") MultipartFile[] multipartFileList){
        if (multipartFileList.length == 0){
            return ResultUtil.error(888,"空文件！");
        }
        List<String> uploadUrlList = UploadUtils.uploadImages(multipartFileList);
        if(uploadUrlList != null && uploadUrlList.size() > 0){
            return ResultUtil.success(uploadUrlList);
        }else
            return ResultUtil.error(888,"上传图片错误");

    }


    @GetMapping("/getUploadImg/{proxy}")
    public void getUploadImg(@PathVariable String proxy, HttpServletResponse response) throws Exception {
        String url = new String(Base64.getDecoder().decode(proxy), StandardCharsets.UTF_8);
        ResponseEntity<byte[]> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, null, byte[].class);
        //获取entity中的数据
        byte[] body = responseEntity.getBody();
        //创建输出流  输出到本地
        OutputStream os = response.getOutputStream();
        os.write(body);
        os.flush();
        //关闭流
        os.close();
    }

}
