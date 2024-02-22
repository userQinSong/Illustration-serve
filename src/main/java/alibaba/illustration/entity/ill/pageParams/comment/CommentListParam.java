package alibaba.illustration.entity.ill.pageParams.comment;

import alibaba.illustration.common.pageHelper.PageParam;
import lombok.Data;


@Data
public class CommentListParam extends PageParam {
    private String blogId;
    private String userId;
}
