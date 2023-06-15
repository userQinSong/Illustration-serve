
package ltd.newbee.mall.api.mall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class NewBeeMallUserVO implements Serializable {

    @ApiModelProperty("用户登录名")
    private String loginName;
}
