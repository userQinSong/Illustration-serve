package alibaba.illustration.entity.ill.pageParams.analysis;

import lombok.Data;

@Data
public class AbsAnalysisParam {
    protected int begin_year;
    protected int begin_month=1;
    protected int begin_day=1;
    protected int end_year;
    protected int end_month=12;
    protected int end_day=31;

    protected String getBeginTime(){
        return  formatStr(begin_year) + "-"
                + formatStr(begin_month) + "-"
                + formatStr(begin_day);
    }

    protected String getEndTime(){
        return  formatStr(end_year) + "-"
                + formatStr(end_month) + "-"
                + formatStr(end_day);
    }

    private String formatStr(int num){
        if(num > 0 && num < 10){
            return "0"+num;
        }else
            return num+"";
    }
}
