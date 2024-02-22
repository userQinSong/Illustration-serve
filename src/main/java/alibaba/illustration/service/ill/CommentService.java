package alibaba.illustration.service.ill;

import alibaba.illustration.entity.ill.entity.Comment;
import alibaba.illustration.entity.ill.pageParams.comment.CommentListParam;
import alibaba.illustration.entity.ill.pageResult.CommentPageResult;
import alibaba.illustration.entity.ill.vo.CommentVo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface CommentService {
    PageInfo<CommentPageResult> getCommentList(CommentListParam param);
    List<CommentVo> queryAllComments(String blog_id, String user_id);
    List<CommentVo> queryAllCommentsInCurrentPage(CommentListParam param);
    void addComment(Comment comment);
    boolean removeComment(CommentVo comment);
    boolean changeAttitude(CommentVo commentVo);
    CommentVo getCommentById(Long id);
    Long getCommentTotalCount(String blog_id);
}
