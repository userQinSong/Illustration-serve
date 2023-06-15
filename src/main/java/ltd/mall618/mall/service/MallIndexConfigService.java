
package ltd.mall618.mall.service;

import ltd.mall618.mall.api.mall.vo.MallIndexConfigGoodsVO;

import java.util.List;

public interface MallIndexConfigService {

    /**
     * 返回固定数量的首页配置商品对象(首页调用)
     *
     * @param number
     * @return
     */
    List<MallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);

}
