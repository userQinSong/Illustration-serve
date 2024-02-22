package alibaba.illustration.entity.ill.pageParams.picolt;

import alibaba.illustration.common.pageHelper.PageParam;
import lombok.Data;


@Data
public class PicoltDetailParam extends PageParam {
    private String picolt_id;
    private String attitude_person_id;
    private String change_type;
    private Integer add_down_count;
}
