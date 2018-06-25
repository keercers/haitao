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

import com.thinvent.basicpf.dao.IButtomRegisterDao;
import com.thinvent.basicpf.handler.IButtomRegisterHandler;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.basicpf.util.ModuleUtil;
import com.thinvent.basicpf.util.MoudleVerifyUtil;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ButtomRegisterHandlerImpl implements IButtomRegisterHandler {

    @Autowired
    private IButtomRegisterDao buttomRegisterDao;

	@Autowired
	private ModuleUtil moduleUtil;

	@Override
	public Object buttomRegisterList(String moudelId, String moudelName, int pageIndex, int pageSize) {
		PageRequest pageRequest = new PageRequest(pageIndex-1, pageSize);
		return this.buttomRegisterDao.queryByMoudleParentIdAndMoudleNameLikeAndMoudleTypeAndEnable(moudelId, "%" + moudelName + "%", "3", 1, pageRequest);
	}

	@Override
	public void buttomRegisterDel(String moudleId) throws ThinventBaseException {
		List<Moudle> list = buttomRegisterDao.findByMoudleParentIdAndEnable(moudleId, 1);
		MoudleVerifyUtil.verifyListChilds(list);
		Moudle moudle = buttomRegisterDao.findByMoudleIdAndEnable(moudleId, 1);
		moudle.setEnable(0);
		buttomRegisterDao.save(moudle);
		moduleUtil.clearRoleModule(moudleId);
	}

    @Override
    public Moudle buttomRegisterOne(String moudleId) {
    	return this.buttomRegisterDao.findByMoudleIdAndEnable(moudleId, 1);
	}
    
    @Override
    public String buttomRegisterAdd(MoudleVO moudleVO) throws ThinventBaseException{
    	Moudle moudle = new Moudle();
    	Moudle moudleParent = this.buttomRegisterDao.findByMoudleIdAndEnable(moudleVO.getMoudleParentId(), 1);
    	MoudleVerifyUtil.verifyMoudleParent(moudleParent);
    	BeanUtils.copyProperties(moudleVO, moudle);
    	String moudleId = findMaxButtomRegisterId();
    	moudle.setMoudleId(moudleId);
    	moudle.setMoudleSign((moudleParent.getMoudleSign()==null?"":moudleParent.getMoudleSign())+moudleParent.getMoudleId()+"-");
    	moudle.setMoudleLevel(String.valueOf(Integer.parseInt(moudleParent.getMoudleLevel())+1));
    	moudle.setMoudleType("3");
    	this.buttomRegisterDao.save(moudle);
    	moudleVO.setMoudleId(moudleId);
    	moduleUtil.initRoleModule(moudleVO);
    	return Constants.RES_SUCCESS;
    }
	
    @Override
    public String findMaxButtomRegisterId(){
    	List<Moudle> moudleList = this.buttomRegisterDao.findAll();
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
	public Map<String, String> buttomRegisterUpdate(MoudleVO moudleVO) throws ThinventBaseException{
    	Moudle moudle = this.buttomRegisterDao.findByMoudleIdAndEnable(moudleVO.getMoudleId(), 1);
    	MoudleVerifyUtil.verifyMoudle(moudle);
    	Map<String, String> map = new HashMap<>();
		BeanUtils.copyProperties(moudleVO, moudle);
		this.buttomRegisterDao.save(moudle);
		map.put("status", Constants.RES_SUCCESS);
		map.put("msg", "修改菜单成功");
    	return map;
    }
}
