package alibaba.illustration.entity.record;

import lombok.Data;


import java.util.Date;
import java.util.List;
@Data
public class RecordVo {
    private String record_id;
    private String customer_name;
    private String receiver_name;
    private int type;
    private String address;
    private Date trade_time;
    private String record_time;
    private Double trade_price;
    private List<RecordDetailVo> buy_list;

    public RecordVo() {
    }

    public RecordVo(String record_id, String customer_name, String receiver_name, int type, String address, Date trade_time, String record_time, Double trade_price) {
        this.record_id = record_id;
        this.customer_name = customer_name;
        this.receiver_name = receiver_name;
        this.type = type;
        this.address = address;
        this.trade_time = trade_time;
        this.record_time = record_time;
        this.trade_price = trade_price;
    }
}
