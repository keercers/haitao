package com.thinvent.basicpf.dao.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.thinvent.basicpf.model.User;
import com.thinvent.library.vo.UserVO;

@Repository
public class UserDaoRepositoryImpl {

	@PersistenceContext
    private EntityManager entityManager;
	
	private static final String USERLIST = new String("userList").intern();
	
	public Map listAllUserByCondition(Pageable pageable, List<String> deptIds, 
			List<String> posIds, String loginName, String userName) {
		Map map = queryBuilder(pageable, User.class, deptIds, posIds, loginName, userName);
		List<User> userList = (List<User>) map.get(USERLIST);
		List<UserVO> vos = new ArrayList<>();
		for(User user : userList) {
			UserVO userVO = new UserVO();
			BeanUtils.copyProperties(user, userVO);
			vos.add(userVO);
		}
		
		map.put(USERLIST, vos);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	private Map queryBuilder(Pageable pageable, Class clazz, List<String> deptIds, 
			List<String> posIds, String loginName, String userName) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(clazz);
		From from = criteriaQuery.from(clazz);
		
		List<Predicate> predicates = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		
		predicates.add(criteriaBuilder.equal(from.get("enable"), 1));
		
		if(!StringUtils.isEmpty(loginName)) {
			predicates.add(criteriaBuilder.like(from.get("loginName"), "%" + loginName + "%"));
		}

		if(!StringUtils.isEmpty(userName)) {
			predicates.add(criteriaBuilder.like(from.get("userName"), "%" + userName + "%"));
		}
		
		if(!deptIds.isEmpty()) {
			Iterator<String> it = deptIds.iterator();
			In inDepts = criteriaBuilder.in(from.get("depId"));
			while(it.hasNext()) {
				inDepts.value(it.next());
			}
			predicates.add(inDepts);
		}
		
		if(!posIds.isEmpty()) {
			In inPosIds = criteriaBuilder.in(from.get("posId"));
			Iterator<String> it = posIds.iterator();
			while(it.hasNext()) {
				inPosIds.value(it.next());
			}
			predicates.add(inPosIds);
		}
		
		orders.add(criteriaBuilder.asc(from.get("id")));
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		if(!orders.isEmpty()) {
			criteriaQuery.orderBy(orders);
		}
		
		Query query = entityManager.createQuery(criteriaQuery);
		if(!StringUtils.isEmpty(pageable)) {
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		
		List<User> users = query.getResultList();
		Map map = new HashMap<>();
		map.put("userList", users);
		
		criteriaQuery.select(criteriaBuilder.count(from));
		query = entityManager.createQuery(criteriaQuery);
		Long count = (Long)query.getResultList().get(0);
		map.put("count", count);
		
		return map;
	}
}
