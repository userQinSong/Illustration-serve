package alibaba.illustration.entity.ill.pageParams.analysis.impl;

import alibaba.illustration.entity.ill.pageParams.analysis.AnalysisParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PieChartAnalysisParam extends AnalysisParam {
    private int pie_count;
}
