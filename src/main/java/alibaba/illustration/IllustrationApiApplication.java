
package alibaba.illustration;

import alibaba.illustration.common.tool.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MapperScan("alibaba.illustration.dao")
@Import(SpringUtil.class)//让自定义的工具类自动导入
@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties
@EnableAsync
public class IllustrationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IllustrationApiApplication.class, args);
    }

}
