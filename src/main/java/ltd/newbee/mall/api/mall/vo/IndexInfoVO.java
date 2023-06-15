
package ltd.newbee.mall.api.mall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IndexInfoVO implements Serializable {

    @ApiModelProperty("轮播图(列表)")
    private List<NewBeeMallIndexCarouselVO> carousels;

    @ApiModelProperty("首页热销商品(列表)")
    private List<NewBeeMallIndexConfigGoodsVO> hotGoodses;
}
