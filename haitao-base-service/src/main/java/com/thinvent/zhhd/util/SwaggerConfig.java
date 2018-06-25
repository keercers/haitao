package com.thinvent.zhhd.util;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/** * SwaggerConfig */
@Configuration
@EnableSwagger2
public class SwaggerConfig {	

    /** * SpringBoot默认已经将classpath:/META-INF/resources/和
     * 			classpath:/META-INF/resources/webjars/映射 * 所以该方法不需要重写，
     * 如果在SpringMVC中，可能需要重写定义（我没有尝试） * 重写该方法需要 extends WebMvcConfigurerAdapter * */

    /** * 可以定义多个组，比如本类中定义把test和demo区分开了 * （访问页面就可以看到效果了）
     *  //访问地址  * http://localhost:8080/swagger-ui.html#/*/
	
	public static List<Parameter> setHeader(){
		String auth = "swagger-api";
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("token").defaultValue(auth).description("免token验证")
			.modelRef(new ModelRef("string"))
			.parameterType("header")
			.required(false)
			.build();
		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(aParameterBuilder.build());
		return aParameters;
	}
	
    @Bean
    public Docket SysDictionaryGroupApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dictionary")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/dictionary/.*")))//过滤的接口
                .build()
                .apiInfo(SysDictionaryGroupApiInfo());
    }

    private ApiInfo SysDictionaryGroupApiInfo() {
        ApiInfo apiInfo = new ApiInfo("航道基础服务接口",//大标题
                "系统字典模块",//小标题
                "0.0.1",//版本
                "",
                "billpan",//作者
                "航道基础服务管理接口",//链接显示文字
                "http://localhost:9090/"//网站链接
        );
        return apiInfo;
    }
    
    @Bean
    public Docket reportViewApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("reportView")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/reportView/.*")))//过滤的接口
                .build()
                .apiInfo(reportViewApiInfo());
    }

    private ApiInfo reportViewApiInfo() {
        ApiInfo apiInfo = new ApiInfo("报表管理接口",//大标题
                "报表管理模块",//小标题
                "0.0.1",//版本
                "",
                "billpan",//作者
                "报表管理模块",//链接显示文字
                "http://localhost:9090/"//网站链接
        );
        return apiInfo;
    }
    
}
