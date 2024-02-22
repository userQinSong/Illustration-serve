package alibaba.illustration.service.ill;

import alibaba.illustration.entity.ill.entity.User;
import alibaba.illustration.entity.ill.pageParams.user.UserParam;
import alibaba.illustration.entity.ill.pageResult.UserPageResult;
import alibaba.illustration.entity.ill.vo.UserVo.UserVo;
import com.github.pagehelper.PageInfo;

public interface UserService {
    UserVo getUserInfo(String user_id);
    PageInfo<UserPageResult> getAllConcernPersonList(UserParam param);
    User logByPhoneNumber(String phone_number);
    boolean changeAttentionOnePerson(UserParam param);
    boolean getAttentionOnePerson(UserParam param);
    boolean editPersonalMsg(UserParam param);
    boolean pushRemainTime(UserParam param);
}
