package com.thinvent.zhhd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.thinvent.zhhd.common.vo.DictionaryItemVO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name="SysDictionaryItem")
@Accessors(chain = true)
@Setter
@Getter
public class SysDictionaryItem {
	
	@Id
	@Column(name="dictItemId",length=36,nullable=false)
	private String dictItemId;

	@Column(name="dictItemKey",length=20,nullable=false)
	private String dictItemKey;

	@Column(name="dictItemValue",length=50,nullable=false)
	private String dictItemValue;

	@Column(name="dictItemFullKey",length=50,nullable=false)
	private String dictItemFullKey;

	@Column(name="dictItemFullValue",length=100,nullable=false)
	private String dictItemFullValue;

	@Column(name="parentItemId",length=36,nullable=true)
	private String parentItemId;

	@Column(name="dictGroupId",length=20,nullable=false)
	private String dictGroupId;

	@Column(name="enabled",length=1,nullable=false)
	private int enabled;
	
	public DictionaryItemVO convertToDictionaryItemVO(){
		DictionaryItemVO dictionaryItemVO = new DictionaryItemVO();
        BeanUtils.copyProperties(this, dictionaryItemVO);
        return dictionaryItemVO;
	}
}
