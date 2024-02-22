package alibaba.illustration.controller.api;

import alibaba.illustration.common.Result;
import alibaba.illustration.common.ResultUtil;
import alibaba.illustration.common.tool.StringToolHelper;
import alibaba.illustration.entity.ill.entity.Comment;
import alibaba.illustration.entity.ill.pageParams.comment.CommentListParam;
import alibaba.illustration.entity.ill.pageResult.CommentPageResult;
import alibaba.illustration.entity.ill.vo.CommentVo;
import alibaba.illustration.service.ill.CommentService;
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
@Api(value = "comment", tags = "评论列表接口")
@RequestMapping("/api/comment")
public class CommentApi {
    @Resource
    private CommentService commentService;

    @GetMapping("/getComments")
    @ApiOperation(value = "获取某(对象)插画集评论", notes = "按blog_id搜索")
    @ResponseBody
    public Result<CommentPageResult> getComments(
            @RequestParam(value = "commentParam",required = false,defaultValue = "") String commentParam
    ) {
        CommentListParam param = JSONObject.parseObject(commentParam,CommentListParam.class);
        PageInfo<CommentPageResult> commentList = commentService.getCommentList(param);
        if(commentList != null){
            return ResultUtil.success(commentList);
        }else{
            return ResultUtil.error(888,"获取插画集讨论列表失败！");
        }
    }

    @GetMapping("/getAllCommentsInCurrentPage")
    @ApiOperation(value = "获取某(对象)插画集评论", notes = "按blog_id搜索")
    @ResponseBody
    public Result<CommentPageResult> getAllCommentsInCurrentPage(
            @RequestParam(value = "commentParam",required = false,defaultValue = "") String commentParam
    ) {
        CommentListParam param = JSONObject.parseObject(commentParam,CommentListParam.class);
        List<CommentVo> list = commentService.queryAllCommentsInCurrentPage(param);
        if(list != null){
            return ResultUtil.success(list);
        }else{
            return ResultUtil.error(888,"获取当前页数的插画集讨论列表失败！");
        }
    }




    @PostMapping("/sendComment")
    @ApiOperation(value = "发送一条评论", notes = "需要用户登录")
    @ResponseBody
    public Result<String> sendComment(
            @RequestBody Comment comment
    ) {
        System.out.println("接受：" + comment.getBlogId());
        if(!StringToolHelper.checkString(comment.getUserId())){
            return ResultUtil.error(888,"当前用户未登录！无法发送评论");
        }else{
            commentService.addComment(comment);
            return ResultUtil.success("发送评论成功！");
        }
    }


    @PostMapping("/removeComment")
    @ApiOperation(value = "发送一条评论", notes = "需要用户登录")
    @ResponseBody
    public Result<String> removeComment(
            @RequestBody CommentVo comment
    ) {
        System.out.println("删除接受：" + comment.getId());
        Boolean isDelete = commentService.removeComment(comment);
        if(isDelete)
            return ResultUtil.success("删除成功");
        else
            return ResultUtil.error(888,"删除失败！");
    }

    @PostMapping("/changeAttitude")
    @ApiOperation(value = "修改用户对某评论的态度", notes = "需要用户登录")
    @ResponseBody
    public Result<CommentVo> changeAttitude(
            @RequestBody CommentVo comment
    ) {

        if(!StringToolHelper.checkString(comment.getUserId())){
            return ResultUtil.error(888,"当前用户未登录！无法发表对评论的态度");
        }else{
            return ResultUtil.success( commentService.changeAttitude(comment));
        }
    }

    @GetMapping("/getCommentById")
    @ApiOperation(value = "获取某(对象)插画集评论", notes = "按blog_id搜索")
    @ResponseBody
    public Result<CommentVo> getCommentById(
            @RequestParam(value = "id",required = false,defaultValue = "") Long id
    ) {
        CommentVo comment = commentService.getCommentById(id);
        if(comment != null){
            return ResultUtil.success(comment);
        }else{
            return ResultUtil.error(888,"获取评论失败！");
        }
    }

    @GetMapping("/getCommentTotalCount")
    @ApiOperation(value = "获取某(对象)总评论数", notes = "按blog_id搜索")
    @ResponseBody
    public Result<CommentVo> getCommentTotalCount(
            @RequestParam(value = "blog_id",required = false,defaultValue = "") String blog_id
    ) {
        Long count = commentService.getCommentTotalCount(blog_id);
        if(count != null){
            return ResultUtil.success(count);
        }else{
            return ResultUtil.error(888,"获取评论总数失败！");
        }
    }
}
