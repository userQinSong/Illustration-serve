package alibaba.illustration.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CigarShop extends Actor implements Comparable<CigarShop>{
    private String shop_id;
    private String shop_name;
    private String shop_boss;
    private String shop_pic;
    private String shop_loc;
    private String shop_phone;
    private int customer_arrive_count;

    @Override
    public int compareTo(CigarShop cigarShop) {
        return this.customer_arrive_count - cigarShop.customer_arrive_count;
    }
}
