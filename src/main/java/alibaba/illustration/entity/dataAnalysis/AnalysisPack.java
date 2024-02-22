package alibaba.illustration.entity.dataAnalysis;


import lombok.Data;

import java.util.List;

@Data
public class AnalysisPack {
    private String title;
    private List<AnalysisData> data;
    private Double totalValue;
}
