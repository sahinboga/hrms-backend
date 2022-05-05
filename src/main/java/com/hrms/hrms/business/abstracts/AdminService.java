package com.hrms.hrms.business.abstracts;

import java.util.List;

import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.entities.concretes.Admin;

public interface AdminService {
	DataResult<List<Admin>>  getAll() throws Exception;
	DataResult<Admin> getAdminByUserId(int userId) throws Exception;
}
