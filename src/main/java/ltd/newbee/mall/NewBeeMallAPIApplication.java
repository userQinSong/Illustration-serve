
package ltd.newbee.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MapperScan("ltd.newbee.mall.dao")
@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties
public class NewBeeMallAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewBeeMallAPIApplication.class, args);
    }

}
