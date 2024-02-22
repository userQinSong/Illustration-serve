package alibaba.illustration.dao.ill;

import alibaba.illustration.entity.ill.entity.Comment;
import alibaba.illustration.entity.ill.relation.Relation_User_Comment_Attitude;
import alibaba.illustration.entity.ill.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    List<CommentVo> queryAllComments(String blogId);
    Long getCommentTotalCount(String blog_id);
    List<Relation_User_Comment_Attitude> queryAllBlogCommentAttitudes(String blog_id,String user_id);
    List<CommentVo> queryRootAllChilds(Long root_id);
    Long insertComment(Comment comment);//插入并返回评论id
    void insertCommentRelation(Comment comment);//插入评论关系
    int deleteById(Long comment_id);
    Relation_User_Comment_Attitude queryRelationAttitude(String user_id,Long comment_id);
    boolean insertRelationAttitude(String user_id,Long comment_id,int attitude);
    boolean updateRelationAttitude(Long relation_id,int attitude);
    CommentVo queryCommentById(Long comment_id);
}
