package alibaba.illustration.entity.packMsg;

import lombok.Data;

@Data
public class WillBuyPack {
    private String user_id;
    private String shop_id;
    private String cigar_id;
    private String price;
    private Integer willBuyCount;
}
