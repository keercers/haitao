package com.thinvent.basicpf.dao;

import java.util.List;

import org.hibernate.criterion.CriteriaQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.thinvent.basicpf.model.Position;

public interface IPositionDao extends CrudRepository<Position, Integer> {

    Position findOneByPosIdAndEnable(String posId, int enable);

    Page<Position> findByPosTypeAndEnable(String posType, int enable, Pageable pageable);

    Page<Position> findByEnable(int enable, Pageable pageable);

    Page<Position> findByPosNameLikeAndEnable(String posName, int enable, Pageable pageable);

    @Query("from Position a where a.enable = 1 and a.posName like %?1%")
    List<Position> listPosByPosName(String posName, Pageable pageable);
    
    @Query("from Position a where a.enable = 1")
	public List<Position> listAllPositionByCondition(Pageable pageable, CriteriaQuery queryCondition);
    
    @Query("from Position a where a.enable = 1")
    List<Position> listAllPositionDistinctByName();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Position p WHERE p.depId = ?1 AND p.enable = 1")
    Boolean existsByDepIdAndEnable(String depId);
    
    Position findByPosNameAndPosTypeAndDepIdAndEnable(String posName, String posType, String depId, int enable);
    
    Position findByPosNameAndPosTypeAndDepIdAndEnableAndPosIdNot(String posName, String posType, String depId, int enable, String posId);

	Position findOneByPosNameAndPosTypeAndEnable(String posName, String posType, int i);
	
	Position findOneByPosNameAndEnable(String posName, int i);

	Position findByPosNameAndPosTypeAndDepIdOrDepIdIsNullAndEnable(String posName, String posType, String depId, int enable);

	Position findByPosNameAndPosTypeAndEnableAndDepIdIsNull(String posName, String posType, int enable);

	Position findByPosNameAndPosTypeAndDepIdIsNullAndEnableAndPosIdNot(String posName, String posType, int enable, String posId);

}
