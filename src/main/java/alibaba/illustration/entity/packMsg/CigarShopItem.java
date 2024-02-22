package alibaba.illustration.entity.packMsg;

import alibaba.illustration.entity.Cigar;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CigarShopItem extends Cigar {
    private String shop_id;
    private int repertory;
    private int sold_count;
}
