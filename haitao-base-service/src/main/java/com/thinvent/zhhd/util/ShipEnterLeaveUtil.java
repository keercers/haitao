package com.thinvent.zhhd.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShipEnterLeaveUtil {

	@Autowired
	public SqlUtil sqlUtil;

	/**
	 * 根据入境时间查找与之对应的出境时间
	 * 
	 * @param mmsi
	 * @param enterTime
	 * @return
	 * @throws Exception
	 */
	public Date getLeaveTimeByEnterTime(String mmsi, Date enterTime) throws Exception {
		// 参数检查
		if (mmsi == null || "".equals(mmsi)) {
			throw new Exception("参数mmsi不能为空");
		}
		if (enterTime == null) {
			throw new Exception("enterTime不能为空");
		}
		Date nextEnterTime = getNextEnterTime(mmsi, enterTime);
		Date leaveTime = getLeaveTimeByTimeInterval(mmsi, enterTime, nextEnterTime);
		return leaveTime;
	}

	/**
	 * 根据入境时间获取下一次入境的时间
	 * 
	 * @param mmsi
	 * @param enterTime
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Date getNextEnterTime(String mmsi, Date enterTime) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ENTER_TIME FROM SHIP_ENTER_RECORD");
		sql.append(" WHERE MMSI = ? AND ENTER_TIME > ? AND ROWNUM = 1 ORDER BY ENTER_TIME ASC");
		List<Object> param = new ArrayList<Object>();
		param.add(mmsi);
		param.add(enterTime);
		Map<Object, Object> map = new HashMap<>();
		map = sqlUtil.getResult(sql.toString(), param);
		List<Object> list = (List) map.get("list");
		if (list.size() != 0) {
			return (Date) list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据时间区间查询该区间内的出境时间
	 * 
	 * @param mmsi
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Date getLeaveTimeByTimeInterval(String mmsi, Date startTime, Date endTime) {
		List<Object> param = new ArrayList<Object>();
		param.add(mmsi);
		param.add(startTime);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT LEAVE_TIME FROM SHIP_LEAVE_RECORD");
		sql.append(" WHERE MMSI = ? AND LEAVE_TIME > ?");
		if (endTime != null) {
			sql.append(" AND LEAVE_TIME < ?");
			param.add(endTime);
		}
		sql.append(" AND ROWNUM = 1 ORDER BY LEAVE_TIME ASC");

		Map<Object, Object> result = sqlUtil.getResult(sql.toString(), param);
		List<Object> list = (List) result.get("list");
		if (list.size() != 0) {
			return (Date) list.get(0);
		} else {
			return null;
		}
	}
}
