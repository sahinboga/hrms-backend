package com.hrms.hrms.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.hrms.business.abstracts.DepartmentService;
import com.hrms.hrms.entities.concretes.Department;

@RequestMapping("/api/departments")
@RestController
public class DepartmentController extends BaseController{
	
	private DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(){
		
		return Ok(()->this.departmentService.getAll());
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Department dep){
		return Ok(()->this.departmentService.add(dep));
	}
}
