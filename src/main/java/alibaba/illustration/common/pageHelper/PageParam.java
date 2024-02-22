package alibaba.illustration.common.pageHelper;


import lombok.Data;

@Data
public class PageParam {
    //页码
    private Integer pageNum = 1;
    //页数，我这里设置默认设置2，一般是10，20
    private Integer pageSize = 2;
    //排序
    private String orderBy;
}
