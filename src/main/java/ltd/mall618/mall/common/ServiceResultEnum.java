
package ltd.mall618.mall.common;

public enum ServiceResultEnum {
    ERROR("error"),

    SUCCESS("success"),

    DATA_NOT_EXIST("未查询到记录！"),

    PARAM_ERROR("参数错误！"),


    SAME_LOGIN_NAME_EXIST("用户名已存在！"),


    LOGIN_NAME_IS_NOT_PHONE("请输入正确的手机号！"),

    GOODS_NOT_EXIST("商品不存在！"),

    GOODS_PUT_DOWN("商品已下架！"),

    SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR("超出单个商品的最大购买数量！"),

    SHOPPING_CART_ITEM_NUMBER_ERROR("商品数量不能小于 1 ！"),

    SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR("超出购物车最大容量！"),

    SHOPPING_CART_ITEM_EXIST_ERROR("已存在！无需重复添加！"),

    LOGIN_ERROR("登录失败！"),

    NOT_LOGIN_ERROR("未登录！"),


    TOKEN_EXPIRE_ERROR("无效认证！请重新登录！"),


    USER_NULL_ERROR("无效用户！请重新登录！"),

    SHOPPING_ITEM_ERROR("购物车数据异常！"),

    SHOPPING_ITEM_COUNT_ERROR("库存不足！"),

    OPERATE_ERROR("操作失败！"),

    REQUEST_FORBIDEN_ERROR("禁止该操作！"),

    NO_PERMISSION_ERROR("无权限！"),

    DB_ERROR("database error");

    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
