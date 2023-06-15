
package ltd.mall618.mall.dao;

import ltd.mall618.mall.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselMapper {

    List<Carousel> findCarouselsByNum(@Param("number") int number);
}