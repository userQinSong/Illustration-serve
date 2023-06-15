
package ltd.mall618.mall.dao;

import ltd.mall618.mall.entity.MallUserToken;

public interface MallUserTokenMapper {
    int deleteByPrimaryKey(Long userId);

    int insertSelective(MallUserToken record);

    MallUserToken selectByPrimaryKey(Long userId);

    MallUserToken selectByToken(String token);

    int updateByPrimaryKeySelective(MallUserToken record);

}