package com.thinvent.basicpf.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thinvent.basicpf.model.RoleMoudle;

@Repository
public class RoleMoudelDaoImpl {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<RoleMoudle> getListRoleMoudleByUserId(String userId) {
		
		 Query query = entityManager.createQuery("select new RoleMoudle(rm) from RoleMoudle rm, UserRole ur where ur.userId = '" + userId + "' "
		 		+ "and ur.enable = '1' and rm.enable = '1' and ur.roleId = rm.roleId");
		 
		 return query.getResultList();
	}
}
