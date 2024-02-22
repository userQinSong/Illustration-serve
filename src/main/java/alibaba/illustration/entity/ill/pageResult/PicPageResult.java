package alibaba.illustration.entity.ill.pageResult;

import alibaba.illustration.common.pageHelper.PageParam;
import alibaba.illustration.entity.ill.entity.User;
import alibaba.illustration.entity.ill.entity.attitude.Attitude;
import alibaba.illustration.entity.ill.vo.PicVo;
import lombok.Data;


@Data
public class PicPageResult extends PageParam {
    private PicVo picVo;
    private User user;
    private Attitude attitude;
}
