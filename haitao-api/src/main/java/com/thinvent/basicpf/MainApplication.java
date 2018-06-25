package com.thinvent.basicpf;

import java.io.IOException;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.thinvent.basicpf.config.ZhhdAPIApplication;
import com.thinvent.basicpf.pubsub.ZhhdAPISubscribe;
import com.thinvent.library.config.InitService;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.library.util.FastDFSClientUtils;
import com.thinvent.library.util.StringUtil;

@Configuration
@SpringBootApplication
public class MainApplication extends WebMvcConfigurerAdapter{

	@SuppressWarnings({ "unchecked", "static-access" })
	public static void main(String[] args) throws IOException {
		InitService.init(new ZhhdAPISubscribe(), new ZhhdAPIApplication().moduleList, StringUtil.getConfigDir(args));
		SpringApplication application = new SpringApplication(MainApplication.class);
		application.setDefaultProperties(ServiceConfig.loadSpringConfig("zhhd-api"));
		FastDFSClientUtils.Init();
		application.run(args);
	}
	
	/**  
     * 文件上传配置  
     * @return  
     */ 
    @Bean 
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //文件最大  
        factory.setMaxFileSize("100MB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("100MB");  
        return factory.createMultipartConfig();  
    }
	
}
