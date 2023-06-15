
package ltd.mall618.mall.dao;

import ltd.mall618.mall.entity.MallShoppingCartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallShoppingCartItemMapper {
    int deleteByPrimaryKey(Long cartItemId);

    int insertSelective(MallShoppingCartItem record);

    MallShoppingCartItem selectByPrimaryKey(Long cartItemId);

    MallShoppingCartItem selectByUserIdAndGoodsId(@Param("mallUserId") Long mallUserId, @Param("goodsId") Long goodsId);

    List<MallShoppingCartItem> selectByUserId(@Param("mallUserId") Long mallUserId, @Param("number") int number);

    List<MallShoppingCartItem> selectByUserIdAndCartItemIds(@Param("mallUserId") Long mallUserId, @Param("cartItemIds") List<Long> cartItemIds);

    int selectCountByUserId(Long mallUserId);

    int updateByPrimaryKeySelective(MallShoppingCartItem record);

    int deleteBatch(List<Long> ids);

}