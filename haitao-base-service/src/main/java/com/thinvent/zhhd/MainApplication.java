package com.thinvent.zhhd;


import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.thinvent.library.config.InitService;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.library.util.StringUtil;
import com.thinvent.zhhd.basicpf.config.ZhhdBaseServiceApplication;
import com.thinvent.zhhd.basicpf.pubsub.ZhhdBaseServiceSubscribe;


@SpringBootApplication
@ServletComponentScan
@EnableScheduling // 启用定时任务
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class MainApplication extends WebMvcConfigurerAdapter{

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		InitService.init(new ZhhdBaseServiceSubscribe(), new ZhhdBaseServiceApplication().moduleList, StringUtil.getConfigDir(args));
		SpringApplication application = new SpringApplication(MainApplication.class);
		application.setDefaultProperties(ServiceConfig.loadSpringConfig("zhhd-base-service"));
		application.run(args);
	}
	
}


