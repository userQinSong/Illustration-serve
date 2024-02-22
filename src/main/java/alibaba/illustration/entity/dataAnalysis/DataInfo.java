package alibaba.illustration.entity.dataAnalysis;


import lombok.Data;


@Data
public class DataInfo {
    private String cigar_name;
    private String user_name;
    private String shop_name;
    private String producer;
    private double cigar_price;
    private double produce_price;
    private int customer_arrive_count;
    private int repertory;
    private int sold_count;
    private int age;
}
