package com.thinvent.basicpf.dao.jpa;

import com.thinvent.basicpf.model.Role;
import com.thinvent.library.vo.RoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleDaoRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String ROLELIST = new String("roleList").intern();
    public Map listAllRoleByCondition(Pageable pageable, String roleName, String roleType) {
        Map map = queryBuilder(pageable, Role.class, roleName, roleType);
        List<Role> roleList = (List<Role>) map.get(ROLELIST);
        List<RoleVO> vos = new ArrayList<>();
        for(Role role : roleList) {
            RoleVO roleVO = new RoleVO();
            BeanUtils.copyProperties(role, roleVO);
            vos.add(roleVO);
        }

        map.put(ROLELIST, vos);
        return map;
    }

    private Map queryBuilder(Pageable pageable, Class clazz, String roleName, String roleType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(clazz);
        From from = criteriaQuery.from(clazz);

        List<Predicate> predicates = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(from.get("enable"), 1));

        if( null != roleName && !"".equals(roleName) ) {
            predicates.add(criteriaBuilder.like(from.get("roleName"), "%" + roleName + "%"));
        }

        if( null != roleType && !"".equals(roleType) ) {
            predicates.add(criteriaBuilder.equal(from.get("roleType"), roleType));
        }

        orders.add(criteriaBuilder.asc(from.get("id")));
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if(!orders.isEmpty()) {
            criteriaQuery.orderBy(orders);
        }

        Query query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Role> roles = query.getResultList();
        Map map = new HashMap<>();
        map.put("roleList", roles);

        criteriaQuery.select(criteriaBuilder.count(from));
        query = entityManager.createQuery(criteriaQuery);
        Long count = (Long)query.getResultList().get(0);
        map.put("count", count);

        return map;
    }
}
