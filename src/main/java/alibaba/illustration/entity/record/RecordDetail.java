package alibaba.illustration.entity.record;

import lombok.Data;

@Data
public class RecordDetail {
    private String record_id;
    private String cigar_id;
    private int buy_count;

    public RecordDetail() {
    }

    public RecordDetail(String record_id, String cigar_id, int buy_count) {
        this.record_id = record_id;
        this.cigar_id = cigar_id;
        this.buy_count = buy_count;
    }
}
