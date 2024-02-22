package alibaba.illustration.service.ill.impl;

import alibaba.illustration.common.Date.DateGetter;
import alibaba.illustration.common.pageHelper.MyPageHelper;
import alibaba.illustration.common.tool.StringToolHelper;
import alibaba.illustration.dao.ill.CommentDao;
import alibaba.illustration.entity.ill.entity.Comment;
import alibaba.illustration.entity.ill.pageParams.comment.CommentListParam;
import alibaba.illustration.entity.ill.relation.Relation_User_Comment_Attitude;
import alibaba.illustration.entity.ill.pageResult.CommentPageResult;
import alibaba.illustration.entity.ill.vo.CommentVo;
import alibaba.illustration.service.ill.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public PageInfo<CommentPageResult> getCommentList(CommentListParam param) {
        List<CommentPageResult> resList = commentVoListToPageResult(queryAllComments(param.getBlogId(),param.getUserId()));
        MyPageHelper<CommentPageResult> pageHelper = new MyPageHelper<>();
        return pageHelper.toListPageInfo(resList,param.getPageNum(), param.getPageSize());
    }

    @Override
    public List<CommentVo> queryAllComments(String blog_id, String user_id) {
        //先获取关于该博客的所有评论
        List<CommentVo> rowComments = commentDao.queryAllComments(blog_id);
        if(StringToolHelper.checkString(user_id)) {
            //再获取我关于该博客的所有点赞或踩的信息
            List<Relation_User_Comment_Attitude> relationList = commentDao.queryAllBlogCommentAttitudes(blog_id, user_id);
            //两者合并更新
            rowComments = attitudeDisplayComments(rowComments, relationList);
        }
        //构建评论树
        return treeFullComments(rowComments);
    }

    @Override
    public List<CommentVo> queryAllCommentsInCurrentPage(CommentListParam param) {
        List<CommentVo> allComments = this.queryAllComments(param.getBlogId(), param.getUserId());
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        int curTotalNum = pageNum * pageSize;
        if (curTotalNum >= allComments.size())
            return allComments;
        else
            return allComments.subList(0, curTotalNum);
    }

    @Override
    public void addComment(Comment comment) {
        comment.setCreateTime(DateGetter.getCurrentTime());
        comment.setIsDelete(0);
        commentDao.insertComment(comment);
        commentDao.insertCommentRelation(comment);
    }

    @Override
    public boolean removeComment(CommentVo Comment) {
        Queue<CommentVo> queue = new LinkedList<>();
        queue.offer(Comment);
        while(!queue.isEmpty()) {
            CommentVo cur = queue.poll();
            int resultNum = commentDao.deleteById(cur.getId());
            if(resultNum <= 0) return false;
            if(cur.getChild() != null) {
                List<CommentVo> child = cur.getChild();
                for(CommentVo tmp: child)
                    queue.offer(tmp);
            }
        }
        return true;
    }

    @Override
    public boolean changeAttitude(CommentVo commentVo) {
        Relation_User_Comment_Attitude relation = commentDao.queryRelationAttitude(commentVo.getUserId(),commentVo.getId());
        if(relation != null) {
           return commentDao.updateRelationAttitude(relation.getRelation_id(), commentVo.getAttitudeDisplay());
        }else {
           return commentDao.insertRelationAttitude(commentVo.getUserId(), commentVo.getId(), commentVo.getAttitudeDisplay());
        }
    }

    @Override
    public CommentVo getCommentById(Long id) {
        CommentVo root = commentDao.queryCommentById(id);
        if(root == null)
            return null;
        List<CommentVo> list = commentDao.queryRootAllChilds(id);
        list.add(root);
        return treeFullComments(list).get(0);
    }

    @Override
    public Long getCommentTotalCount(String blog_id) {
        return commentDao.getCommentTotalCount(blog_id);
    }

    /**
     * 构建评论树
     * @param list
     * @return
     */
    public static List<CommentVo> treeFullComments(List<CommentVo> list) {
        Map<Long, CommentVo> map = new HashMap<>();   // (id, Comment)
        List<CommentVo> result = new ArrayList<>();
        // 将所有根评论加入 map
        for(CommentVo commentVo : list) {
            if(commentVo.getParentId() == null)
                result.add(commentVo);
            map.put(commentVo.getId(), commentVo);
        }
        // 子评论加入到父评论的 child 中
        for(CommentVo commentVo : list) {
            Long id = commentVo.getParentId();
            if(id != null) {   // 当前评论为子评论
                Long root_id = commentVo.getRootParentId();
                CommentVo p = map.get(id);
                CommentVo root = map.get(root_id);
                if(p!=null){
                    if(p.getChild() == null)    // child 为空，则创建
                        p.setChild(new ArrayList<>());
                    p.getChild().add(commentVo);
                    commentVo.setParentName(p.getUserName());
                    root.setTotalChildCount(root.getTotalChildCount() + 1);
                }
            }
        }
        return result;
    }

    public List<CommentPageResult> commentVoListToPageResult(List<CommentVo> list){
        List<CommentPageResult> rootList = new ArrayList<>();
        for(CommentVo commentVo: list){
            CommentPageResult commentPage = new CommentPageResult();
            commentPage.setCommentVo(commentVo);
            commentPage.setComment_count(commentVo.getTotalChildCount());
            rootList.add(commentPage);
        }
        return rootList;
    }

    public Long getCommentCount(String blog_id){
        //获取评论总数
        return commentDao.getCommentTotalCount(blog_id);
    }

    public static List<CommentVo> attitudeDisplayComments(List<CommentVo> CommentList, List<Relation_User_Comment_Attitude> attitudeList){
        boolean[] chooseBox =  new boolean[attitudeList.size()];
        for(CommentVo Comment : CommentList){
            for(int i = 0 ; i < attitudeList.size() ; i++){
                if(!chooseBox[i] && attitudeList.get(i).getComment_id().equals(Comment.getId())){
                    Comment.setAttitudeDisplay(attitudeList.get(i).getAttitude());
                    chooseBox[i] = true;
                    break;
                }
            }
        }
        return CommentList;
    }
}
