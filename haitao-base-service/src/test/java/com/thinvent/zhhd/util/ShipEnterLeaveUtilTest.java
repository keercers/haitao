package com.thinvent.zhhd.util;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;

public class ShipEnterLeaveUtilTest {

	String mmsi = "413792299";
	String findTimeS = "2015-11-25 17:54:42";

	@InjectMocks
	ShipEnterLeaveUtil shipEnterLeaveUtil;
	
	@Mock
	SqlUtil sqlUtil;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = Exception.class)
	public void getLeaveTimeByEnterTime() throws Exception {
		Date date = new Date();
		shipEnterLeaveUtil.getLeaveTimeByEnterTime("", date);
	}
	
	@Test(expected = Exception.class)
	public void getLeaveTimeByEnterTimeBranch() throws Exception {
		Date date = new Date();
		shipEnterLeaveUtil.getLeaveTimeByEnterTime(null, date);
	}
	
	@Test(expected = Exception.class)
	public void getLeaveTimeByEnterTimeBranchThree() throws Exception {
		shipEnterLeaveUtil.getLeaveTimeByEnterTime("test", null);
	}
	
	@Test
	public void getLeaveTimeByEnterTimeBranchFour() throws Exception {
		Date date = new Date();
		Map<Object, Object> map = new HashMap<>();
		List<Object> list = Lists.newArrayList();
		list.add(date);
		map.put("list", list);
		List<Object> param = new ArrayList<Object>();
		param.add("test");
		param.add(date);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ENTER_TIME FROM SHIP_ENTER_RECORD");
		sql.append(" WHERE MMSI = ? AND ENTER_TIME > ? AND ROWNUM = 1 ORDER BY ENTER_TIME ASC");
		when(sqlUtil.getResult(sql.toString(), param)).thenReturn(map);
		
		StringBuilder sql1 = new StringBuilder();
		sql1.append("SELECT LEAVE_TIME FROM SHIP_LEAVE_RECORD");
		sql1.append(" WHERE MMSI = ? AND LEAVE_TIME > ?");
		sql1.append(" AND LEAVE_TIME < ?");
		List<Object> param1 = new ArrayList<Object>();
		param1.add("test");
		param1.add(date);
		param1.add(date);
		sql1.append(" AND ROWNUM = 1 ORDER BY LEAVE_TIME ASC");
		when(sqlUtil.getResult(sql1.toString(), param1)).thenReturn(map);
		shipEnterLeaveUtil.getLeaveTimeByEnterTime("test", date);
	}

	@Test
	public void getNextEnterTime() throws Exception {
		Date date = new Date();
		Map<Object, Object> map = new HashMap<>();
		List<Object> list = Lists.newArrayList();
		map.put("list", list);
		List<Object> param = new ArrayList<Object>();
		param.add("test");
		param.add(date);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ENTER_TIME FROM SHIP_ENTER_RECORD");
		sql.append(" WHERE MMSI = ? AND ENTER_TIME > ? AND ROWNUM = 1 ORDER BY ENTER_TIME ASC");
		when(sqlUtil.getResult(sql.toString(), param)).thenReturn(map);
		shipEnterLeaveUtil.getNextEnterTime("test", date);
		
		list.add(date);
		map.put("list", list);
		when(sqlUtil.getResult(sql.toString(), param)).thenReturn(map);
		shipEnterLeaveUtil.getNextEnterTime("test", date);
	}

	@Test
	public void getLeaveTimeByTimeInterval() throws Exception {
		Map<Object, Object> map = new HashMap<>();
		List<Object> list = Lists.newArrayList();
		map.put("list", list);
		List<Object> param = new ArrayList<Object>();
		Date date = new Date();
		param.add("test");
		param.add(date);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT LEAVE_TIME FROM SHIP_LEAVE_RECORD");
		sql.append(" WHERE MMSI = ? AND LEAVE_TIME > ?");
		sql.append(" AND ROWNUM = 1 ORDER BY LEAVE_TIME ASC");
		
		when(sqlUtil.getResult(sql.toString(), param)).thenReturn(map);
		shipEnterLeaveUtil.getLeaveTimeByTimeInterval("test", date, null);
		
		List<Object> param1 = new ArrayList<Object>();
		param1.add("test");
		param1.add(date);
		StringBuilder sql1 = new StringBuilder();
		sql1.append("SELECT LEAVE_TIME FROM SHIP_LEAVE_RECORD");
		sql1.append(" WHERE MMSI = ? AND LEAVE_TIME > ?");
		Date endTime = new Date();
		sql1.append(" AND LEAVE_TIME < ?");
		param1.add(endTime);
		sql1.append(" AND ROWNUM = 1 ORDER BY LEAVE_TIME ASC");
		when(sqlUtil.getResult(sql1.toString(), param1)).thenReturn(map);
		shipEnterLeaveUtil.getLeaveTimeByTimeInterval("test", date, endTime);
		
		list.add(date);
		map.put("list", list);
		when(sqlUtil.getResult(sql1.toString(), param1)).thenReturn(map);
		shipEnterLeaveUtil.getLeaveTimeByTimeInterval("test", date, endTime);
		
	}

}
