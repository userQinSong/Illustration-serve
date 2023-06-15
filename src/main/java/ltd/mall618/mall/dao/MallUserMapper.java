
package ltd.mall618.mall.dao;

import ltd.mall618.mall.entity.MallUser;
import org.apache.ibatis.annotations.Param;

public interface MallUserMapper {

    int insertSelective(MallUser record);

    MallUser selectByPrimaryKey(Long userId);

    MallUser selectByLoginName(String loginName);

    MallUser selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

}