package alibaba.illustration.entity.ill.pageResult;

import alibaba.illustration.common.pageHelper.PageParam;
import alibaba.illustration.entity.ill.entity.User;
import alibaba.illustration.entity.ill.entity.attitude.Attitude;
import alibaba.illustration.entity.ill.vo.PicoltVo;
import lombok.Data;

@Data
public class PicoltPageResult extends PageParam {
    private PicoltVo picolt;
    private User creator;
    private String[] tagArray;
    private Attitude attitude;
}
