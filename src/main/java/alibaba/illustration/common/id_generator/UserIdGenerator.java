package alibaba.illustration.common.id_generator;

public class UserIdGenerator extends IdGenerator{
    private String user_str = "user_";
    public String getUserNewId(){
        return user_str + super.getUUID();
    }
}
