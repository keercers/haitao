package com.thinvent.basicpf.sys.util;

import com.thinvent.library.Constants;
import com.thinvent.library.config.ServiceConfig;

public class URLUtil {

	private URLUtil() {
		super();
	}

	public static String getUrl() {
		return ServiceConfig.getServiceConfig(Constants.zhhdBasicService, Constants.zhhdBasicUrlKey);
	}
	
}
