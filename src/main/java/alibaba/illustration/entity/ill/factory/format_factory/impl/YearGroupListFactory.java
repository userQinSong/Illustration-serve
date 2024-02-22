package alibaba.illustration.entity.ill.factory.format_factory.impl;

import alibaba.illustration.entity.ill.factory.format_factory.FormatFactory;
import alibaba.illustration.entity.ill.vo.analysis.ChartVo;

import java.util.ArrayList;
import java.util.List;

public class YearGroupListFactory extends FormatFactory {
    public YearGroupListFactory(List<ChartVo> chartVos) {
        super(chartVos);
    }

    @Override
    public List<ChartVo> formatStandardList(List<ChartVo> chartVos) {
        if(chartVos == null || chartVos.size() == 0)
            return null;
        String beginYear = chartVos.get(0).getGroup_name().split("-")[0];
        List<ChartVo> standard = new ArrayList<>();
        for(int i = 1 ; i <= 12 ; i++){
            ChartVo chartVo = new ChartVo();
            chartVo.setGroup_name(beginYear + "-" + i);
            chartVo.setGroup_value((long) 0);
            standard.add(chartVo);
        }
        return standard;
    }
}
