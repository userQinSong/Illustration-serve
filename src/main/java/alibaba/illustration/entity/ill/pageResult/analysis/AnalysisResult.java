package alibaba.illustration.entity.ill.pageResult.analysis;

import alibaba.illustration.entity.ill.vo.analysis.ChartVo;
import lombok.Data;

import java.util.List;

@Data
public class AnalysisResult {
    private List<ChartVo> lineChartVos;
    private List<ChartVo> pieChartVos;
}
