package com.thinvent.basicpf.config;
import com.thinvent.library.config.ApplicationConfig;

public class ZhhdServiceApplication extends ApplicationConfig {
	
	public ZhhdServiceApplication() { 
		moduleList.add("zhhd-service.spring");
		moduleList.add("zhhd-service.logging");
		moduleList.add("zhhd-service.server");
		moduleList.add("zhhd-service.basic");
	}
}
