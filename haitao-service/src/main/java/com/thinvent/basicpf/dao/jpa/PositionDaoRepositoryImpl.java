package com.thinvent.basicpf.dao.jpa;

import java.util.ArrayList;
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

import com.thinvent.basicpf.model.Position;
import com.thinvent.library.vo.PositionVO;

@Repository
public class PositionDaoRepositoryImpl {
	@PersistenceContext
    private EntityManager entityManager;
	
	private static final String POSITIONLIST = new String("positionList").intern();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map listAllPositionByCondition(Pageable pageable, String posName, String posType) {
		Map map = queryBuilder(pageable, Position.class, posName, posType);
		List<Position> positionList = (List<Position>) map.get(POSITIONLIST);
		List<PositionVO> vos = new ArrayList<>();
		for(Position position : positionList) {
			PositionVO positionVO = new PositionVO();
			BeanUtils.copyProperties(position, positionVO);
			vos.add(positionVO);
		}
		
		map.put(POSITIONLIST, vos);
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map queryBuilder(Pageable pageable, Class clazz, String posName, String posType) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(clazz);
		From from = criteriaQuery.from(clazz);
		
		List<Predicate> predicates = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		
		predicates.add(criteriaBuilder.equal(from.get("enable"), 1));
		
		if( null != posName && !"".equals(posName) ) {
			predicates.add(criteriaBuilder.like(from.get("posName"), "%" + posName + "%"));
		}

		if( null != posType && !"".equals(posType) ) {
			predicates.add(criteriaBuilder.equal(from.get("posType"), posType));
		}
		
		orders.add(criteriaBuilder.asc(from.get("posSort")));
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		if(!orders.isEmpty()) {
			criteriaQuery.orderBy(orders);
		}
		
		Query query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		List<Position> positions = query.getResultList();
		Map map = new HashMap<>();
		map.put("positionList", positions);
		
		criteriaQuery.select(criteriaBuilder.count(from));
		query = entityManager.createQuery(criteriaQuery);
		Long count = (Long)query.getResultList().get(0);
		map.put("count", count);
		
		return map;
	}
}
