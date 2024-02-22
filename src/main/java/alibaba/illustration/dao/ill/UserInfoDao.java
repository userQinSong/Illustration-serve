package alibaba.illustration.dao.ill;

import alibaba.illustration.entity.ill.entity.User;
import alibaba.illustration.entity.ill.pageParams.user.UserParam;
import alibaba.illustration.entity.ill.pageResult.UserPageResult;
import alibaba.illustration.entity.ill.vo.UserVo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserInfoDao {
    UserVo queryUserById(String user_id);
    //计算某个用户所有图集的被下载数
    UserVo queryUserInfoDetailByUserId(String user_id);

    List<UserPageResult> queryAllConcernByUserParam(UserParam userParam);

    User findUserByPhoneNumber(String phone_number);

    void logoutNewUser(User user);

    boolean hasExistAttention(String attention_id,String fans_id);

    boolean updateUserInfo(UserParam param);

    //UserAttention
    boolean insertRelationUserAttention(String attention_id, String fans_id,Date create_time);

    boolean updateRelationUserAttention(UserParam param);

    boolean queryRelationUserAttention(UserParam param);

    //UserRemainTime
    boolean queryRelationUserRemain(UserParam param);

    boolean updateRelationUserRemain(UserParam param);

    boolean insertRelationUserRemain(UserParam param);
}
