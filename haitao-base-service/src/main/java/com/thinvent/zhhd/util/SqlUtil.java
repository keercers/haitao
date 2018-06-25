package com.thinvent.zhhd.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thinvent.zhhd.common.vo.AisDataVO;

@Repository
public class SqlUtil {

	@PersistenceContext
	public EntityManager entityManager;

	public Map getResult(String sql, List<Object> paramList) {

		Query query = entityManager.createNativeQuery(sql);
		for (int i = 1; i <= paramList.size(); i++) {
			query.setParameter(i, paramList.get(i - 1));
		}

		List<Object> list = query.getResultList();
		Map<Object, Object> map = new HashMap<>();
		map.put("list", list);
		return map;
	}

	public Map getAisData(String sql, List<Object> paramList) {

		Query query = entityManager.createNativeQuery(sql);
//		for (int i = 1; i <= paramList.size(); i++) {
//			query.setParameter(i, paramList.get(i - 1));
//		}

		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		
		List<AisDataVO> aisDataList = new ArrayList<>();
		int LENGTH = sql.contains("2015")?22:sql.contains("2016")?19:23;

		for (int i = 0; i < list.size(); i++) {
			AisDataVO a = new AisDataVO();
			a.setAisId((String) (list.get(i))[0]);
			a.setCnName((String) (list.get(i))[1]);
			a.setEnName((String) (list.get(i))[2]);
			a.setCallNo((String) (list.get(i))[3]);
			a.setMmsi((String) (list.get(i))[4]);
			a.setImo((String) (list.get(i))[5]);
			a.setShipCategory((String) (list.get(i))[6]);
			a.setDeviceType((String) (list.get(i))[7]);
			a.setShipLength(toFloat((list.get(i))[8]));
			a.setShipWidth(toFloat((list.get(i))[9]));
			a.setDraugth(toFloat((list.get(i))[10]));
			a.setLng(toDouble((list.get(i))[11]));
			a.setLat(toDouble((list.get(i))[12]));
			a.setShipHeading(toFloat((list.get(i))[13]));
			if (LENGTH == 22){
				a.setShipCourseDirection(toFloat((list.get(i))[14]));
				a.setSpeed(toFloat((list.get(i))[15]));
				a.setStatus((String) (list.get(i))[16]);
				a.setPort((String) (list.get(i))[17]);
				a.setGpsTime((Date)(list.get(i))[18]);
				a.setUserX(toDouble((list.get(i))[19]));
				a.setUserY(toDouble((list.get(i))[20]));
				a.setShipTurnRate(toFloat((list.get(i))[21]));
			} else if (LENGTH == 19) {
				a.setShipPath(toFloat((list.get(i))[14]));
				a.setSpeed(toFloat((list.get(i))[15]));
				a.setStatus((String) (list.get(i))[16]);
				a.setPort((String) (list.get(i))[17]);
				a.setGpsTime((Date)(list.get(i))[18]);
			} else if (LENGTH == 23) {
				a.setShipTurnRate(toFloat((list.get(i))[14]));
				a.setSpeed(toFloat((list.get(i))[15]));
				a.setStatus((String) (list.get(i))[16]);
				a.setPort((String) (list.get(i))[17]);
				a.setGpsTime((Date)(list.get(i))[18]);
				a.setCreateTime((Date)(list.get(i))[19]);
				a.setUserX(toDouble((list.get(i))[20]));
				a.setUserY(toDouble((list.get(i))[21]));
				a.setShipCourseDirection(toFloat((list.get(i))[22]));
			}
			aisDataList.add(a);
		}

		Map<Object, Object> map = new HashMap<>();
		map.put("list", aisDataList);
		return map;
	}
	
	public Map getAisDataGpsTime(String sql, List<Object> paramList) {

		Query query = entityManager.createNativeQuery(sql);
		for (int i = 1; i <= paramList.size(); i++) {
			query.setParameter(i, paramList.get(i - 1));
		}

		List<Date> list = query.getResultList();

		List<AisDataVO> aisDataList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			AisDataVO a = new AisDataVO();
			a.setGpsTime(new Date(list.get(i).getTime()));
			aisDataList.add(a);
		}

		Map<Object, Object> map = new HashMap<>();
		map.put("list", aisDataList);
		return map;
	}

	/**
	 * 防止null.toString()报错
	 * 
	 * @param o
	 * @return
	 */
	public Float toFloat(Object o) {
		if (o == null) {
			return null;
		}
		return Float.parseFloat(o.toString());
	}
	public Double toDouble(Object o) {
		if (o == null) {
			return null;
		}
		return Double.parseDouble(o.toString());
	}
}
