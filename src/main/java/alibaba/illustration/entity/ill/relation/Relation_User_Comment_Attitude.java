package alibaba.illustration.entity.ill.relation;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class Relation_User_Comment_Attitude extends Relation{
    private Long relation_id;//关系表id
    private String user_id;
    private Long comment_id;
    private int attitude;//0代表不评价，1代表赞成，-1代表踩
}
