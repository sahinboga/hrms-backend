package com.hrms.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_skills")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserSkill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_skill_id")
	private int UserSkillid;
	

	@ManyToOne()
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name="resume_id")
	private Resume resume;
	
	@ManyToOne()
	@JoinColumn(name="skill_id")
	private Skill skill;
}