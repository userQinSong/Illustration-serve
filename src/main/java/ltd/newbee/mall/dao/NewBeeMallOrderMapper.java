
package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.NewBeeMallOrder;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewBeeMallOrderMapper {

    int insertSelective(NewBeeMallOrder record);

}