package com.thinvent.basicpf.handler.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.thinvent.basicpf.dao.IMoudleDao;
import com.thinvent.basicpf.handler.IMoudleHandler;
import com.thinvent.basicpf.model.Moudle;
import com.thinvent.library.exception.ThinventBaseException;

import net.sf.json.JSONArray;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class MoudleHandlerImpl implements IMoudleHandler {

    @Autowired
    private IMoudleDao moudleDao;
	
	// 查找系统模块
    @Override
    public List<Moudle> findByMoudleLevelAndEnable(String moudleLevel, String userId) {
    	List<Moudle> listMoudle = getListMoudleByUserId(userId);
    	List<String> listMoudleId = new ArrayList<>();
    	Iterator<Moudle> itMoudle = listMoudle.iterator();
    	while(itMoudle.hasNext()){
    		listMoudleId.add(itMoudle.next().getMoudleId());
    	}
    	List<Moudle> list = moudleDao.findByMoudleLevelAndMoudleIdInAndEnable(moudleLevel, listMoudleId, 1);
    	Collections.sort(list);
    	return list;
    }

    // 生成模块树
	@Override
    public List<Map> findTreeByMoudleSignLike(String moudleSign, String userId) {
    	List<Moudle> listMoudle = getListMoudleByUserId(userId);
    	List<String> listMoudleId = new ArrayList<>();
    	Iterator<Moudle> itMoudle = listMoudle.iterator();
    	while(itMoudle.hasNext()){
    		listMoudleId.add(itMoudle.next().getMoudleId());
    	}
    	List<Moudle> list = moudleDao.findByMoudleSignLikeAndMoudleIdInAndEnable(moudleSign + "%", listMoudleId, 1);
    	Collections.sort(list);
        return constrTreeList(list, moudleSign);
    }

	// 根据用户获取模块
    public List<Moudle> getListMoudleByUserId(String userId) {
    	List<Moudle> listMoudle = new ArrayList<>();
    	List<Moudle> listSub = moudleDao.getMoudleByUserId(userId);
    	Iterator<Moudle> itRoleMoudle = listSub.iterator();
    	while(itRoleMoudle.hasNext()){
    		getFatherMoudle(listMoudle, itRoleMoudle.next());
    	}
    	return listMoudle;
	}

    // 根据子模块获取父模块
    public void getFatherMoudle(List<Moudle> listMoudle, Moudle moudle) {
		if(null != moudle && !listMoudle.contains(moudle)){
			listMoudle.add(moudle);
		}
		if(null != moudle && null != moudle.getMoudleParentId()){
			Moudle fatherMoudle = moudleDao.findByMoudleIdAndEnable(moudle.getMoudleParentId(), 1);
			getFatherMoudle(listMoudle, fatherMoudle);
		}
	}
	
    // 生成模块树工具
    public static List<Map> constrTreeList(List<Moudle> list, String moudleSign) {
        List<Map> mapList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (Moudle md : list) {
                Map map = new HashMap();
                if (md.getMoudleSign().equals(moudleSign)) {
                    map.put("id", md.getMoudleId() == null ? "" : md.getMoudleId());
                    map.put("name", md.getMoudleName() == null ? "" : md.getMoudleName());
                    map.put("path", md.getMoudleUrl() == null ? "" : md.getMoudleUrl());
					map.put("icon", md.getMoudleIcon() == null ? "" : md.getMoudleIcon());
                    map.put("sign", md.getMoudleSign());
                    map.put("child", constrTreeList(list, moudleSign + md.getMoudleId() + "-"));
                    mapList.add(map);
                }
            }
        }
        return mapList;
    }

	// 获取禁止按钮
	@Override
	public String getForbidList(String userId) {
    	List<String> list = new ArrayList<>();
    	List<Moudle> listMoudle = moudleDao.findByMoudleTypeAndEnable("3", 1);
    	List<Moudle> moudles = moudleDao.getAccesses(userId);
    	listMoudle.removeAll(moudles);
    	for(Moudle moudle: listMoudle) {
    		list.add(moudle.getMoudleUrl());
    	}
		return JSONArray.fromObject(list).toString();
	}

	// 查找所有模块
	@Override
    public List<Moudle> findByEnable() {
        return this.moudleDao.findByEnable(1);
    }

	@Override
	public List<Map> getMoudleTree(String moudleSign, String userId) throws ThinventBaseException {
		List<Moudle> listMoudle = getListMoudleByUserId(userId);
    	List<String> listMoudleId = new ArrayList<>();
    	Iterator<Moudle> itMoudle = listMoudle.iterator();
    	while(itMoudle.hasNext()){
    		listMoudleId.add(itMoudle.next().getMoudleId());
    	}
    	List<Moudle> list = moudleDao.findByMoudleSignLikeAndMoudleIdInAndEnable(moudleSign + "%", listMoudleId, 1);
    	Collections.sort(list);
        return getMoudleTreeList(list, moudleSign);
	}

	private List<Map> getMoudleTreeList(List<Moudle> list, String moudleSign) {
		List<Map> mapList = new ArrayList();
        if (!list.isEmpty()) {
            for (Moudle moudle : list) {
            	this.getMoudleTreeUtil(list, moudle, moudleSign, mapList);
            }
        }
        return mapList;
	}
	
	private List<Map> getMoudleTreeUtil(List<Moudle> list, Moudle moudle, String moudleSign, List<Map> mapList) {
		if (!(moudle.getMoudleSign().split("-").length == 4)) {
    		Map map = new HashMap();
    		if (moudle.getMoudleSign().equals(moudleSign)) {
    			map.put("label", moudle.getMoudleName() == null ? "" : moudle.getMoudleName());
    			map.put("children", getMoudleTreeList(list, moudleSign + moudle.getMoudleId() + "-"));
    			mapList.add(map);
    		}
		}
		return mapList;
	}
}
