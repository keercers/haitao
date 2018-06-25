package com.thinvent.basicpf.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.IMenuDao;
import com.thinvent.basicpf.handler.IMenuHandler;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.basicpf.util.ModuleUtil;
import com.thinvent.basicpf.util.MoudleVerifyUtil;
import com.thinvent.library.Constants;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.MoudleVO;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class MenuHandlerImpl implements IMenuHandler {

    @Autowired
    private IMenuDao menuDao;

	@Autowired
	private ModuleUtil moduleUtil;
	
	// 查找所有模块
	@Override
    public List<Moudle> menuAll() {
		List<String> moudleTypes = new ArrayList<>();
		moudleTypes.add("1");
		moudleTypes.add("2");
        return this.menuDao.findByEnableAndMoudleTypeIn(1, moudleTypes);
    }

	// 获取菜单列表
	@Override
	public Object menuList(String moudelName, int pageIndex, int pageSize) {
		PageRequest pageRequest = new PageRequest(pageIndex-1, pageSize);
		return this.menuDao.queryByMoudleNameLikeAndMoudleTypeAndEnable("%" + moudelName + "%", "2", 1, pageRequest);
	}

	// 删除菜单
	@Override
	public void menuDel(String moudleId) throws ThinventBaseException {
		List<Moudle> list = menuDao.findByMoudleParentIdAndEnable(moudleId, 1);
		Moudle moudle = menuDao.findByMoudleIdAndEnable(moudleId, 1);
		MoudleVerifyUtil.verifyListChilds(list);
		moudle.setEnable(0);
		menuDao.save(moudle);
		moduleUtil.clearRoleModule(moudleId);
	}
    
    // 保存菜单
    @Override
    public String menuAdd(MoudleVO moudleVO) throws ThinventBaseException{
    	Moudle moudleExist = menuDao.findByMoudleNameAndEnable(moudleVO.getMoudleName(), 1);
    	MoudleVerifyUtil.verifyMoudleRepeat(moudleExist);
    	Moudle moudle = new Moudle();
    	Moudle moudleParent = this.menuDao.findByMoudleIdAndEnable(moudleVO.getMoudleParentId(), 1);
    	MoudleVerifyUtil.verifyMoudleParent(moudleParent);
    	BeanUtils.copyProperties(moudleVO, moudle);
    	String moudleId = findMaxMenuId();
    	moudle.setMoudleId(moudleId);
    	moudle.setMoudleSign((moudleParent.getMoudleSign()==null?"":moudleParent.getMoudleSign())+moudleParent.getMoudleId()+"-");
    	moudle.setMoudleLevel(String.valueOf(Integer.parseInt(moudleParent.getMoudleLevel())+1));
    	moudle.setMoudleType("2");
    	this.menuDao.save(moudle);
    	moudleVO.setMoudleId(moudleId);
    	moduleUtil.initRoleModule(moudleVO);
    	return Constants.RES_SUCCESS;
    }
    
	// 查找模块数量
    @Override
    public String findMaxMenuId(){
    	List<Moudle> moudleList = this.menuDao.findAll();
    	int max = 0;
    	if(moudleList!=null&&!moudleList.isEmpty())
	    	for(Moudle moudle:moudleList){
	    		int moudleId = Integer.parseInt(moudle.getMoudleId());
	    		if(moudleId>max)
	    			max = moudleId;
	    	}
    	return String.valueOf(max+1);
    }
    
    // 获取单个菜单
    @Override
    public Moudle menuOne(String moudleId) {
    	return this.menuDao.findByMoudleIdAndEnable(moudleId, 1);
	}
    
    // 更新菜单
    @Override
	public Map<String, String> menuUpdate(MoudleVO moudleVO) throws ThinventBaseException{
    	Moudle moudle = this.menuDao.findByMoudleIdAndEnable(moudleVO.getMoudleId(), 1);
    	MoudleVerifyUtil.verifyMoudle(moudle);
    	String oldSign = moudle.getMoudleSign();
    	int oldLevel = Integer.parseInt(moudle.getMoudleLevel());
    	Map<String, String> map = new HashMap<>();
    	//如果父级部门没有改变
    	if(moudle.getMoudleParentId().equals(moudleVO.getMoudleParentId())){
    		BeanUtils.copyProperties(moudleVO, moudle);
    		this.menuDao.save(moudle);
    		map.put("status", Constants.RES_SUCCESS);
    		map.put("msg", "修改菜单成功");
    	}else{
    		//检验父级菜单是否存在
    		Moudle moudleParent = this.menuDao.findByMoudleIdAndEnable(moudleVO.getMoudleParentId(), 1);
    		MoudleVerifyUtil.verifyMoudleParent(moudleParent);
    		//更改自己的sign
    		moudleVO.setMoudleSign(moudleParent.getMoudleSign()+moudleParent.getMoudleId()+"-");
    		String newSign = moudleVO.getMoudleSign();
    		int newLevel = Integer.parseInt(moudleParent.getMoudleLevel())+1;
    		moudleVO.setMoudleLevel(String.valueOf(newLevel));
    		BeanUtils.copyProperties(moudleVO, moudle);
    		this.menuDao.save(moudle);
    		//查询所有子节点
    		List<Moudle> moudleList = this.menuDao.findByMoudleSignLikeAndEnable(oldSign+moudle.getMoudleId()+"-" + "%", 1);
    		//更改所有子节点
    		if(moudleList!=null&&!moudleList.isEmpty()&&oldSign.length()>0){
    			for(Moudle childMoudle:moudleList){
    				String sign = childMoudle.getMoudleSign().replaceAll(oldSign, newSign);
    				childMoudle.setMoudleSign(sign);
    				int level = Integer.valueOf(childMoudle.getMoudleLevel()) + newLevel - oldLevel;
    				childMoudle.setMoudleLevel(String.valueOf(level));
    				this.menuDao.save(childMoudle);
    			}
    		}
    		map.put("status", Constants.RES_SUCCESS);
    		map.put("msg", "修改菜单成功");
    	}
    	return map;
    }
}
