package com.thinvent.basicpf.dao.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.thinvent.basicpf.model.Log;
import com.thinvent.library.vo.LogVO;

@Repository
public class LogDaoRepositoryImpl {

	@PersistenceContext
    private EntityManager entityManager;
	
	private static final String LOGLIST = "logList";
	
	public Map listLogByConditions(Pageable pageable, String logType, String userName, String system,
			Date maxDate, Date minDate) {
		Map map = queryBuilder(pageable, Log.class, logType, userName, system, maxDate, minDate);
		List<Log> logList = (List<Log>) map.get(LOGLIST);
		List<LogVO> vos = new ArrayList<>();
		for(Log log : logList) {
			LogVO logVO = new LogVO();
			BeanUtils.copyProperties(log, logVO);
			vos.add(logVO);
		}
		
		map.remove(LOGLIST);
		map.put(LOGLIST, vos);
		return map;
	}
	
	private Map queryBuilder(Pageable pageable, Class clazz, String logType, String userName, String system,
			Date maxDate, Date minDate) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(clazz);
		From from = criteriaQuery.from(clazz);
		
		List<Predicate> predicates = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		
		if( null != logType && !"".equals(logType) ) {
			predicates.add(criteriaBuilder.equal(from.get("logType"), logType));
		}
		
		if( null != userName && !"".equals(userName) ) {
			predicates.add(criteriaBuilder.equal(from.get("userName"), userName));
		}
		
		if( null != system && !"".equals(system) ) {
			predicates.add(criteriaBuilder.equal(from.get("system"), system));
		}
		
		if ( null != maxDate && null != minDate) {
			predicates.add(criteriaBuilder.between(from.get("logTime"), minDate, maxDate));
		}
		
		orders.add(criteriaBuilder.desc(from.get("id")));
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		if(!orders.isEmpty()) {
			criteriaQuery.orderBy(orders);
		}
		
		Query query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		List<Log> logs = query.getResultList();
		Map map = new HashMap<>();
		map.put("logList", logs);
		
		criteriaQuery.select(criteriaBuilder.count(from));
		query = entityManager.createQuery(criteriaQuery);
		Long count = (Long)query.getResultList().get(0);
		map.put("count", count);
		return map;
	}
}
