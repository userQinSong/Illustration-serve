package alibaba.illustration.entity.ill.pageResult;

import alibaba.illustration.common.pageHelper.PageParam;
import alibaba.illustration.entity.ill.vo.UserVo.UserConcernVo;
import alibaba.illustration.entity.ill.vo.UserVo.UserVo;
import lombok.Data;


@Data
public class UserPageResult extends PageParam {
    private UserVo userVo;
    private UserConcernVo userConcernVo;
}
