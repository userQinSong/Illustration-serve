
package ltd.mall618.mall.service;

import ltd.mall618.mall.api.mall.param.SaveCartItemParam;
import ltd.mall618.mall.api.mall.param.UpdateCartItemParam;
import ltd.mall618.mall.api.mall.vo.MallShoppingCartItemVO;
import ltd.mall618.mall.entity.MallUser;
import ltd.mall618.mall.entity.MallShoppingCartItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MallShoppingCartService {

    /**
     * 保存商品至购物车中
     *
     * @param saveCartItemParam
     * @param userId
     * @return
     */
    String saveMallCartItem(SaveCartItemParam saveCartItemParam, Long userId);

    /**
     * 修改购物车中的属性
     *
     * @param updateCartItemParam
     * @param userId
     * @return
     */
    String updateMallCartItem(UpdateCartItemParam updateCartItemParam, Long userId);

    /**
     * 获取购物项详情
     *
     * @param mallShoppingCartItemId
     * @return
     */
    MallShoppingCartItem getMallCartItemById(Long mallShoppingCartItemId);

    /**
     * 删除购物车中的商品
     *
     *
     * @param shoppingCartItemId
     * @param userId
     * @return
     */
    Boolean deleteById(Long shoppingCartItemId, Long userId);

    /**
     * 获取我的购物车中的列表数据
     *
     * @param mallUserId
     * @return
     */
    List<MallShoppingCartItemVO> getMyShoppingCartItems(Long mallUserId);

    /**
     * 根据userId和cartItemIds获取对应的购物项记录
     *
     * @param cartItemIds
     * @param mallUserId
     * @return
     */
    List<MallShoppingCartItemVO> getCartItemsForSettle(List<Long> cartItemIds, Long mallUserId);

    @Transactional
    String settlement(MallUser loginMallUser, List<MallShoppingCartItemVO> myShoppingCartItems);
}
