package alibaba.illustration.entity.ill.factory.format_factory;

import alibaba.illustration.entity.ill.vo.analysis.ChartVo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class FormatFactory {
    List<ChartVo> chartVos;

    public FormatFactory(List<ChartVo> chartVos) {
        this.chartVos = chartVos;
    }

    public List<ChartVo> getStandardLineList(){
        List<ChartVo> standard = this.formatStandardList(chartVos);
        return fullFillFromStandardList(standard,chartVos);
    }
    public List<ChartVo> getStandardPieList(int pie_count){
        List<ChartVo> standard = this.formatStandardList(chartVos);
        return fillFromStandardListWithMaxLimit(standard,chartVos,pie_count);
    }
    public List<ChartVo> getStandardSixDemList(){
        List<ChartVo> standard = this.formatStandardList(chartVos);
        return fillFromStandardListWithMaxLimit(standard,chartVos,6);
    }

    //需要具体子类实现具体装饰规则
    public abstract List<ChartVo> formatStandardList(List<ChartVo> chartVos);

    //全匹配给standard赋值
    private List<ChartVo> fullFillFromStandardList(List<ChartVo> standard,List<ChartVo> chartVos){
        for(ChartVo standardVo : standard){
            for(ChartVo compare : chartVos){
                if(standardVo.getGroup_name().equals(compare.getGroup_name())){
                    standardVo.setGroup_value(compare.getGroup_value());
                    break;
                }
            }
        }
        return standard;
    }

    //只赋值前maxlimit项，剩余的赋值给其他
    private List<ChartVo> fillFromStandardListWithMaxLimit(List<ChartVo> standard,List<ChartVo> chartVos,int maxLimit){
        int preIndex = 0;
        for(; preIndex < maxLimit && preIndex < chartVos.size() ; preIndex++){
            standard.get(preIndex).setGroup_value(chartVos.get(preIndex).getGroup_value());
        }
        if(preIndex == chartVos.size()) return standard;
        ChartVo other = new ChartVo();
        other.setGroup_name("其他");
        other.setGroup_value((long) 0);
        for(int i = maxLimit ; i < chartVos.size() ; i++){
            other.setGroup_value(other.getGroup_value() + chartVos.get(i).getGroup_value());
        }
        standard.add(other);
        return standard;
    }

}
