package com.thinvent.zhhd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinvent.zhhd.model.SysDictionaryGroup;

public interface IDictionaryGroupDao extends JpaRepository<SysDictionaryGroup, Integer> {
	public List<SysDictionaryGroup> findAll();
}
