package alibaba.illustration.entity.ill.vo;

import alibaba.illustration.entity.ill.entity.Pic;
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
public class PicVo extends Pic {
    private String creator_id;
    private Long like_count;
    private Long down_count;
    private Long collect_count;
}

