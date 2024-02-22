package alibaba.illustration.dao.ill;

import alibaba.illustration.entity.ill.entity.Pic;
import alibaba.illustration.entity.ill.entity.Picolt;
import alibaba.illustration.entity.ill.entity.attitude.Attitude;
import alibaba.illustration.entity.ill.pageParams.pic.PicDetailParam;
import alibaba.illustration.entity.ill.pageParams.pic.PicParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltDetailParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltListParam;
import alibaba.illustration.entity.ill.pageResult.PicPageResult;
import alibaba.illustration.entity.ill.pageResult.PicoltPageResult;
import alibaba.illustration.entity.ill.vo.PicVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IllustrationDao {
    //获取图集
    List<PicoltPageResult> getPicoltList(PicoltListParam param);
    PicoltPageResult getPicolt(String picolt_id);
    Long getPicoltContainPicCount(String picolt_id);
    //获取图片
    List<PicVo> getPicVoListByPicoltId(PicParam param);
    List<PicPageResult> getPicVoListByUserLiked(PicParam param);
    List<PicPageResult> getPicVoListByUserCreated(PicParam param);
    Attitude getPicoltAttitudeByParam(PicoltDetailParam param);
    boolean isPicoltAttitudeExist(PicoltDetailParam param);
    boolean isPicAttitudeExist(PicDetailParam param);
    boolean insertPicoltAttitude(PicoltDetailParam param);
    boolean insertPicAttitude(PicDetailParam param);
    boolean updatePicoltAttitude(PicoltDetailParam param);
    boolean updatePicAttitude(PicDetailParam param);
    //插入图集信息
    boolean insertPicolt(Picolt picolt);
    //批量插入图片
    boolean insertPics(@Param("list") List<Pic> picList);
    //批量插入图集与各图片的关系
    boolean insertRelationPicoltPicContain(String picolt_id,@Param("list") List<Pic> picList);
    //插入用户图集创建关系
    boolean insertRelationUserPicoltCreate(String user_id,String picolt_id);
    //批量插入用户和各图片创建关系
    boolean insertRelationUserPicCreate(String user_id,@Param("list") List<Pic> picList);
}
