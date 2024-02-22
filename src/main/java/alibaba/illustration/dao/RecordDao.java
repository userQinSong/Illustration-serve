package alibaba.illustration.dao;

import alibaba.illustration.entity.record.Record;
import alibaba.illustration.entity.record.RecordDetail;
import alibaba.illustration.entity.record.RecordDetailVo;
import alibaba.illustration.entity.record.RecordVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordDao {
    void insertRecord(Record record);
    void insertRecordDetail(RecordDetail recordDetail);
    List<RecordVo> getRecords(String user_id);
    List<RecordDetailVo> getRecordDetail(String record_id);
}
