package com.greg.gmall.action;

import java.util.Map;

import org.apache.struts2.interceptor.*;
import org.springframework.transaction.annotation.Transactional;

import com.greg.gmall.model.Category;
import com.greg.gmall.service.CategoryService;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {

	private static final long serialVersionUID = -7833879077954727007L;

	private Category category;

	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String update() {
		System.out.println("----update----");
		System.out.println(categoryService);
		//categoryService.save(category);
		return "index";
	}

	public String save() {
		System.out.println("----save----");
		System.out.println(categoryService);
		//categoryService.save(category);
		return "index";
	}

	public String query() {
		// 解决方案一，采用相应的map取代原来的内置对象，这样与jsp没有依赖，但是代码量比较大
		// ActionContext.getContext().put("categoryList",
		// categoryService.query()); //放到request域中
		// ActionContext.getContext().getSession().put("categoryList",
		// categoryService.query()); //放到session域中
		// ActionContext.getContext().getApplication().put("categoryList",
		// categoryService.query()); //放到application域中

		// 解决方案二，实现相应的接口(RequestAware,SessionAware,ApplicationAware)，让相应的map注入
		request.put("categoryList", categoryService.query());
		session.put("categoryList", categoryService.query());
		application.put("categoryList", categoryService.query());
		return "index";
	}

	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

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
}
