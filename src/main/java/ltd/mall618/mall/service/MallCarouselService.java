
package ltd.mall618.mall.service;

import ltd.mall618.mall.api.mall.vo.MallIndexCarouselVO;

import java.util.List;

public interface MallCarouselService {

    /**
     * 返回固定数量的轮播图对象(首页调用)
     *
     * @param number
     * @return
     */
    List<MallIndexCarouselVO> getCarouselsForIndex(int number);

}
