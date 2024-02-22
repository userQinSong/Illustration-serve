package alibaba.illustration.dao.ill;

import alibaba.illustration.entity.ill.pageParams.analysis.AnalysisParam;
import alibaba.illustration.entity.ill.vo.analysis.ChartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnalysisDao {
    //统计按年 根据（create_time）
    List<ChartVo> queryDataGroupByYear(AnalysisParam param);
    //统计按小时 根据（create_time）
    List<ChartVo> queryDataGroupByHourTime(AnalysisParam param);
    //获取所有图片按图片被喜欢的次数并按插画类型分类统计
    List<ChartVo> queryAllPicLikeCountGroupByPicType(AnalysisParam param);

    List<ChartVo>  queryAllPicoltLikeCountGroupByPicoltTag(AnalysisParam param);

    Long sumWebsiteCount(String which,String type);
}
