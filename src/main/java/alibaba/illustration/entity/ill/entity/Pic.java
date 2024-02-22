package alibaba.illustration.entity.ill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Pic {
    private String pic_id;
    private String pic_src;
    private String pic_name;
    private String pic_type;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date add_time;
    //图像所占物理空间大小
    private Long pic_space_size;
}
