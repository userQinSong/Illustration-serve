package alibaba.illustration.dao;
import alibaba.illustration.entity.dataAnalysis.DataInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataAnalysisDao {
    List<DataInfo> getDataInfos(String shop_id);
}
