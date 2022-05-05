package com.hrms.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.hrms.business.abstracts.AdminService;
import com.hrms.hrms.business.abstracts.AuthService;
import com.hrms.hrms.business.abstracts.EmployerService;
import com.hrms.hrms.business.abstracts.JobSeekerService;
import com.hrms.hrms.business.abstracts.ResumeService;
import com.hrms.hrms.business.abstracts.UserService;
import com.hrms.hrms.core.utilities.business.BusinessRules;
import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.core.utilities.result.ErrorDataResult;
import com.hrms.hrms.core.utilities.result.Result;
import com.hrms.hrms.core.utilities.result.SuccessDataResult;
import com.hrms.hrms.core.utilities.result.SuccessResult;
import com.hrms.hrms.core.utilities.security.HashingHelper;
import com.hrms.hrms.entities.concretes.Admin;
import com.hrms.hrms.entities.concretes.Employer;
import com.hrms.hrms.entities.concretes.JobSeeker;
import com.hrms.hrms.entities.concretes.Resume;
import com.hrms.hrms.entities.concretes.Role;
import com.hrms.hrms.entities.concretes.User;
import com.hrms.hrms.entities.dtos.AuthDto;
import com.hrms.hrms.entities.dtos.UserForLoginDto;
@Service
public class AuthManager implements AuthService {
	
	

	private JobSeekerService jobSeekerService;
	private EmployerService employerService;
	private AdminService adminService;
	private UserService userService;
	private ResumeService resumeService;
	
	@Autowired
	public AuthManager(JobSeekerService jobSeekerService,EmployerService employerService,UserService userService, ResumeService resumeService,
			AdminService adminService) {
		super();
		this.jobSeekerService = jobSeekerService;
		this.employerService=employerService;
		this.userService=userService;
		this.resumeService = resumeService;
		this.adminService=adminService;
	}
	@Override
	public DataResult<AuthDto> login(UserForLoginDto loginDto) throws Exception {
		AuthDto authDto = new AuthDto();
		DataResult<User> UserToCheck= this.userService.getByEmail(loginDto.getEmail());
		if(UserToCheck.getData()==null) {
			return new ErrorDataResult<AuthDto>("E-posta hatalı!");
		}
		if(!HashingHelper.VerifyPasswordHash(loginDto.getPassword(), UserToCheck.getData().getPassword())) {
			return new ErrorDataResult<AuthDto>("Şifre hatalı!");
		}
		UserToCheck.getData().setPassword(null);
		
		Role role = UserToCheck.getData().getRole();
		if(role.getId() == Role.JOBSEEKER().getId()) {
			DataResult<JobSeeker> jobSeekerResult = jobSeekerService.getUserById(UserToCheck.getData().getUserId());
			if(!jobSeekerResult.isSuccess()) {
				return new ErrorDataResult<AuthDto>(jobSeekerResult.getMessage());
			}
			authDto.setUserData(jobSeekerResult.getData());
		}
		else if(role.getId() == Role.EMPLOYER().getId()) {
			DataResult<Employer> employerResult = employerService.getEmployerByUserId(UserToCheck.getData().getUserId());
			if(!employerResult.isSuccess()) {
				return new ErrorDataResult<AuthDto>(employerResult.getMessage());
			}
			authDto.setUserData(employerResult.getData());
		}
		else if(role.getId() == Role.ADMIN().getId()) {
			DataResult<Admin> adminResult = adminService.getAdminByUserId(UserToCheck.getData().getUserId());
			if(!adminResult.isSuccess()) {
				return new ErrorDataResult<AuthDto>(adminResult.getMessage());
			}
			authDto.setUserData(adminResult.getData());
		}
		return new SuccessDataResult<AuthDto>(authDto,"Giriş başarılı");
	}

	@Override
	public Result registerForJobSeeker(JobSeeker jobSeeker) throws Exception {
		
		// iş kuralları
		Result result=BusinessRules.Run(this.userService.validate(jobSeeker.getUser()),this.jobSeekerService.validate(jobSeeker));
		if(result!=null) {
			return result;
		}
		
		jobSeeker.getUser().setRole(Role.JOBSEEKER());
		
		DataResult<User> userAddResult= this.userService.add(jobSeeker.getUser());
		if(!userAddResult.isSuccess()) {
			return userAddResult;
		}
		
		jobSeeker.getUser().setUserId(userAddResult.getData().getUserId());
		
		DataResult<JobSeeker> jobSeekerAddResult=this.jobSeekerService.add(jobSeeker);
		if(!jobSeekerAddResult.isSuccess()) {
			this.userService.delete(userAddResult.getData());
			return jobSeekerAddResult;
		}
		
		Resume resume = new Resume();
		resume.setJobSeeker(jobSeekerAddResult.getData());
		resumeService.add(resume);
		
		return new SuccessResult("Kayıt başarılı.");
	}

	@Override
	public Result registerForEmployer(Employer employer) throws Exception {
		// iş Kuralları
		Result result=BusinessRules.Run(this.userService.validate(employer.getUser()),this.employerService.validate(employer));
		if(result!=null) {
			return result;
		}
		
		employer.getUser().setRole(Role.EMPLOYER());
		
		DataResult<User> userAddResult= this.userService.add(employer.getUser());
		if(!userAddResult.isSuccess()) {
			return userAddResult;
		}
		
		employer.getUser().setUserId(userAddResult.getData().getUserId());
		DataResult<Employer> employerAddResult=this.employerService.add(employer);
		if(!employerAddResult.isSuccess()) {
			this.userService.delete(userAddResult.getData());
			return employerAddResult;
		}
		return new SuccessResult("Kayıt başarılı.");
	}
	@Override
	public Result registerForAdmin(Admin admin) throws Exception {
		Result result=BusinessRules.Run(this.userService.validate(admin.getUser()),this.employerService.validate(admin));
		if(result!=null) {
			return result;
		}
		
		admin.getUser().setRole(Role.ADMIN());
		
		DataResult<User> userAddResult= this.userService.add(admin.getUser());
		if(!userAddResult.isSuccess()) {
			return userAddResult;
		}
		
		admin.getUser().setUserId(userAddResult.getData().getUserId());
		DataResult<Admin> adminAddResult=this.adminService.add(admin);
		if(!adminAddResult.isSuccess()) {
			this.userService.delete(userAddResult.getData());
			return adminAddResult;
		}
		return new SuccessResult("Kayıt başarılı.");
	}

}
