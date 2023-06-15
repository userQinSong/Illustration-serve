
package ltd.mall618.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MapperScan("ltd.mall618.mall.dao")
@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties
public class MallAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAPIApplication.class, args);
    }

}
