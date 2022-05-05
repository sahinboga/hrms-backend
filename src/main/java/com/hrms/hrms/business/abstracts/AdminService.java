package com.hrms.hrms.business.abstracts;

import java.util.List;

import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.core.utilities.result.Result;
import com.hrms.hrms.entities.concretes.Admin;
import com.hrms.hrms.entities.concretes.Employer;

public interface AdminService {
	DataResult<List<Admin>>  getAll() throws Exception;
	DataResult<Admin> add(Admin entity) throws Exception;
	Result isNull(Admin admin) throws Exception;
	Result validate(Admin admin) throws Exception;
	DataResult<Admin> getAdminByUserId(int userId) throws Exception;
}
