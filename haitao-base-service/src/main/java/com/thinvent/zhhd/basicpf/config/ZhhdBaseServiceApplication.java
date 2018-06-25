package com.thinvent.zhhd.basicpf.config;

import com.thinvent.library.config.ApplicationConfig;

public class ZhhdBaseServiceApplication extends ApplicationConfig {
	
	public ZhhdBaseServiceApplication() { 
		moduleList.add("zhhd-base-service.spring");
		moduleList.add("zhhd-base-service.logging");
		moduleList.add("zhhd-base-service.server");
		moduleList.add("zhhd-base-service.basic");
	}
}
