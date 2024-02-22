package alibaba.illustration.entity.ill.vo;

import alibaba.illustration.common.pageHelper.PageParam;
import alibaba.illustration.entity.ill.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 评论信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo extends Comment {
    private String userName;      // 评论作者姓名
    private String parentName;
    private List<CommentVo> child;    // 本评论下的子评论
    private String potraitSrc; //用户头像
    private int attitudeDisplay;//用于前端对评论的点踩进行处理 （0：无态度，1：赞成，-1不赞成）
    private long totalChildCount;
    private Long likeCount;//喜欢人数
    private Long unlikeCount;//不喜欢人数
}

