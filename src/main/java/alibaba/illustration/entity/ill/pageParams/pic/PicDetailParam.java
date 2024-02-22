package alibaba.illustration.entity.ill.pageParams.pic;

import alibaba.illustration.common.pageHelper.PageParam;
import lombok.Data;


@Data
public class PicDetailParam extends PageParam {
    private String pic_id;
    private String attitude_person_id;
    private String change_type;
    private Integer add_down_count;
}
