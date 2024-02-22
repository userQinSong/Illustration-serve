package alibaba.illustration.controller.api;

import alibaba.illustration.common.Result;
import alibaba.illustration.common.ResultUtil;
import alibaba.illustration.common.tool.StringToolHelper;
import alibaba.illustration.entity.ill.entity.User;
import alibaba.illustration.entity.ill.pageParams.user.UserParam;
import alibaba.illustration.entity.ill.pageResult.CommentPageResult;
import alibaba.illustration.entity.ill.pageResult.UserPageResult;
import alibaba.illustration.service.ill.UserService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Slf4j
@RestController
@Api(value = "person", tags = "个人信息接口")
@RequestMapping("/api/person")
public class UserApi {
    @Resource
    private UserService userService;

    @GetMapping("/getPersonInfo")
    @ApiOperation(value = "获取某用户个人信息", notes = "按user_id搜索")
    @ResponseBody
    public Result<CommentPageResult> getPersonInfo(
            @RequestParam(value = "user_id",required = false,defaultValue = "") String user_id
    ) {
        User user = userService.getUserInfo(user_id);
        if(user != null){
            return ResultUtil.success(user);
        }else{
            return ResultUtil.error(888,"获取用户信息失败！");
        }
    }

    @GetMapping("/getConcernPersonListByUserId")
    @ApiOperation(value = "获取有关系的人列表", notes = "按search_user_type分成找粉丝还是找关注")
    @ResponseBody
    public Result<PageInfo<User>> getConcernPersonListByUserId(
            @RequestParam(value = "userParam",required = false,defaultValue = "") String userParam
    ) {
        UserParam param = JSONObject.parseObject(userParam, UserParam.class);
        if(StringToolHelper.checkString(param.getAttitude_person_id())){
            PageInfo<UserPageResult> pageInfo = userService.getAllConcernPersonList(param);
            if(pageInfo != null){
                return ResultUtil.success(pageInfo);
            }else{
                return ResultUtil.error(888,"获取关系人信息");
            }
        }else
            return ResultUtil.error(888,"当前用户未登录，获取关注人列表失败！");
    }

    @GetMapping("/logByPhoneNumber")
    @ApiOperation(value = "登录注册用户", notes = "判断手机号是否存在，有则登录，无则重新注册")
    @ResponseBody
    public Result<User> logByPhoneNumber(
            @RequestParam(value = "phone_number",required = false,defaultValue = "") String phone_number
    ) {
        User user = userService.logByPhoneNumber(phone_number);
        if(user != null){
            return ResultUtil.success(user);
        }else{
            return ResultUtil.error(888,"获取登录用户信息失败");
        }
    }

    @GetMapping("/changeAttentionOnePerson")
    @ApiOperation(value = "修改某用户关注信息", notes = "根据user_id,和attitude_person_id")
    @ResponseBody
    public Result<String> changeAttentionOnePerson(
            @RequestParam(value = "userParam",required = false,defaultValue = "") String userParam
    ) {
        UserParam param = JSONObject.parseObject(userParam,UserParam.class);
        if(StringToolHelper.checkString(param.getAttitude_person_id())){
            boolean isAttention = userService.changeAttentionOnePerson(param);
            if(isAttention){
                return ResultUtil.success("关注成功！");
            }else{
                return ResultUtil.error(888,"关注失败！");
            }
        }else
            return ResultUtil.error(888,"当前用户未登录，获取关注信息失败！");
    }

    @GetMapping("/getAttentionOnePerson")
    @ApiOperation(value = "获取某用户和当前登录用户的关注信息", notes = "根据user_id,和attitude_person_id")
    @ResponseBody
    public Result<Boolean> getAttentionOnePerson(
            @RequestParam(value = "userParam",required = false,defaultValue = "") String userParam
    ) {
        UserParam param = JSONObject.parseObject(userParam,UserParam.class);
        if(StringToolHelper.checkString(param.getAttitude_person_id())){
            boolean isAttention = userService.getAttentionOnePerson(param);
            return ResultUtil.success(isAttention);
        }else
            return ResultUtil.error(888,"当前用户未登录，获取关注信息失败！");
    }

    @GetMapping("/editPersonalMsg")
    @ApiOperation(value = "编辑个人信息", notes = "根据user_id,和edited_username,edited_brief，返回一个boolean判断是否编辑成功")
    @ResponseBody
    public Result<Boolean> editPersonalMsg(
            @RequestParam(value = "userParam",required = false,defaultValue = "") String userParam
    ) {
        UserParam param = JSONObject.parseObject(userParam,UserParam.class);
        if(StringToolHelper.checkString(param.getUser_id())){
            boolean isEdited = userService.editPersonalMsg(param);
            return ResultUtil.success(isEdited);
        }else
            return ResultUtil.error(888,"当前用户未登录，获取关注信息失败！");
    }

    @GetMapping("/pushRemainTime")
    @ApiOperation(value = "更新浏览时间", notes = "根据user_id,和remain_time")
    @ResponseBody
    public Result<Boolean> pushRemainTime(
            @RequestParam(value = "userParam",required = false,defaultValue = "") String userParam
    ) {
        UserParam param = JSONObject.parseObject(userParam,UserParam.class);
        if(StringToolHelper.checkString(param.getUser_id())){
            boolean isEdited = userService.pushRemainTime(param);
            return ResultUtil.success(isEdited);
        }else
            return ResultUtil.error(888,"当前用户未登录，获取关注信息失败！");
    }
}
