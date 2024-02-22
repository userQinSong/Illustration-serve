package alibaba.illustration.entity.ill.factory.analysis_factory.impl;

import alibaba.illustration.entity.ill.factory.analysis_factory.AnalysisFactory;
import alibaba.illustration.entity.ill.pageParams.analysis.AnalysisParam;
import alibaba.illustration.entity.ill.vo.analysis.ChartVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAnalysisFactory extends AnalysisFactory {

    @Override
    protected List<ChartVo> queryListGroupByOther(AnalysisParam param) {
        if(param.getGroup_by().equals("pic")) return super.dao.queryAllPicLikeCountGroupByPicType(param);
        else if(param.getGroup_by().equals("picolt")) return super.dao.queryAllPicoltLikeCountGroupByPicoltTag(param);
        else return null;
    }
}
