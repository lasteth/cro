package com.cro.action;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware {
	private static final long serialVersionUID = -1170067112876395839L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	
	
	protected Map session = ActionContext.getContext().getSession();

	public String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> arg0) {
	}

}
