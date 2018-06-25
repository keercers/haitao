package com.thinvent.zhhd.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.thinvent.library.Constants;
import com.thinvent.library.config.ServiceConfig;
import com.thinvent.zhhd.util.KettleUtil;

@Component
public class kettleTaskTimer {

	private static Logger LOGGER = LogManager.getLogger(kettleTaskTimer.class.getName());
	
	static String kettleUrl = ServiceConfig.getServiceConfig(Constants.zhhdBaseService, "zhhd-config.reportUrl.url");

	public void run(String transFileName, String[] params) throws Exception {
		LOGGER.info("*****kettle全量入库定时任务运行开始******");
		KettleUtil.callNativeTransWithParams(params, transFileName);
		LOGGER.info("*****kettle全量入库定时任务运行结束******");
	}
	
	public static Map<String, String> getDate() {
		Date today = new Date();
		Date date = new Date(today.getTime() - 86400000L);// 86400000L，它的意思是说1天的时间=24小时
															// x 60分钟 x 60秒 x
															// 1000毫秒 单位是L。
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String year = c.get(Calendar.YEAR) + "";
		int month = c.get(Calendar.MONTH) + 1;
		String smonth = null;
		if (month < 10) {
			smonth = "0" + month;
		} else {
			smonth = month + "";
		}
		int day = c.get(Calendar.DATE);
		String sday = null;
		if (day < 10) {
			sday = "0" + day;
		} else {
			sday = day + "";
		}
		Map<String, String> map = new HashMap<>();
		map.put("monthParams", year + "-" + smonth);
		map.put("dayParams", year + "-" + smonth + "-" + sday);
		return map;
	}

	@Scheduled(cron = "0 10 0 * * ?")
	public static void executeDayTask() {
		// 每日00:10:00执行增量入库定时任务
		kettleTaskTimer job = new kettleTaskTimer();

		String[] monthParams = { getDate().get("monthParams") };
		String[] dayParams = { getDate().get("dayParams") };

		String ktrPath = kettleUrl + "kettle/";;
		String tfmadName = ktrPath + "TfMaxAvgMonAll.ktr";
		String tfsdName = ktrPath + "TfStatDayAll.ktr";
		String tfsmName = ktrPath + "TfStatMonAll.ktr";
		String tfodName = ktrPath + "TfOverlenDayAll.ktr";
		String tfomName = ktrPath + "TfOverlenMonAll.ktr";
		String tfsamName = ktrPath + "TfSumAvgMonAll.ktr";
		try {
			job.run(tfmadName, monthParams);
			job.run(tfsdName, dayParams);
			job.run(tfsmName, monthParams);
			job.run(tfodName, dayParams);
			job.run(tfomName, monthParams);
			job.run(tfsamName, monthParams);
		} catch (Exception e) {
			LOGGER.info("*****kettle增量入库定时任务运行异常******");
			e.printStackTrace();
		}
	}
}