package alibaba.illustration.entity.ill.relation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public abstract class Relation {
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    protected Date create_time;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    protected Date renew_time;
}
