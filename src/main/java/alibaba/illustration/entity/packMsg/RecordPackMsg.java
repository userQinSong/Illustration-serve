package alibaba.illustration.entity.packMsg;

import alibaba.illustration.entity.Cigar;
import alibaba.illustration.entity.record.Record;
import alibaba.illustration.entity.record.RecordDetail;
import lombok.Data;

import java.util.List;

@Data
public class RecordPackMsg extends Cigar {
    private Record record;
    private List<RecordDetail> recordDetailList;
}
