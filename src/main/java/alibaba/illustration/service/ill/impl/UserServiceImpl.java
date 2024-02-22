package alibaba.illustration.service.ill.impl;

import alibaba.illustration.common.Date.DateGetter;
import alibaba.illustration.common.id_generator.AutoIdGenerator;
import alibaba.illustration.dao.ill.UserInfoDao;
import alibaba.illustration.entity.ill.entity.User;
import alibaba.illustration.entity.ill.pageParams.user.UserParam;
import alibaba.illustration.entity.ill.pageResult.UserPageResult;
import alibaba.illustration.entity.ill.vo.UserVo.UserVo;
import alibaba.illustration.service.ill.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserVo getUserInfo(String user_id) {
        //先获取用户最基本的信息
        UserVo userVo = userInfoDao.queryUserById(user_id);
        if(userVo != null){
            //再获取用户有关的关系信息
            UserVo userDetailPack = userInfoDao.queryUserInfoDetailByUserId(user_id);
            //组装封装成一个完整的前端包
            userVo.setPicolt_be_like_count(userDetailPack.getPicolt_be_like_count());
            userVo.setPicolt_be_collect_count(userDetailPack.getPicolt_be_collect_count());
            userVo.setPicolt_be_down_count(userDetailPack.getPicolt_be_down_count());
            userVo.setPicolt_upload(userDetailPack.getPicolt_upload());
            userVo.setPic_be_like_count(userDetailPack.getPic_be_like_count());
            userVo.setPic_be_collect_count(userDetailPack.getPic_be_collect_count());
            userVo.setPic_be_down_count(userDetailPack.getPic_be_down_count());
            userVo.setPic_upload(userDetailPack.getPic_upload());
            userVo.setAttention(userDetailPack.getAttention());
            userVo.setBe_attention(userDetailPack.getBe_attention());
        }
        return userVo;
    }

    @Override
    public PageInfo<UserPageResult> getAllConcernPersonList(UserParam param) {
        return PageHelper.startPage(param.getPageNum(), param.getPageSize())
                .doSelectPageInfo(() -> userInfoDao.queryAllConcernByUserParam(param));
    }

    @Override
    public User logByPhoneNumber(String phone_number) {
        User findUser = userInfoDao.findUserByPhoneNumber(phone_number);
        if(findUser != null){//找到就直接返回
            return findUser;
        }else{//没找到就先注册再返回
            User logUser = new User();
            logUser.setPhone_number(phone_number);
            logUser.setRank(0);
            logUser.setGender(1);
            logUser.setUser_id(new AutoIdGenerator("user").getNewId());
            userInfoDao.logoutNewUser(logUser);
            return logUser;
        }
    }

    @Override
    public boolean changeAttentionOnePerson(UserParam param) {
        if(!userInfoDao.hasExistAttention(param.getUser_id(), param.getAttitude_person_id()))//不存在该关系则插入
            return userInfoDao.insertRelationUserAttention(param.getUser_id(), param.getAttitude_person_id(), DateGetter.getCurrentTime());
        else//有的话就直接更改关系状态
            return userInfoDao.updateRelationUserAttention(param);
    }

    @Override
    public boolean getAttentionOnePerson(UserParam param) {
        return userInfoDao.queryRelationUserAttention(param);
    }

    @Override
    public boolean editPersonalMsg(UserParam param) {
        return userInfoDao.updateUserInfo(param);
    }

    @Override
    public boolean pushRemainTime(UserParam param) {
        param.setCreate_time(DateGetter.getCurrentTime());
        return userInfoDao.insertRelationUserRemain(param);
    }
}
