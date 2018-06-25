package com.thinvent.zhhd.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.thinvent.zhhd.common.vo.MonitorPointVO;

@Repository
public class ReportDaoRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	public MonitorPointVO getMpLocationByMpId(String mpId) {
		StringBuilder sql = new StringBuilder();
        sql.append(" select mp.mp_id, mp_name, mp.mp_location ");
        sql.append(" from monitor_point mp");
        if(!StringUtils.isEmpty(mpId)) {
            sql.append(" where mp.mp_id = '" + mpId + "' ");
        }

        Query query = entityManager.createNativeQuery(sql.toString());
        
        List<Object[]> result = query.getResultList();
        
    	MonitorPointVO vo = new MonitorPointVO();
    	vo.setMpId(toS(result.get(0)[0]));
    	vo.setMpName(toS(result.get(0)[1]));
    	vo.setMpLocation(toS(result.get(0)[2]));
        
        return vo;
	}
	
	private String toS(Object o) {
		if (o == null) {
			return "";
		} else {
			return o + "";
		}
	}
}
