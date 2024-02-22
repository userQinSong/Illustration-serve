package alibaba.illustration.entity.ill.vo.UserVo;

import alibaba.illustration.entity.ill.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConcernVo extends User {
    private String fans_id;
    private String attention_id;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date follow_time;
    //判断当前登录用户对当前用户是否关注：true为关注，false为不关注
    private boolean is_person_attention;
}
