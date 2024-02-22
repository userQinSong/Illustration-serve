package alibaba.illustration.entity.ill.factory.format_factory.impl;

import alibaba.illustration.entity.ill.factory.format_factory.FormatFactory;
import alibaba.illustration.entity.ill.vo.analysis.ChartVo;

import java.util.ArrayList;
import java.util.List;

//根据时间进行分布
public class HourGroupListFactory extends FormatFactory {

    public HourGroupListFactory(List<ChartVo> chartVos) {
        super(chartVos);
    }

    @Override
    public List<ChartVo> formatStandardList(List<ChartVo> chartVos) {
        List<ChartVo> standard = new ArrayList<>();
        for(ChartVo chartVo : chartVos){
            //先获取每个数据的分类时间
            int curHour = Integer.parseInt(chartVo.getGroup_name());
            //new一个新的chartVo标准对象
            ChartVo standardItem = new ChartVo();
            standardItem.setGroup_name(curHour+ ":00~" + (curHour + 1) + ":00");
            standardItem.setGroup_value((long) 0);
            //组装好后添加至standard列表中
            standard.add(standardItem);
        }
        return standard;
    }
}
