
package ltd.mall618.mall.api.mall;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ltd.mall618.mall.api.mall.param.SaveCartItemParam;
import ltd.mall618.mall.api.mall.param.SettlementParam;
import ltd.mall618.mall.api.mall.param.UpdateCartItemParam;
import ltd.mall618.mall.common.MallException;
import ltd.mall618.mall.common.ServiceResultEnum;
import ltd.mall618.mall.config.annotation.TokenToMallUser;
import ltd.mall618.mall.api.mall.vo.MallShoppingCartItemVO;
import ltd.mall618.mall.entity.MallUser;
import ltd.mall618.mall.entity.MallShoppingCartItem;
import ltd.mall618.mall.service.MallShoppingCartService;
import ltd.mall618.mall.util.Result;
import ltd.mall618.mall.util.ResultGenerator;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@Api(value = "v1", tags = "5.618商城购物车相关接口")
@RequestMapping("/api/v1")
public class MallShoppingCartAPI {

    @Resource
    private MallShoppingCartService mallShoppingCartService;

    @GetMapping("/shop-cart")
    @ApiOperation(value = "购物车列表(网页移动端不分页)", notes = "")
    public Result<List<MallShoppingCartItemVO>> cartItemList(@TokenToMallUser MallUser loginMallUser) {
        return ResultGenerator.genSuccessResult(mallShoppingCartService.getMyShoppingCartItems(loginMallUser.getUserId()));
    }

    @PostMapping("/shop-cart")
    @ApiOperation(value = "添加商品到购物车接口", notes = "传参为商品id、数量")
    public Result saveMallShoppingCartItem(@RequestBody SaveCartItemParam saveCartItemParam,
                                           @TokenToMallUser MallUser loginMallUser) {
        String saveResult = mallShoppingCartService.saveMallCartItem(saveCartItemParam, loginMallUser.getUserId());
        //添加成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(saveResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //添加失败
        return ResultGenerator.genFailResult(saveResult);
    }

    @PutMapping("/shop-cart")
    @ApiOperation(value = "修改购物项数据", notes = "传参为购物项id、数量")
    public Result updateMallShoppingCartItem(@RequestBody UpdateCartItemParam updateCartItemParam,
                                             @TokenToMallUser MallUser loginMallUser) {
        String updateResult = mallShoppingCartService.updateMallCartItem(updateCartItemParam, loginMallUser.getUserId());
        //修改成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(updateResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //修改失败
        return ResultGenerator.genFailResult(updateResult);
    }

    @DeleteMapping("/shop-cart/{mallShoppingCartItemId}")
    @ApiOperation(value = "删除购物项", notes = "传参为购物项id")
    public Result updateMallShoppingCartItem(@PathVariable("mallShoppingCartItemId") Long mallShoppingCartItemId,
                                             @TokenToMallUser MallUser loginMallUser) {
        MallShoppingCartItem mallCartItemById = mallShoppingCartService.getMallCartItemById(mallShoppingCartItemId);
        if (!loginMallUser.getUserId().equals(mallCartItemById.getUserId())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.REQUEST_FORBIDEN_ERROR.getResult());
        }
        Boolean deleteResult = mallShoppingCartService.deleteById(mallShoppingCartItemId,loginMallUser.getUserId());
        //删除成功
        if (deleteResult) {
            return ResultGenerator.genSuccessResult();
        }
        //删除失败
        return ResultGenerator.genFailResult(ServiceResultEnum.OPERATE_ERROR.getResult());
    }

    @GetMapping("/shop-cart/settle")
    @ApiOperation(value = "根据购物项id数组查询购物项明细", notes = "购物车页面使用")
    public Result<List<MallShoppingCartItemVO>> toSettle(Long[] cartItemIds, @TokenToMallUser MallUser loginMallUser) {
        if (cartItemIds.length < 1) {
            MallException.fail("参数异常");
        }
        int priceTotal = 0;
        List<MallShoppingCartItemVO> itemsForSettle = mallShoppingCartService.getCartItemsForSettle(Arrays.asList(cartItemIds), loginMallUser.getUserId());
        if (CollectionUtils.isEmpty(itemsForSettle)) {
            //无数据则抛出异常
            MallException.fail("参数异常");
        } else {
            //总价
            for (MallShoppingCartItemVO mallShoppingCartItemVO : itemsForSettle) {
                priceTotal += mallShoppingCartItemVO.getGoodsCount() * mallShoppingCartItemVO.getSellingPrice();
            }
            if (priceTotal < 1) {
                MallException.fail("价格异常");
            }
        }
        return ResultGenerator.genSuccessResult(itemsForSettle);
    }

    @PostMapping("/shop-cart/settlement")
    @ApiOperation(value = "购物车结算接口", notes = "传参为待结算的购物项id数组")
    public Result<String> settlement(@ApiParam(value = "结算参数") @RequestBody SettlementParam settlementParam, @TokenToMallUser MallUser loginMallUser) {
        int priceTotal = 0;
        if (settlementParam == null || settlementParam.getCartItemIds() == null) {
            MallException.fail(ServiceResultEnum.PARAM_ERROR.getResult());
        }
        if (settlementParam.getCartItemIds().length < 1) {
            MallException.fail(ServiceResultEnum.PARAM_ERROR.getResult());
        }
        List<MallShoppingCartItemVO> itemsForSave = mallShoppingCartService.getCartItemsForSettle(Arrays.asList(settlementParam.getCartItemIds()), loginMallUser.getUserId());
        if (CollectionUtils.isEmpty(itemsForSave)) {
            //无数据
            MallException.fail("参数异常");
        } else {
            //总价
            for (MallShoppingCartItemVO mallShoppingCartItemVO : itemsForSave) {
                priceTotal += mallShoppingCartItemVO.getGoodsCount() * mallShoppingCartItemVO.getSellingPrice();
            }
            if (priceTotal < 1) {
                MallException.fail("价格异常");
            }
            //结算并返回信息
            String saveOrderResult = mallShoppingCartService.settlement(loginMallUser, itemsForSave);
            Result result = ResultGenerator.genSuccessResult();
            result.setData(saveOrderResult);
            return result;
        }
        return ResultGenerator.genFailResult("结算单失败");
    }
}
