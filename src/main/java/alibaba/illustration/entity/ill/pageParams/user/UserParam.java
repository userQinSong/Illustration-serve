package alibaba.illustration.entity.ill.pageParams.user;

import alibaba.illustration.common.pageHelper.PageParam;
import lombok.Data;

import java.util.Date;


@Data
public class UserParam extends PageParam {
    private String user_id;//接受态度人的id
    private String attitude_person_id;//表达态度的人的id
    /*非必须*/private String search_user_type;//代表搜索的内容主题，比如粉丝，或者关注博主
    /*非必须*/private String edited_username;//修改的名字
    /*非必须*/private String edited_brief;//修改的简介
    /*非必须*/private String remain_time;//修改浏览总时长,用于记录用户浏览网页的时长
    /*非必须*/private Date create_time;//用于记录浏览时间存入的当前时间
}
