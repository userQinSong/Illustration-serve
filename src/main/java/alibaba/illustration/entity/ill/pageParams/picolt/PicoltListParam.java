package alibaba.illustration.entity.ill.pageParams.picolt;

import alibaba.illustration.common.pageHelper.PageParam;

import lombok.Data;


@Data
public class PicoltListParam extends PageParam {
    private String user_name;
    private String picolt_name;
    private String picolt_type;
    private String creator_name;
    private String picolt_tag;
    private String attitude_person_id;
    /*非必须*/private String user_id;
    /*非必须*/private int search_upload;//1代表搜索用户发布的内容,0代表不搜索发布的内容
    /*非必须*/private int search_like;//1代表搜索用户喜欢的内容,0代表不搜索喜欢的内容
}
