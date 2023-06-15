
package ltd.mall618.mall.service;

import ltd.mall618.mall.entity.MallGoods;
import ltd.mall618.mall.util.PageQueryUtil;
import ltd.mall618.mall.util.PageResult;

public interface MallGoodsService {

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    MallGoods getMallGoodsById(Long id);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchMallGoods(PageQueryUtil pageUtil);
}
