
package ltd.mall618.mall.service.impl;

import ltd.mall618.mall.api.mall.vo.MallIndexCarouselVO;
import ltd.mall618.mall.dao.CarouselMapper;
import ltd.mall618.mall.entity.Carousel;
import ltd.mall618.mall.service.MallCarouselService;
import ltd.mall618.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallCarouselServiceImpl implements MallCarouselService {

    @Autowired
    private CarouselMapper carouselMapper;


    @Override
    public List<MallIndexCarouselVO> getCarouselsForIndex(int number) {
        List<MallIndexCarouselVO> mallIndexCarouselVOS = new ArrayList<>(number);
        List<Carousel> carousels = carouselMapper.findCarouselsByNum(number);
        if (!CollectionUtils.isEmpty(carousels)) {
            mallIndexCarouselVOS = BeanUtil.copyList(carousels, MallIndexCarouselVO.class);
        }
        return mallIndexCarouselVOS;
    }
}
