package com.thinvent.basicpf.config;
import com.thinvent.library.config.ApplicationConfig;

public class ZhhdAPIApplication extends ApplicationConfig {
	
	public ZhhdAPIApplication() { 
		moduleList.add("zhhd-api.spring");
		moduleList.add("zhhd-api.logging");
		moduleList.add("zhhd-api.server");
		moduleList.add("zhhd-api.basic");
		moduleList.add("fastdfs_client.conf");
	}
}
