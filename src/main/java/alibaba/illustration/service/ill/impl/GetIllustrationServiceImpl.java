package alibaba.illustration.service.ill.impl;

import alibaba.illustration.common.Date.DateGetter;
import alibaba.illustration.common.id_generator.AutoIdGenerator;
import alibaba.illustration.common.pageHelper.MyPageHelper;
import alibaba.illustration.common.tool.StringToolHelper;
import alibaba.illustration.dao.ill.IllustrationDao;
import alibaba.illustration.entity.ill.entity.Pic;
import alibaba.illustration.entity.ill.entity.Picolt;
import alibaba.illustration.entity.ill.entity.attitude.Attitude;
import alibaba.illustration.entity.ill.pageParams.pic.PicDetailParam;
import alibaba.illustration.entity.ill.pageParams.pic.PicParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltDetailParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltListParam;
import alibaba.illustration.entity.ill.pageParams.picolt.PicoltUploadParam;
import alibaba.illustration.entity.ill.pageResult.PicPageResult;
import alibaba.illustration.entity.ill.pageResult.PicoltPageResult;
import alibaba.illustration.entity.ill.vo.PicVo;
import alibaba.illustration.entity.ill.vo.PicoltVo;
import alibaba.illustration.service.ill.GetIllustrationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class GetIllustrationServiceImpl implements GetIllustrationService {
    @Autowired
    private IllustrationDao getIllustrationDao;

    @Override
    public PageInfo<PicoltPageResult> getPicoltList(PicoltListParam param) {
        List<PicoltPageResult> resList = getIllustrationDao.getPicoltList(param);
        for(PicoltPageResult item: resList){
            PicoltVo picolt = item.getPicolt();
            picolt.setPicolt_count(getIllustrationDao.getPicoltContainPicCount(picolt.getPicolt_id()));
            if(!StringToolHelper.checkString(param.getAttitude_person_id())){
                item.setAttitude(new Attitude());
            }
        }
        MyPageHelper<PicoltPageResult> pageHelper = new MyPageHelper<>();
        return pageHelper.toListPageInfo(resList,param.getPageNum(), param.getPageSize());
    }

    @Override
    public PageInfo<PicPageResult> getPicListUserCreated(PicParam param) {
        return PageHelper.startPage(param.getPageNum(), param.getPageSize())
                .doSelectPageInfo(() -> getIllustrationDao.getPicVoListByUserCreated(param));
    }

    @Override
    public PageInfo<PicPageResult> getPicListUserLiked(PicParam param) {
        return PageHelper.startPage(param.getPageNum(), param.getPageSize())
                .doSelectPageInfo(() -> getIllustrationDao.getPicVoListByUserLiked(param));
    }

    //获取某图集的所有图片
    @Override
    public List<PicVo> getPicListByPicoltId(PicParam param) {
        return getIllustrationDao.getPicVoListByPicoltId(param);
    }

    //获取某图集具体信息（包括当前登录用户对该图集的态度）
    @Override
    public PicoltPageResult getPicolt(PicoltDetailParam param) {
        PicoltPageResult picoltPageResult = getIllustrationDao.getPicolt(param.getPicolt_id());
        picoltPageResult.getPicolt().setPicolt_count(getIllustrationDao.getPicoltContainPicCount(param.getPicolt_id()));
        Attitude attitude = getIllustrationDao.getPicoltAttitudeByParam(param);
        if(attitude == null) attitude = new Attitude();
        picoltPageResult.setAttitude(attitude);
        String tag_list = picoltPageResult.getPicolt().getPicolt_tag();
        String[] tages = tag_list.split(",");
        picoltPageResult.setTagArray(tages);
        //组装picolt的创造者信息
        return picoltPageResult;
    }

    @Override
    public boolean changeLoginUserPicoltAttitude(PicoltDetailParam param) {
        if(getIllustrationDao.isPicoltAttitudeExist(param))//先判断用户是否对该图集已建立态度
            return getIllustrationDao.updatePicoltAttitude(param);//有的话，直接修改
        else
            return getIllustrationDao.insertPicoltAttitude(param);//没有的话当即插入态度
    }

    @Override
    public boolean changeLoginUserPicAttitude(PicDetailParam param) {
        if(getIllustrationDao.isPicAttitudeExist(param))//先判断用户是否对该图集已建立态度
            return getIllustrationDao.updatePicAttitude(param);//有的话，直接修改
        else
            return getIllustrationDao.insertPicAttitude(param);//没有的话当即插入态度
    }

    @Override
    public boolean uploadPicolt(PicoltUploadParam param) {
        //先获取当前上传时间
        Date date = DateGetter.getCurrentTime();
        //设置每个待上传的图片其上传时间为当前时间
        List<Pic> uploadList = param.getUploadPicList();
        for(Pic pic:uploadList){
            pic.setAdd_time(date);
        }
        //先获取一个图集id,并设置于param的picolt信息中去
        Picolt picolt = param.getPicolt();
        String picolt_id = new AutoIdGenerator("picolt").getNewId();
        picolt.setPicolt_id(picolt_id);
        picolt.setCreate_time(date);
        picolt.setRenew_time(date);

        //当四个插入都返回true时返回true,其中有一个错误，就返回false
        return getIllustrationDao.insertPics(uploadList)//批量插入图片至数据库中
                && getIllustrationDao.insertPicolt(param.getPicolt())//插入图集信息至数据库
                && getIllustrationDao.insertRelationPicoltPicContain(picolt_id,uploadList)//批量插入图集与图片的包含关系至数据库
                && getIllustrationDao.insertRelationUserPicCreate(param.getUser_id(), uploadList)//批量插入用户图片创建关系
                && getIllustrationDao.insertRelationUserPicoltCreate(param.getUser_id(), picolt_id);//插入用户图集创建关系
    }
}
