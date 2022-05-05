package com.hrms.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.hrms.entities.concretes.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer>{
	
	Admin getAdminByUser_userId(int userId);
}
