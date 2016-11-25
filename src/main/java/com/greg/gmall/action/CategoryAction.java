package com.greg.gmall.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.greg.gmall.model.Category;
import com.greg.gmall.service.CategoryService;

@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction<Category> {

	private static final long serialVersionUID = -7833879077954727007L;

	private Category category;

	@Resource
	private CategoryService categoryService;


	public String update() {
		System.out.println("----update----");
		System.out.println(categoryService);
		System.out.println(category);
		//categoryService.save(category);
		return "index";
	}

	public String save() {
		System.out.println("----save----");
		System.out.println(categoryService);
		categoryService.save(category);
		return "index";
	}

	public String query() {
		request.put("categoryList", categoryService.query());
		session.put("categoryList", categoryService.query());
		application.put("categoryList", categoryService.query());
		return "index";
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
