package alibaba.illustration.service.ill.impl;

import alibaba.illustration.dao.ill.AnalysisDao;
import alibaba.illustration.entity.ill.factory.analysis_factory.AnalysisFactory;
import alibaba.illustration.entity.ill.factory.analysis_factory.impl.CommentAnalysisFactory;
import alibaba.illustration.entity.ill.factory.analysis_factory.impl.PicAnalysisFactory;
import alibaba.illustration.entity.ill.factory.analysis_factory.impl.RemainAnalysisTimeFactory;
import alibaba.illustration.entity.ill.factory.analysis_factory.impl.UserAnalysisFactory;
import alibaba.illustration.entity.ill.factory.format_factory.FormatFactory;
import alibaba.illustration.entity.ill.factory.format_factory.impl.HourGroupListFactory;
import alibaba.illustration.entity.ill.factory.format_factory.impl.TagGroupListFactory;
import alibaba.illustration.entity.ill.factory.format_factory.impl.YearGroupListFactory;
import alibaba.illustration.entity.ill.pageParams.analysis.AnalysisParam;
import alibaba.illustration.entity.ill.pageParams.analysis.impl.LineChartAnalysisParam;
import alibaba.illustration.entity.ill.pageParams.analysis.impl.PieChartAnalysisParam;
import alibaba.illustration.entity.ill.pageParams.analysis.impl.SixDemChartAnalysisParam;
import alibaba.illustration.entity.ill.vo.analysis.ChartVo;
import alibaba.illustration.entity.ill.vo.analysis.TotalCalculationVo;
import alibaba.illustration.service.ill.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisDao analysisDao;

    @Override
    public List<ChartVo> getAnalysisListFromParam(AnalysisParam param) {
        //先根据数据类型：remain_time,user,pic,comment 获取到特定的工厂
        AnalysisFactory analysisFactory = this.getAnalysisFactoryFromSearchType(param);
        //analysisFactory中已经针对group_by进行识别了，从而获取到依据group_by的分析列表
        //通过获取到的分析列表，配合group_by生成chart展示组装工厂
        FormatFactory formatFactory = this.getFormatFactoryFromGroupBy(analysisFactory.queryListByAnalysisParam(param),param);
        //最后根据param的种类：lienChart,pieChart,...进行返回特定组装数据
        return getChartTypeListByParam(formatFactory,param);
    }

    private List<ChartVo> getChartTypeListByParam(FormatFactory factory,AnalysisParam param){
        if(param instanceof LineChartAnalysisParam)
            return factory.getStandardLineList();
        else if(param instanceof PieChartAnalysisParam)
            return factory.getStandardPieList(((PieChartAnalysisParam) param).getPie_count());
        else if(param instanceof SixDemChartAnalysisParam)
            return factory.getStandardSixDemList();
        return null;
    }

    private FormatFactory getFormatFactoryFromGroupBy(List<ChartVo> databaseList,AnalysisParam param){
        FormatFactory format_Factory = null;
        if(param.getGroup_by().equals("year"))
            format_Factory = new YearGroupListFactory(databaseList);
        else if(param.getGroup_by().equals("hour"))
            format_Factory = new HourGroupListFactory(databaseList);
        else
            format_Factory = new TagGroupListFactory(databaseList);
        return format_Factory;
    }

    private AnalysisFactory getAnalysisFactoryFromSearchType(AnalysisParam param){
        AnalysisFactory analysis_factory = null;
        if(param.getSearch_type().equals("remain_time"))
            analysis_factory = new RemainAnalysisTimeFactory();
        else if(param.getSearch_type().equals("user"))
            analysis_factory = new UserAnalysisFactory();
        else if(param.getSearch_type().equals("pic"))
            analysis_factory = new PicAnalysisFactory();
        else if(param.getSearch_type().equals("comment"))
            analysis_factory = new CommentAnalysisFactory();
        return analysis_factory;
    }

    @Override
    public TotalCalculationVo getWebsiteTotalCalPack() {
        TotalCalculationVo pack = new TotalCalculationVo();
        //组装总体数据
        pack.setTotalRemainTime(analysisDao.sumWebsiteCount("remain_time","total"));
        pack.setTotalCommentCount(analysisDao.sumWebsiteCount("comment_count","total"));
        pack.setTotalPicCount(analysisDao.sumWebsiteCount("pic_count","total"));
        pack.setTotalUserCount(analysisDao.sumWebsiteCount("user_count","total"));
        //组装月数据
        pack.setMonthRemainTime(analysisDao.sumWebsiteCount("remain_time","month"));
        pack.setMonthCommentCount(analysisDao.sumWebsiteCount("comment_count","month"));
        pack.setMonthPicCount(analysisDao.sumWebsiteCount("pic_count","month"));
        pack.setMonthUserCount(analysisDao.sumWebsiteCount("user_count","month"));
        //组装天数据
        pack.setDayRemainTime(analysisDao.sumWebsiteCount("remain_time","day"));
        pack.setDayCommentCount(analysisDao.sumWebsiteCount("comment_count","day"));
        pack.setDayPicCount(analysisDao.sumWebsiteCount("pic_count","day"));
        pack.setDayUserCount(analysisDao.sumWebsiteCount("user_count","day"));
        return pack;
    }
}
