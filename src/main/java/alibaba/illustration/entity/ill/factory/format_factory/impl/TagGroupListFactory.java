package alibaba.illustration.entity.ill.factory.format_factory.impl;


import alibaba.illustration.entity.ill.factory.format_factory.FormatFactory;
import alibaba.illustration.entity.ill.vo.analysis.ChartVo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagGroupListFactory extends FormatFactory {

    public TagGroupListFactory(List<ChartVo> chartVos) {
        super(chartVos);
    }

    //判断某个tag是否存在于某个数组中,如果存在则返回匹配成功的ChartVo对象，否则返回空
    public ChartVo hasPushTag(String testTag,List<ChartVo> origenArray){
        for(ChartVo origenChartVo:origenArray){
            String origenChartVoName = origenChartVo.getGroup_name();
            if(testTag.equals(origenChartVoName))
                return origenChartVo;
        }
        return null;
    }

    @Override
    public List<ChartVo> formatStandardList(List<ChartVo> chartVos) {
        List<ChartVo> standard = new ArrayList<>();
        for(ChartVo chartVo:chartVos){
            //先获取该chatVo对象的tag列表
            String[] tags = chartVo.getGroup_name().split(",");
            long addValue = chartVo.getGroup_value();
            //对每个tag进行检查，如果已经存在于standardList，则直接增加数量，没有则先创建一个chartVo再赋值
            for(String tag:tags){
                ChartVo changeChartVo = this.hasPushTag(tag,standard);//从standard中匹配到该tag的对象
                if(changeChartVo == null){//如果standard中未出现过该tag，则新增一个该tag进入
                    changeChartVo = new ChartVo();
                    changeChartVo.setGroup_name(tag);
                    changeChartVo.setGroup_value(addValue);
                    standard.add(changeChartVo);
                }else{//如果standard已经出现该tag则直接修改
                    changeChartVo.setGroup_value(changeChartVo.getGroup_value() + addValue);
                }
            }
        }
        //进行由小到大的排序
        Collections.sort(standard);
        //然后翻转一次，变为由大到小
        Collections.reverse(standard);
        return standard;
    }

}
