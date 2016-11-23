package com.greg.gmall.action;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport
		implements RequestAware, SessionAware, ApplicationAware, ModelDriven<T> {

	private static final long serialVersionUID = 4065549026599172654L;

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	protected T model;

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;

	}

	
	@Override
	public T getModel() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> persistenClass = (Class<T>) type.getActualTypeArguments()[0];
		try {
			model = (T) persistenClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return model;
	}

}
