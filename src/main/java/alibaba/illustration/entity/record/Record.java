package alibaba.illustration.entity.record;

import alibaba.illustration.common.Date.DateGetter;
import alibaba.illustration.common.id_generator.RecordIdGenerator;
import lombok.Data;

import java.util.Date;

@Data
public class Record{
    private String record_id;
    private String customer_id;
    private String receiver_id;
    private Double trade_price;
    private Date trade_time;
    private String trade_address;
    private int trade_rank;

    public Record() {
        this.record_id = new RecordIdGenerator().getNewRecordId();
        this.trade_time = DateGetter.getCurrentTime();
    }

    public Record(Double trade_price,String user_id,String shop_id,String trade_address,int trade_rank) {
        this();
        this.trade_price = trade_price;
        this.customer_id = user_id;
        this.receiver_id = shop_id;
        this.trade_address = trade_address;
        this.trade_rank = trade_rank;
    }
}
