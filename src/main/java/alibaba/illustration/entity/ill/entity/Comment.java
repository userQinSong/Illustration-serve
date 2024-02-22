package alibaba.illustration.entity.ill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private Long id;       // 评论ID
    private String blogId;      // 博客ID
    private String content;       // 评论内容
    private String userId;          // 评论作者ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;      // 创建时间
    private Integer isDelete;     // 是否删除（0：未删除；1：已删除）
    private Long parentId;    // 父评论ID（被回复的评论）
    private Long rootParentId;      // 根评论ID（最顶级的评论）
}

