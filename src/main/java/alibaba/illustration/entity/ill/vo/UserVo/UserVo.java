package alibaba.illustration.entity.ill.vo.UserVo;

import alibaba.illustration.entity.ill.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 评论信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends User {
    //所有上传的图片被喜欢数
    private Long picolt_be_like_count;
    //所有上传的图片被收藏数
    private Long picolt_be_collect_count;
    //所有上传的图片被下载数
    private Long picolt_be_down_count;
    //所有上传的图集被喜欢数
    private Long pic_be_like_count;
    //所有上传的图集被收藏数
    private Long pic_be_collect_count;
    //所有上传的图集被下载数
    private Long pic_be_down_count;
    //所有当前用户所关注的其他用户数
    private Long attention;
    //所有当前用户所被关注的其他用户数
    private Long be_attention;
    //所有当前用户上传的图集数
    private Long picolt_upload;
    //所有当前用户上传的图片数
    private Long pic_upload;
}

