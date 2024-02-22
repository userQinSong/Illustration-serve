package alibaba.illustration.entity.ill.vo.analysis;

import lombok.Data;

@Data
public class TotalCalculationVo {
    private Long totalRemainTime;//总浏览时长
    private Long totalPicCount;//总插画数
    private Long totalUserCount;//总用户数
    private Long totalCommentCount;//总评论数
    private Long monthRemainTime;//月浏览时长
    private Long monthPicCount;//月插画数
    private Long monthUserCount;//月用户数
    private Long monthCommentCount;//月评论数
    private Long dayRemainTime;//日浏览时长
    private Long dayPicCount;//日插画数
    private Long dayUserCount;//日用户数
    private Long dayCommentCount;//日评论数
}
