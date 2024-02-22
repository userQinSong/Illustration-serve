package alibaba.illustration.entity.ill.vo.analysis;

import lombok.Data;

@Data
public class ChartVo implements Comparable<ChartVo>{
    private String group_name;
    private Long group_value;

    @Override
    public int compareTo(ChartVo compare) {
        return (int) (this.group_value - compare.getGroup_value());
    }
}
