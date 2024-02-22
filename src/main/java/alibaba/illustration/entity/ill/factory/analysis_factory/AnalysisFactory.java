package alibaba.illustration.entity.ill.factory.analysis_factory;

import alibaba.illustration.common.tool.SpringUtil;
import alibaba.illustration.dao.ill.AnalysisDao;
import alibaba.illustration.entity.ill.pageParams.analysis.AnalysisParam;
import alibaba.illustration.entity.ill.vo.analysis.ChartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public abstract class AnalysisFactory {
    protected AnalysisDao dao = null;

    public AnalysisFactory() {
        //1.获取相关service层的方法
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        //这是笔者声明的Service类，使用者可以换成自己定义的
        this.dao = applicationContext.getBean(AnalysisDao.class);
    }

    public List<ChartVo> queryListByAnalysisParam(AnalysisParam param){
        //param参数初始化，可以进行sql
        param.init();
        if(param.getGroup_by().equals("year")) return dao.queryDataGroupByYear(param);
        else if(param.getGroup_by().equals("hour")) return dao.queryDataGroupByHourTime(param);
        else return queryListGroupByOther(param);
    }
    protected abstract List<ChartVo> queryListGroupByOther(AnalysisParam param);
}
