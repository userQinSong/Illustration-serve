package alibaba.illustration.controller.api;

import alibaba.illustration.common.Result;
import alibaba.illustration.common.ResultUtil;
import alibaba.illustration.entity.ill.pageParams.analysis.AnalysisParam;
import alibaba.illustration.entity.ill.pageParams.analysis.impl.LineChartAnalysisParam;
import alibaba.illustration.entity.ill.pageParams.analysis.impl.PieChartAnalysisParam;
import alibaba.illustration.entity.ill.pageParams.analysis.impl.SixDemChartAnalysisParam;
import alibaba.illustration.entity.ill.pageResult.analysis.AnalysisResult;
import alibaba.illustration.entity.ill.vo.analysis.TotalCalculationVo;
import alibaba.illustration.service.ill.AnalysisService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@Api(value = "analysis",
        tags = "需要analysisParam: " +
                "参数需要（1）search_year:搜索年份" +
                "参数需要（2）search_type:搜索大类" +
                "参数需要（3）group_by:数据分析依据"
)
@RequestMapping("/api/analysis")
public class AnalysisApi {
    @Resource
    private AnalysisService analysisService;

    @GetMapping("/getPieMsgPack")
    @ApiOperation(value = "获取今年的数据包用来组装饼图", notes = "根据search_type分类查询")
    @ResponseBody
    public Result<Map> getPieMsgPack(
            @RequestParam(value = "analysisParam",required = false,defaultValue = "") String analysisParam) {
        AnalysisParam param = JSONObject.parseObject(analysisParam, PieChartAnalysisParam.class);
        AnalysisResult curYearMsgPack = new AnalysisResult();
        curYearMsgPack.setPieChartVos(analysisService.getAnalysisListFromParam(param));
        //组装前端map
        Map<String,AnalysisResult> resMap = new HashMap<>();
        resMap.put("curYearMsgPack",curYearMsgPack);
        //发送
        return ResultUtil.success(resMap);
    }

    @GetMapping("/getLineMsgPack")
    @ApiOperation(value = "获取今年和过去一年的信息包用来组装折线图", notes = "根据search_type分类查询")
    @ResponseBody
    public Result<Map> getLineMsgPack(
            @RequestParam(value = "analysisParam",required = false,defaultValue = "") String analysisParam) {
        AnalysisParam param = JSONObject.parseObject(analysisParam, LineChartAnalysisParam.class);
        AnalysisResult curYearMsgPack = new AnalysisResult();
        //设置当前年的完整信息包
        curYearMsgPack.setLineChartVos(analysisService.getAnalysisListFromParam(param));
        //param切换至前年
        param = getNearGapFromParam(param,-1);
        //设置前年的部分信息包
        AnalysisResult lastYearMsgPack = new AnalysisResult();
        lastYearMsgPack.setLineChartVos(analysisService.getAnalysisListFromParam(param));
        //组装前端map
        Map<String,AnalysisResult> resMap = new HashMap<>();
        resMap.put("curYearMsgPack",curYearMsgPack);
        resMap.put("lastYearMsgPack",lastYearMsgPack);
        //发送
        return ResultUtil.success(resMap);
    }

    @GetMapping("/getSixDemMsgPack")
    @ApiOperation(value = "获取今年的数据包用来组装六维雷达图", notes = "根据search_type分类查询")
    @ResponseBody
    public Result<Map> getSixDemMsgPack(
            @RequestParam(value = "analysisParam",required = false,defaultValue = "") String analysisParam) {
        AnalysisParam param = JSONObject.parseObject(analysisParam, SixDemChartAnalysisParam.class);
        AnalysisResult curYearMsgPack = new AnalysisResult();
        curYearMsgPack.setPieChartVos(analysisService.getAnalysisListFromParam(param));
        //组装前端map
        Map<String,AnalysisResult> resMap = new HashMap<>();
        resMap.put("curYearMsgPack",curYearMsgPack);
        //发送
        return ResultUtil.success(resMap);
    }


    @GetMapping("/getTotalMsgInfo")
    @ApiOperation(value = "获取今年和过去一年的统计信息包", notes = "根据search_type分类查询")
    @ResponseBody
    public Result<Map> getTotalMsgInfo() {
        TotalCalculationVo totalMsgPack = analysisService.getWebsiteTotalCalPack();
        if(totalMsgPack != null){
            return ResultUtil.success(totalMsgPack);
        }else{
            return ResultUtil.error(888,"获取总数据集失败");
        }
    }


    private static AnalysisParam getNearGapFromParam(AnalysisParam origenParam,int gap_year){
        int origenYear = origenParam.getBegin_year();
        origenParam.setBegin_year(origenYear + gap_year);
        origenParam.setEnd_year(origenYear + gap_year);
        return origenParam;
    }
}
