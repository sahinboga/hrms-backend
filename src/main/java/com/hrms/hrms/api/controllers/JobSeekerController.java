package com.hrms.hrms.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.hrms.business.abstracts.JobSeekerService;


@RequestMapping("/api/jobseekers")
@RestController
public class JobSeekerController extends BaseController{
	
	private JobSeekerService jobSeekerService;
	
	@Autowired
	public JobSeekerController(JobSeekerService jobSeekerService) {
		super();
		this.jobSeekerService = jobSeekerService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(){
		 return Ok(()->this.jobSeekerService.getAll());
		 
	}
	
	@GetMapping("/getjobseeker")
	public ResponseEntity<?> getJobSeeker(int jsId){
		 return Ok(()->this.jobSeekerService.getJobSeekerById(jsId));
		 
	}
	
	@GetMapping("/getjobseekerbyuserid")
	public ResponseEntity<?> getJobSeekerByUserId(int userId){
		 return Ok(()->this.jobSeekerService.getUserById(userId));
		 
	}
	
	
}
