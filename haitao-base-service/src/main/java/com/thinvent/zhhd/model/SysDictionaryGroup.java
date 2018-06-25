package com.thinvent.zhhd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name="SysDictionaryGroup")
@Accessors(chain = true)
@Setter
@Getter
public class SysDictionaryGroup {
	
	@Id
	@Column(name="dictGroupId",length=20,nullable=false)
	private String dictGroupId;
	
	@Column(name="dictGroupName",length=50,nullable=false)
	private String dictGroupName;
	
	@Column(name="enabled",length=1,nullable=false)
	private int enabled;

}
