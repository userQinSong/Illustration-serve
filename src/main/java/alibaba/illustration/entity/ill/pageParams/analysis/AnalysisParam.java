package alibaba.illustration.entity.ill.pageParams.analysis;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AnalysisParam extends AbsAnalysisParam{
    private String search_type;
    private String group_by;
    private String sql_begin_date;
    private String sql_end_date;
    public void init(){
        this.sql_begin_date = super.getBeginTime();
        this.sql_end_date = super.getEndTime();
    }
}
