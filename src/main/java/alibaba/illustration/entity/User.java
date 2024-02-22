package alibaba.illustration.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Actor{
    private String user_id;
    private String user_name;
    private String gender;
    private int age;
    private String phone_number;
    private int rank;
}
