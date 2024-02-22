package alibaba.illustration.service.ill;

import alibaba.illustration.entity.ill.pageParams.pic.PicDetailParam;
import alibaba.illustration.entity.ill.pageParams.pic.PicParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltDetailParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltListParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltUploadParam;
import alibaba.illustration.entity.ill.pageResult.PicPageResult;
import alibaba.illustration.entity.ill.pageResult.PicoltPageResult;
import alibaba.illustration.entity.ill.vo.PicVo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface GetIllustrationService {
    PageInfo<PicoltPageResult> getPicoltList(PicoltListParam param);
    PageInfo<PicPageResult> getPicListUserCreated(PicParam param);
    PageInfo<PicPageResult> getPicListUserLiked(PicParam param);
    List<PicVo> getPicListByPicoltId(PicParam param);
    PicoltPageResult getPicolt(PicoltDetailParam param);
    boolean changeLoginUserPicoltAttitude(PicoltDetailParam param);
    boolean changeLoginUserPicAttitude(PicDetailParam param);
    boolean uploadPicolt(PicoltUploadParam param);
}
