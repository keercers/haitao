package com.thinvent.basicpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.thinvent.library.util.UUIDUtil;
import com.thinvent.library.vo.PositionVO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
@Entity
@Table(name = "m_sys_position")
@Accessors(chain = true)
@Setter
@Getter
public class Position {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
    private int id;
    @Column(name = "posId", length = 36, nullable = false)
    private String posId = UUIDUtil.getUuid(); // ID
    @Column(name = "posName", length = 125)
    private String posName; // 岗位名称
    @Column(name = "depId", length = 36)
    private String depId; // 所属部门
    @Column(name = "posLevel")
    private int posLevel; // 职级
    @Column(name = "posType", length = 20)
    private String posType; // 岗位类型0:全局岗位 1：部门岗位 2：个人岗位
    @Column(name = "posSort")
    private int posSort; // 岗位序号
    @Column(name = "enable", length = 2)
    private int enable = 1; // 是否可用
    @Column(name = "createUser", length = 36)
    private String createUser;
    @Column(name = "updateUser", length = 36)
    private String updateUser;
    @Column(name = "createTime")
    private Date createTime = new Date();
    @Column(name = "updateTime")
    private Date updateTime = new Date();
    public PositionVO convertToPositionVO() {
        PositionVO positionVO = new PositionVO();
        BeanUtils.copyProperties(this, positionVO);
        return positionVO;
    }
}
