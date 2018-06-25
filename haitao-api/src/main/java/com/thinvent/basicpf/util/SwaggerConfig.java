package com.thinvent.basicpf.util;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import com.thinvent.library.Constants;

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
	
	public static List<Parameter> setHeader(){
		String auth = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiODJjMGVmMy00NTY3LTQxMTItOTczOC0yOWJjOTNmZjZhOTIiLCJpYXQiOjE1MDIyNTc3MTQsInN1YiI6IntcInVzZXJJZFwiOlwiZWM4OTVhMzAtMWJlMC00OTAxLThjNGUtZmFlYWFkZjE5Zjk1XCJ9In0.iBXiiOGBgYY-CnBP3BgyHQasy8-UBPUcO2AexjHmHcA";
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name(Constants.TOKEN_KEY).defaultValue(auth).description("免token验证")
			.modelRef(new ModelRef("string"))
			.parameterType("header")
			.required(false)
			.build();
		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(aParameterBuilder.build());
		return aParameters;
	}
	
	private static final  String AUTHOR = "thinvent";	
	private static final  String WEBSITEURL = "http://localhost:9090/";	
	private static final  String VERSION = "0.0.1";	
	private static final  String BASEPLATFORM = "基础平台接口";
	private static final  String ZHHDBASEPLATFORM = "航道基础服务接口";	

    /** * SpringBoot默认已经将classpath:/META-INF/resources/和
     * 			classpath:/META-INF/resources/webjars/映射 * 所以该方法不需要重写，
     * 如果在SpringMVC中，可能需要重写定义（我没有尝试） * 重写该方法需要 extends WebMvcConfigurerAdapter * */

    /** * 可以定义多个组，比如本类中定义把test和demo区分开了 * （访问页面就可以看到效果了）
     *  //访问地址  * http://localhost:8080/swagger-ui.html#/*/
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/sys/user/.*")))//过滤的接口
                .build()
                .apiInfo(userApiInfo());
    }

    private ApiInfo userApiInfo() {
        return new ApiInfo(BASEPLATFORM,//大标题
                "用户模块",//小标题
                VERSION,//版本
                "",
                AUTHOR,//作者
                "基础平台",//链接显示文字
                WEBSITEURL//网站链接
        );
    }
    
    @Bean
    public Docket companyApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("company")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/sys/company/.*")))//过滤的接口
                .build()
                .apiInfo(companyApiInfo());
    }

    private ApiInfo companyApiInfo() {
        return new ApiInfo(BASEPLATFORM,//大标题
                "组织/公司/机构模块",//小标题
                VERSION,//版本
                "",
                "",//作者
                "基础平台",//链接显示文字
                WEBSITEURL//网站链接
        );
    }

    @Bean
    public Docket positionApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("position")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/sys/position/.*")))//过滤的接口
                .build()
                .apiInfo(positionApiInfo());
	}

    private ApiInfo positionApiInfo() {
        return new ApiInfo(BASEPLATFORM,//大标题
                "岗位模块",//小标题
                VERSION,//版本
                "",
                "",//作者
                "基础平台",//链接显示文字
                WEBSITEURL//网站链接
        );
    }
    
    @Bean
    public Docket moudleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("moudle")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/sys/moudle/.*")))//过滤的接口
                .build()
                .apiInfo(moudleApiInfo());
	}

    private ApiInfo moudleApiInfo() {
        return new ApiInfo(BASEPLATFORM,//大标题
                "菜单模块",//小标题
                VERSION,//版本
                "",
                "",//作者
                "基础平台",//链接显示文字
                WEBSITEURL//网站链接
        );
    }
    
    @Bean
    public Docket configApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Config")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/sys/sysconf/.*")))//过滤的接口
                .build()
                .apiInfo(configApiInfo());
	}

    private ApiInfo configApiInfo() {
        return new ApiInfo(BASEPLATFORM,//大标题
                "配置模块",//小标题
                VERSION,//版本
                "",
                "",//作者
                "基础平台",//链接显示文字
                WEBSITEURL//网站链接
        );
    }
    
    @Bean
    public Docket roleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("role")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/sys/role/.*")))//过滤的接口
                .build()
                .apiInfo(roleApiInfo());
	}

    private ApiInfo roleApiInfo() {
        return new ApiInfo(BASEPLATFORM,//大标题
                "角色模块",//小标题
                VERSION,//版本
                "",
                "",//作者
                "基础平台",//链接显示文字
                WEBSITEURL//网站链接
        );
    }
    
    @Bean
    public Docket sysDictionaryGroupApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dictionary")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/sys/dictionary/.*")))//过滤的接口
                .build()
                .apiInfo(sysDictionaryGroupApiInfo());
    }

    private ApiInfo sysDictionaryGroupApiInfo() {
        return new ApiInfo(ZHHDBASEPLATFORM,//大标题
                "系统字典模块",//小标题
                VERSION,//版本
                "",
                "",//作者
                "航道基础服务管理接口",//链接显示文字
                WEBSITEURL//网站链接
        );
    }
    
    @Bean
    public Docket monitoringApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("monitoring")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setHeader())
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/monitoring/.*")))//过滤的接口
                .build()
                .apiInfo(monitoringInfo());
    }

    private ApiInfo monitoringInfo() {
        return new ApiInfo(ZHHDBASEPLATFORM,//大标题
                "动态监测模块",//小标题
                VERSION,//版本
                "",
                "",//作者
                "动态监测模块接口",//链接显示文字
                WEBSITEURL//网站链接
        );
    }
	
}
