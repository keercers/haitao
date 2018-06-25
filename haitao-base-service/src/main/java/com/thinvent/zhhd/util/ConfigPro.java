package com.thinvent.zhhd.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "zhhd-config")
public class ConfigPro {

	private String scripts;
	private String styles;
	private Map<String, String> user = new HashMap();
	private Map<String, String> web = new HashMap();
	private Map<String, String> session = new HashMap();
	private Map<String, String> MQ = new HashMap();
	private Map<String, String> TCP = new HashMap();
	private Map<String, String> IDmapGUID = new HashMap();

	public String getScripts() {
		return scripts;
	}

	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public Map<String, String> getUser() {
		return user;
	}

	public void setUser(Map<String, String> user) {
		this.user = user;
	}

	public Map<String, String> getWeb() {
		return web;
	}

	public void setWeb(Map<String, String> web) {
		this.web = web;
	}

	public Map<String, String> getSession() {
		return session;
	}

	public void setSession(Map<String, String> session) {
		this.session = session;
	}

	public Map<String, String> getMQ() {
		return MQ;
	}

	public void setMQ(Map<String, String> mQ) {
		MQ = mQ;
	}

	public Map<String, String> getTCP() {
		return TCP;
	}

	public void setTCP(Map<String, String> tCP) {
		TCP = tCP;
	}

	public Map<String, String> getIDmapGUID() {
		return IDmapGUID;
	}

	public void setIDmapGUID(Map<String, String> iDmapGUID) {
		IDmapGUID = iDmapGUID;
	}

}
