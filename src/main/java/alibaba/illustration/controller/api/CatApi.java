package alibaba.illustration.controller.api;

import alibaba.illustration.common.Result;
import alibaba.illustration.common.ResultUtil;
import alibaba.illustration.service.ill.CatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@Api(value = "cat", tags = "标识分类（标签，类型等）")
@RequestMapping("/api/cat")
public class CatApi {
    @Resource
    private CatService catService;

    @GetMapping("/getTagList")
    @ApiOperation(value = "获取某插画集评论", notes = "按blog_id搜索")
    @ResponseBody
    public Result<List<String>> getTagList() {
        List<String> tags = catService.queryAllTags();
        if(tags != null){
            return ResultUtil.success(tags);
        }else{
            return ResultUtil.error(888,"获取标签列表失败");
        }
    }

    @GetMapping("/getTypeList")
    @ApiOperation(value = "获取某插画集评论", notes = "按blog_id搜索")
    @ResponseBody
    public Result<List<String>> getTypeList() {
        List<String> types = catService.queryAllTypes();
        if(types != null){
            return ResultUtil.success(types);
        }else{
            return ResultUtil.error(888,"获取类型列表失败");
        }
    }
}
