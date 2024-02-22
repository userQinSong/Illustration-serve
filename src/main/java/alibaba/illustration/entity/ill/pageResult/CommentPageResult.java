package alibaba.illustration.entity.ill.pageResult;

import alibaba.illustration.common.pageHelper.PageParam;
import alibaba.illustration.entity.ill.vo.CommentVo;
import lombok.Data;

@Data
public class CommentPageResult extends PageParam {
    private CommentVo commentVo;
    private Long comment_count;
}
