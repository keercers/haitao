package com.thinvent.basicpf.dao;


import com.thinvent.basicpf.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IDepartmentDao extends CrudRepository<Department, Integer> {
    List<Department> findByEnable(int enable);

    List<Department> findByDepParentIdAndEnableOrderByDepSort(String depParentId, int enable);

    Department findOneByDepIdAndEnable(String depId, int enable);

    Department findOneByDepNameAndEnable(String depName, int enable);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Department d WHERE d.depParentId = ?1 AND d.enable = 1")
    Boolean existsByDepParentIdAndEnable(String depId);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Department d WHERE d.comId = ?1 AND d.enable = 1")
    Boolean existsByComIdAndEnable(String comId);

	List<Department> findByDepNameAndEnable(String deptName, int i);
}
