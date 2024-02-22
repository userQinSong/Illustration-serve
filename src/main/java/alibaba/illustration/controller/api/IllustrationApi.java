package alibaba.illustration.controller.api;

import alibaba.illustration.common.Result;
import alibaba.illustration.common.ResultUtil;
import alibaba.illustration.common.tool.StringToolHelper;
import alibaba.illustration.entity.ill.entity.Picolt;
import alibaba.illustration.entity.ill.pageParams.pic.PicDetailParam;
import alibaba.illustration.entity.ill.pageParams.pic.PicParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltDetailParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltListParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltUploadParam;
import alibaba.illustration.entity.ill.pageResult.PicPageResult;
import alibaba.illustration.entity.ill.pageResult.PicoltPageResult;
import alibaba.illustration.entity.ill.vo.PicVo;
import alibaba.illustration.service.ill.GetIllustrationService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@Api(value = "illustration", tags = "插画交流平台接口")
@RequestMapping("/api/picolt")
public class IllustrationApi {
    @Resource
    private GetIllustrationService getIllustrationService;

    @GetMapping("/getPicolts")
    @ApiOperation(value = "获取数据库中的插画集列表", notes = "按条件搜索")
    @ResponseBody
    public Result<PageInfo<Picolt>> getPicolts(
            @RequestParam(value = "picoltParam",required = false,defaultValue = "") String picoltParam
    ) {
        PicoltListParam param = JSONObject.parseObject(picoltParam,PicoltListParam.class);
        PageInfo<PicoltPageResult> pageInfo = getIllustrationService.getPicoltList(param);
        if(pageInfo != null){
            return ResultUtil.success(pageInfo);
        }else{
            return ResultUtil.error(888,"获取插画集失败");
        }
    }

    @GetMapping("/getPicoltDetail")
    @ApiOperation(value = "获取数据库中某插画集的详情信息", notes = "按条件搜索")
    @ResponseBody
    public Result<Picolt> getPicoltDetail(
            @RequestParam(value = "picoltDetailParam",required = false,defaultValue = "") String picoltDetailParam
    ) {
        PicoltDetailParam param = JSONObject.parseObject(picoltDetailParam,PicoltDetailParam.class);
        PicoltPageResult picolt = getIllustrationService.getPicolt(param);
        if(picolt != null){
            return ResultUtil.success(picolt);
        }else{
            return ResultUtil.error(888,"获取插画集失败");
        }
    }

    @GetMapping("/getPicListByUserId")
    @ApiOperation(value = "获取数据库中某插画集的插画列表", notes = "按user_id搜索")
    @ResponseBody
    public Result<PageInfo<PicVo>> getPicListByUserId(
            @RequestParam(value = "picParam",required = false,defaultValue = "") String picParam
    ) {
        PicParam param = JSONObject.parseObject(picParam,PicParam.class);
        PageInfo<PicPageResult> page = null;
        if(StringToolHelper.checkString(param.getUser_id())){
            if(param.getSearch_like() == 1){
                page = getIllustrationService.getPicListUserLiked(param);
            }else if(param.getSearch_upload() == 1){
                page = getIllustrationService.getPicListUserCreated(param);
            }
        }
        if(page != null){
            return ResultUtil.success(page);
        }else{
            return ResultUtil.error(888,"获取插画列表失败");
        }
    }

    @GetMapping("/getPicListByPicoltId")
    @ApiOperation(value = "获取数据库中某插画集的插画列表", notes = "按picolt_id搜索")
    @ResponseBody
    public Result<PageInfo<List<PicVo>>> getPicListByPicoltId(
            @RequestParam(value = "picParam",required = false,defaultValue = "") String picParam
    ) {
        PicParam param = JSONObject.parseObject(picParam,PicParam.class);
        List<PicVo> picList = getIllustrationService.getPicListByPicoltId(param);
        if(picList != null){
            return ResultUtil.success(picList);
        }else{
            return ResultUtil.error(888,"获取插画列表失败");
        }
    }

    @GetMapping("/changePicoltAttitude")
    @ApiOperation(value = "更改当前登录用户对某图集的态度", notes = "按user_id,attitude_person_id,change_type搜索")
    @ResponseBody
    public Result<Boolean> changePicoltAttitude(
            @RequestParam(value = "picoltDetailParam",required = false,defaultValue = "") String picoltDetailParam
    ) {
        PicoltDetailParam param = JSONObject.parseObject(picoltDetailParam,PicoltDetailParam.class);
        if(StringToolHelper.checkString(param.getAttitude_person_id())){
            boolean is_change = getIllustrationService.changeLoginUserPicoltAttitude(param);
            return ResultUtil.success(is_change);
        }else
            return ResultUtil.error(888,"当前用户未登录无法对图集发表态度");
    }

    @GetMapping("/changePicAttitude")
    @ApiOperation(value = "更改当前登录用户对某图片的态度", notes = "按user_id,attitude_person_id,change_type搜索")
    @ResponseBody
    public Result<Boolean> changePicAttitude(
            @RequestParam(value = "picDetailParam",required = false,defaultValue = "") String picDetailParam
    ) {
        PicDetailParam param = JSONObject.parseObject(picDetailParam, PicDetailParam.class);
        if(StringToolHelper.checkString(param.getAttitude_person_id())){
            boolean is_change = getIllustrationService.changeLoginUserPicAttitude(param);
            return ResultUtil.success(is_change);
        }else
            return ResultUtil.error(888,"当前用户未登录无法对图集发表态度");
    }

    @GetMapping("/uploadPicolt")
    @ApiOperation(value = "更改当前登录用户对某图集的态度", notes = "按user_id,attitude_person_id,change_type搜索")
    @ResponseBody
    public Result<Boolean> uploadPicolt(
            @RequestParam(value = "picoltUploadParam",required = false,defaultValue = "") String picoltUploadParam
    ) {
        PicoltUploadParam param = JSONObject.parseObject(picoltUploadParam, PicoltUploadParam.class);
        if(StringToolHelper.checkString(param.getUser_id())){
            boolean is_upload = getIllustrationService.uploadPicolt(param);
            return ResultUtil.success(is_upload);
        }else
            return ResultUtil.error(888,"当前用户未登录无法对图集发表态度");
    }
}
