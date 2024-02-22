package alibaba.illustration.entity.ill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class Picolt {
    private String picolt_id;
    private String picolt_name;
    private String picolt_tag;
    private String picolt_cover;
    private String brief_intro;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date create_time;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date renew_time;
}
