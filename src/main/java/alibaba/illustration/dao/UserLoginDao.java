package alibaba.illustration.dao;
import alibaba.illustration.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginDao {
    User login(String phone_number, String password);
    void register(String user_id,String phone_number,String user_name,String password,String id_card);
}
