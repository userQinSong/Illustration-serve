
package ltd.mall618.mall.service;

import ltd.mall618.mall.api.mall.vo.MallIndexCategoryVO;

import java.util.List;

public interface MallCategoryService {

    /**
     * 返回分类数据(首页调用)
     *
     * @return
     */
    List<MallIndexCategoryVO> getCategoriesForIndex();

}
