package alibaba.illustration.entity.ill.vo;

import alibaba.illustration.entity.ill.entity.Pic;
import alibaba.illustration.entity.ill.entity.Picolt;
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
public class PicoltVo extends Picolt {
    private Long picolt_count;
    private Long picolt_download;
    private Long picolt_love;
    private Long picolt_collect;
}

