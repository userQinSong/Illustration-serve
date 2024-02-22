package alibaba.illustration.service.ill;


import alibaba.illustration.entity.ill.pageParams.analysis.AnalysisParam;
import alibaba.illustration.entity.ill.vo.analysis.ChartVo;
import alibaba.illustration.entity.ill.vo.analysis.TotalCalculationVo;

import java.util.List;


public interface AnalysisService {
    List<ChartVo> getAnalysisListFromParam(AnalysisParam param);
    TotalCalculationVo getWebsiteTotalCalPack();
}
