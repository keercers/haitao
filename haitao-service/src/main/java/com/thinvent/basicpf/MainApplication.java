package com.thinvent.basicpf;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.thinvent.basicpf.config.ZhhdServiceApplication;
import com.thinvent.basicpf.pubsub.ZhhdServiceSubscribe;
import com.thinvent.library.config.InitService;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.library.util.StringUtil;


@ComponentScan(value={"com.thinvent.*"})
@SpringBootApplication
public class MainApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) throws IOException {
		InitService.init(new ZhhdServiceSubscribe(), new ZhhdServiceApplication().moduleList, StringUtil.getConfigDir(args));
		SpringApplication application = new SpringApplication(MainApplication.class);
		application.setDefaultProperties(ServiceConfig.loadSpringConfig("zhhd-service"));
		application.run(args);
	}
}
