package com.hrms.hrms.business.abstracts;

import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.core.utilities.result.Result;
import com.hrms.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService extends CrudService<JobSeeker> {
	
	Result isNull(JobSeeker jobSeeker) throws Exception;
	Result validate(JobSeeker jobSeeker) throws Exception;
	DataResult<JobSeeker> getJobSeekerById(int jsId) throws Exception;
	DataResult<JobSeeker> getUserById(int userId) throws Exception;
}
