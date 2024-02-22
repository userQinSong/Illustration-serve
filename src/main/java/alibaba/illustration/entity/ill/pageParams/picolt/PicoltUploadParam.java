package alibaba.illustration.entity.ill.pageParams.picolt;

import alibaba.illustration.common.pageHelper.PageParam;
import alibaba.illustration.entity.ill.entity.Pic;
import alibaba.illustration.entity.ill.entity.Picolt;
import lombok.Data;

import java.util.List;


@Data
public class PicoltUploadParam extends PageParam {
    //上传人id
    private String user_id;
    //图片列表
    private List<Pic> uploadPicList;
    //不完整的插画对象
    private Picolt picolt;
}
