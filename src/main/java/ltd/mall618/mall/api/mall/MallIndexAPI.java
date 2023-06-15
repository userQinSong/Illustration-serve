
package ltd.mall618.mall.api.mall;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.mall618.mall.common.Constants;
import ltd.mall618.mall.common.IndexConfigTypeEnum;
import ltd.mall618.mall.api.mall.vo.IndexInfoVO;
import ltd.mall618.mall.api.mall.vo.MallIndexCarouselVO;
import ltd.mall618.mall.api.mall.vo.MallIndexConfigGoodsVO;
import ltd.mall618.mall.service.MallCarouselService;
import ltd.mall618.mall.service.MallIndexConfigService;
import ltd.mall618.mall.util.Result;
import ltd.mall618.mall.util.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "v1", tags = "1.618商城首页接口")
@RequestMapping("/api/v1")
public class MallIndexAPI {

    @Resource
    private MallCarouselService mallCarouselService;

    @Resource
    private MallIndexConfigService mallIndexConfigService;

    @GetMapping("/index-infos")
    @ApiOperation(value = "获取首页数据", notes = "轮播图、热销等")
    public Result<IndexInfoVO> indexInfo() {
        IndexInfoVO indexInfoVO = new IndexInfoVO();
        List<MallIndexCarouselVO> carousels = mallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        List<MallIndexConfigGoodsVO> hotGoodses = mallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
        indexInfoVO.setCarousels(carousels);
        indexInfoVO.setHotGoodses(hotGoodses);
        return ResultGenerator.genSuccessResult(indexInfoVO);
    }
}
