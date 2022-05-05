package com.hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.hrms.business.abstracts.AdminService;
import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.core.utilities.result.SuccessDataResult;
import com.hrms.hrms.dataAccess.abstracts.AdminDao;
import com.hrms.hrms.entities.concretes.Admin;

@Service
public class AdminManager implements AdminService{

	AdminDao adminDao;
	
	@Autowired
	public AdminManager(AdminDao adminDao) {
		super();
		this.adminDao = adminDao;
	}

	@Override
	public DataResult<List<Admin>> getAll() throws Exception {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Admin>>(this.adminDao.findAll(),"Admin listelendi");
	}

	@Override
	public DataResult<Admin> getAdminByUserId(int userId) throws Exception {
		// TODO Auto-generated method stub
		Admin admin=this.adminDao.getAdminByUser_userId(userId);
		return new SuccessDataResult<Admin>(admin);
	}

}
