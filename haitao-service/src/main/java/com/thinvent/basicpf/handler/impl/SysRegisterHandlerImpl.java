package com.thinvent.basicpf.handler.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.ISysRegisterDao;
import com.thinvent.basicpf.handler.ISysRegisterHandler;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.basicpf.util.ModuleUtil;
import com.thinvent.basicpf.util.MoudleVerifyUtil;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class SysRegisterHandlerImpl implements ISysRegisterHandler {

    @Autowired
    private ISysRegisterDao sysRegisterDao;

	@Autowired
	private ModuleUtil moduleUtil;

	@Override
	public Object sysRegisterList(String moudelName, int pageIndex, int pageSize) {
		PageRequest pageRequest = new PageRequest(pageIndex-1, pageSize);
		return this.sysRegisterDao.queryByMoudleNameLikeAndMoudleTypeAndEnable("%" + moudelName + "%", "1", 1, pageRequest);
	}

	@Override
	public void sysRegisterDel(String moudleId) throws ThinventBaseException {
		List<Moudle> list = sysRegisterDao.findByMoudleParentIdAndEnable(moudleId, 1);
		Moudle moudle = sysRegisterDao.findByMoudleIdAndEnable(moudleId, 1);
		MoudleVerifyUtil.verifyListChilds(list);
		moudle.setEnable(0);
		sysRegisterDao.save(moudle);
		moduleUtil.clearRoleModule(moudleId);
	}

	@Override
    public String findMaxSysRegisterId(){
    	List<Moudle> moudleList = this.sysRegisterDao.findAll();
    	int max = 0;
    	if(moudleList!=null&&!moudleList.isEmpty())
	    	for(Moudle moudle:moudleList){
	    		int moudleId = Integer.parseInt(moudle.getMoudleId());
	    		if(moudleId>max)
	    			max = moudleId;
	    	}
    	return String.valueOf(max+1);
    }
    
    @Override
    public Moudle sysRegisterOne(String moudleId) {
    	return this.sysRegisterDao.findByMoudleIdAndEnable(moudleId, 1);
	}
    
    @Override
    public String sysRegisterAdd(MoudleVO moudleVO) throws ThinventBaseException{
    	Moudle moudle = new Moudle();
    	Moudle moudleParent = this.sysRegisterDao.findByMoudleIdAndEnable(moudleVO.getMoudleParentId(), 1);
    	MoudleVerifyUtil.verifyMoudleParent(moudleParent);
    	BeanUtils.copyProperties(moudleVO, moudle);
    	String moudleId = findMaxSysRegisterId();
    	moudle.setMoudleId(moudleId);
    	moudle.setMoudleSign((moudleParent.getMoudleSign()==null?"":moudleParent.getMoudleSign())+moudleParent.getMoudleId()+"-");
    	moudle.setMoudleLevel(String.valueOf(Integer.parseInt(moudleParent.getMoudleLevel())+1));
    	moudle.setMoudleType("1");
    	this.sysRegisterDao.save(moudle);
    	moudleVO.setMoudleId(moudleId);
    	moduleUtil.initRoleModule(moudleVO);
    	return Constants.RES_SUCCESS;
    }
    
    @Override
	public Map<String, String> sysRegisterUpdate(MoudleVO moudleVO) throws ThinventBaseException{
    	Moudle moudle = this.sysRegisterDao.findByMoudleIdAndEnable(moudleVO.getMoudleId(), 1);
    	MoudleVerifyUtil.verifyMoudle(moudle);
		BeanUtils.copyProperties(moudleVO, moudle);
		this.sysRegisterDao.save(moudle);
		Map<String, String> map = new HashMap<>();
		map.put("status", Constants.RES_SUCCESS);
		map.put("msg", "修改菜单成功");
    	return map;
    }
}
