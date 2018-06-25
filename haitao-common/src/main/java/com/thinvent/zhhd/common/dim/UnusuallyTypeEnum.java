package com.thinvent.zhhd.common.dim;

public enum UnusuallyTypeEnum {
	
	FEIFATK("非法停靠", 1), ZGONGTUWT("中途违停", 2), GUIJIYC("轨迹异常", 3),  AISXHDS("AIS信号丢失", 4); 
//	CHUANBJKCS("船舶监控超时", 5);
	
	private String name;
	private int index;
	private UnusuallyTypeEnum(String name, int index){
		this.name = name;
		this.index = index;
	}
	
	//获取对应变量
	public static String getName(int index){
		String name = "未定义";
		for(UnusuallyTypeEnum exe: UnusuallyTypeEnum.values()){
			if(exe.getIndex() == index)
				name = exe.getName();
		}
		return name;
	}
	
	//获取对应序列
	public static int getIndex(String name){
		int index = -1;
		for(UnusuallyTypeEnum exe: UnusuallyTypeEnum.values()){
			if(exe.getName().equals(name))
				index = exe.getIndex();
		}
		return index;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

}