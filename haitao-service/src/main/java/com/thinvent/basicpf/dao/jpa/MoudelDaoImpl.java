package com.thinvent.basicpf.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thinvent.basicpf.model.Moudle;

@Repository
public class MoudelDaoImpl {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Moudle> getListMoudleByUserId(String userId) {
		
		 Query query = entityManager.createQuery("select new Moudle(m) from Moudle m, RoleMoudle rm, UserRole ur where ur.userId = '" + userId + "' "
		 		+ "and ur.enable = '1' and ur.roleId = rm.roleId and rm.enable = '1' and rm.moudelId = m.moudleId and m.enable='1'");
		 
		 return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Moudle> getForbidListMoudleByUserId(String userId) {
		
		 Query query = entityManager.createQuery("select new Moudle(m) from Moudle m, RoleMoudle rm, UserRole ur where ur.userId = '" + userId + "' "
		 		+ "and ur.enable = '1' and ur.roleId = rm.roleId and rm.enable = '1' and rm.moudelId = m.moudleId and m.moudleType='3' and m.enable = '1'");
		 
		 return query.getResultList();
	}
}
