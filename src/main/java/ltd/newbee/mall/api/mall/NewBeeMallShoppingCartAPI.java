
package ltd.newbee.mall.api.mall;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ltd.newbee.mall.api.mall.param.SaveCartItemParam;
import ltd.newbee.mall.api.mall.param.SettlementParam;
import ltd.newbee.mall.api.mall.param.UpdateCartItemParam;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.config.annotation.TokenToMallUser;
import ltd.newbee.mall.api.mall.vo.NewBeeMallShoppingCartItemVO;
import ltd.newbee.mall.entity.MallUser;
import ltd.newbee.mall.entity.NewBeeMallShoppingCartItem;
import ltd.newbee.mall.service.NewBeeMallShoppingCartService;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@Api(value = "v1", tags = "5.新蜂商城购物车相关接口")
@RequestMapping("/api/v1")
public class NewBeeMallShoppingCartAPI {

    @Resource
    private NewBeeMallShoppingCartService newBeeMallShoppingCartService;

    @GetMapping("/shop-cart")
    @ApiOperation(value = "购物车列表(网页移动端不分页)", notes = "")
    public Result<List<NewBeeMallShoppingCartItemVO>> cartItemList(@TokenToMallUser MallUser loginMallUser) {
        return ResultGenerator.genSuccessResult(newBeeMallShoppingCartService.getMyShoppingCartItems(loginMallUser.getUserId()));
    }

    @PostMapping("/shop-cart")
    @ApiOperation(value = "添加商品到购物车接口", notes = "传参为商品id、数量")
    public Result saveNewBeeMallShoppingCartItem(@RequestBody SaveCartItemParam saveCartItemParam,
                                                 @TokenToMallUser MallUser loginMallUser) {
        String saveResult = newBeeMallShoppingCartService.saveNewBeeMallCartItem(saveCartItemParam, loginMallUser.getUserId());
        //添加成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(saveResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //添加失败
        return ResultGenerator.genFailResult(saveResult);
    }

    @PutMapping("/shop-cart")
    @ApiOperation(value = "修改购物项数据", notes = "传参为购物项id、数量")
    public Result updateNewBeeMallShoppingCartItem(@RequestBody UpdateCartItemParam updateCartItemParam,
                                                   @TokenToMallUser MallUser loginMallUser) {
        String updateResult = newBeeMallShoppingCartService.updateNewBeeMallCartItem(updateCartItemParam, loginMallUser.getUserId());
        //修改成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(updateResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //修改失败
        return ResultGenerator.genFailResult(updateResult);
    }

    @DeleteMapping("/shop-cart/{newBeeMallShoppingCartItemId}")
    @ApiOperation(value = "删除购物项", notes = "传参为购物项id")
    public Result updateNewBeeMallShoppingCartItem(@PathVariable("newBeeMallShoppingCartItemId") Long newBeeMallShoppingCartItemId,
                                                   @TokenToMallUser MallUser loginMallUser) {
        NewBeeMallShoppingCartItem newBeeMallCartItemById = newBeeMallShoppingCartService.getNewBeeMallCartItemById(newBeeMallShoppingCartItemId);
        if (!loginMallUser.getUserId().equals(newBeeMallCartItemById.getUserId())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.REQUEST_FORBIDEN_ERROR.getResult());
        }
        Boolean deleteResult = newBeeMallShoppingCartService.deleteById(newBeeMallShoppingCartItemId,loginMallUser.getUserId());
        //删除成功
        if (deleteResult) {
            return ResultGenerator.genSuccessResult();
        }
        //删除失败
        return ResultGenerator.genFailResult(ServiceResultEnum.OPERATE_ERROR.getResult());
    }

    @GetMapping("/shop-cart/settle")
    @ApiOperation(value = "根据购物项id数组查询购物项明细", notes = "购物车页面使用")
    public Result<List<NewBeeMallShoppingCartItemVO>> toSettle(Long[] cartItemIds, @TokenToMallUser MallUser loginMallUser) {
        if (cartItemIds.length < 1) {
            NewBeeMallException.fail("参数异常");
        }
        int priceTotal = 0;
        List<NewBeeMallShoppingCartItemVO> itemsForSettle = newBeeMallShoppingCartService.getCartItemsForSettle(Arrays.asList(cartItemIds), loginMallUser.getUserId());
        if (CollectionUtils.isEmpty(itemsForSettle)) {
            //无数据则抛出异常
            NewBeeMallException.fail("参数异常");
        } else {
            //总价
            for (NewBeeMallShoppingCartItemVO newBeeMallShoppingCartItemVO : itemsForSettle) {
                priceTotal += newBeeMallShoppingCartItemVO.getGoodsCount() * newBeeMallShoppingCartItemVO.getSellingPrice();
            }
            if (priceTotal < 1) {
                NewBeeMallException.fail("价格异常");
            }
        }
        return ResultGenerator.genSuccessResult(itemsForSettle);
    }

    @PostMapping("/shop-cart/settlement")
    @ApiOperation(value = "购物车结算接口", notes = "传参为待结算的购物项id数组")
    public Result<String> settlement(@ApiParam(value = "结算参数") @RequestBody SettlementParam settlementParam, @TokenToMallUser MallUser loginMallUser) {
        int priceTotal = 0;
        if (settlementParam == null || settlementParam.getCartItemIds() == null) {
            NewBeeMallException.fail(ServiceResultEnum.PARAM_ERROR.getResult());
        }
        if (settlementParam.getCartItemIds().length < 1) {
            NewBeeMallException.fail(ServiceResultEnum.PARAM_ERROR.getResult());
        }
        List<NewBeeMallShoppingCartItemVO> itemsForSave = newBeeMallShoppingCartService.getCartItemsForSettle(Arrays.asList(settlementParam.getCartItemIds()), loginMallUser.getUserId());
        if (CollectionUtils.isEmpty(itemsForSave)) {
            //无数据
            NewBeeMallException.fail("参数异常");
        } else {
            //总价
            for (NewBeeMallShoppingCartItemVO newBeeMallShoppingCartItemVO : itemsForSave) {
                priceTotal += newBeeMallShoppingCartItemVO.getGoodsCount() * newBeeMallShoppingCartItemVO.getSellingPrice();
            }
            if (priceTotal < 1) {
                NewBeeMallException.fail("价格异常");
            }
            //结算并返回信息
            String saveOrderResult = newBeeMallShoppingCartService.settlement(loginMallUser, itemsForSave);
            Result result = ResultGenerator.genSuccessResult();
            result.setData(saveOrderResult);
            return result;
        }
        return ResultGenerator.genFailResult("结算单失败");
    }
}
