package com.thinvent.zhhd.util;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.library.Constants;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.mq.entity.KaMessage;
import com.thinvent.library.util.GetPostUtil;

public class PutMessageToKafkaUtil {
	public static void sendMessage(KaMessage kaMessage) throws ThinventBaseException{
		String baseUrl = ServiceConfig.getServiceConfig(Constants.zhhdBaseService, Constants.messageServiceUrlKey);
		String url = baseUrl + "message/push";
		String jsonMsg = JSONObject.toJSONString(kaMessage);
		GetPostUtil.sendPost(url, jsonMsg);
	}
}
