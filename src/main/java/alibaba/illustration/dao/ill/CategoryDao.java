package alibaba.illustration.dao.ill;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {
    List<String> queryAllTags();
    List<String> queryAllTypes();
}
