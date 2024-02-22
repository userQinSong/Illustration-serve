package alibaba.illustration.entity.dataAnalysis;


import lombok.Data;

@Data
public class AnalysisData{
    private String name;
    private Double value;

    public AnalysisData() {
    }

    public AnalysisData(AnalysisData data) {
        this.name = data.name;
        this.value = data.value;
    }
}
