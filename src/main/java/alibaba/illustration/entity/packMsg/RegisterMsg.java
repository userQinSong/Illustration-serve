package alibaba.illustration.entity.packMsg;

import lombok.Data;

@Data
public class RegisterMsg {
    private String user_id;
    private String phone_number;
    private String password;
    private String user_name;
    private String id_card;
}
