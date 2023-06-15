
package ltd.mall618.mall.dao;

import ltd.mall618.mall.entity.IndexConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexConfigMapper {

    List<IndexConfig> findIndexConfigsByTypeAndNum(@Param("configType") int configType, @Param("number") int number);
}