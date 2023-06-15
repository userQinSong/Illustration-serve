
package ltd.newbee.mall.api.mall.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 购物车结算param
 */
@Data
public class SettlementParam implements Serializable {

    @ApiModelProperty("结算项id数组")
    private Long[] cartItemIds;
}
