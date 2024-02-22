package alibaba.illustration.entity.ill.pageParams.pic;

import alibaba.illustration.common.pageHelper.PageParam;
import lombok.Data;


@Data
public class PicParam extends PageParam {
    /*非必须*/private String picolt_id;
    private String attitude_person_id;//指当前对该图片表达态度的用户（喜欢：0,1 收藏：0,1 下载：0,1）
    /*非必须*/private String user_id;
    /*非必须*/private int search_upload;//1代表搜索用户发布的内容,0代表不搜索发布的内容
    /*非必须*/private int search_like;//1代表搜索用户喜欢的内容,0代表不搜索喜欢的内容
}
