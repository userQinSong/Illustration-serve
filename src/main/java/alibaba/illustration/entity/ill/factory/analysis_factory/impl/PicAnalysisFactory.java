package alibaba.illustration.entity.ill.factory.analysis_factory.impl;

import alibaba.illustration.entity.ill.factory.analysis_factory.AnalysisFactory;
import alibaba.illustration.entity.ill.pageParams.analysis.AnalysisParam;
import alibaba.illustration.entity.ill.vo.analysis.ChartVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PicAnalysisFactory extends AnalysisFactory {

    @Override
    protected List<ChartVo> queryListGroupByOther(AnalysisParam param) {
        return null;
    }
}
