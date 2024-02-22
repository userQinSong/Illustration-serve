package alibaba.illustration.entity.ill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String user_id;
    private String user_name;
    private String password;
    private String phone_number;
    private String potrait_src;
    private int rank;
    private String big_back_img;
    private int gender;//性别：0:女,1:男
    private String brief;//个人简介
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date create_time;//账号创建时间
}
