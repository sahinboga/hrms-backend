package com.hrms.hrms.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.hrms.business.abstracts.AdminService;

@RestController
@RequestMapping("api/admin")
public class AdminController extends BaseController{
	
	AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@GetMapping("/getall")
	 public ResponseEntity<?> getAll(){
		
		return Ok(()->this.adminService.getAll());
	}
	
	@GetMapping("/getadminbyuserid")
	public ResponseEntity<?> getEmployerByUserId(int userId){
		
		return Ok(()->this.adminService.getAdminByUserId(userId));
	}
}
