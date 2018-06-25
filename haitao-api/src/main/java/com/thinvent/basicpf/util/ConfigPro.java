package com.thinvent.basicpf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "admin-api")
public class ConfigPro {
	
	private String basicServiceUrl;
	private String zhhdBasicServiceUrl;	
	private Map<String, String> proMap = new HashMap();
	private List<String> proList = new ArrayList();
	private List<Map<String, String>> proListMap = new ArrayList();
	
	public String getBasicServiceUrl() {
		return basicServiceUrl;
	}
	public void setBasicServiceUrl(String basicServiceUrl) {
		this.basicServiceUrl = basicServiceUrl;
	}
	public String getZhhdBasicServiceUrl() {
		return zhhdBasicServiceUrl;
	}
	public void setZhhdBasicServiceUrl(String zhhdBasicServiceUrl) {
		this.zhhdBasicServiceUrl = zhhdBasicServiceUrl;
	}	
	public Map<String, String> getProMap() {
		return proMap;
	}
	public void setProMap(Map<String, String> proMap) {
		this.proMap = proMap;
	}
	public List<String> getProList() {
		return proList;
	}
	public void setProList(List<String> proList) {
		this.proList = proList;
	}
	public List<Map<String, String>> getProListMap() {
		return proListMap;
	}
	public void setProListMap(List<Map<String, String>> proListMap) {
		this.proListMap = proListMap;
	}

}
