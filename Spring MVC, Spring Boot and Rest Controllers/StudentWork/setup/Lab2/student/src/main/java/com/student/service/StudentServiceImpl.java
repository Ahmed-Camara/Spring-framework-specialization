package com.student.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import com.student.core.Student;
import com.student.dao.StudentDao;

@Named
public class StudentServiceImpl implements StudentService {
	
	@Inject
	private StudentDao studentDao;
	
	@Override
	public Student get(long id) {
		return studentDao.getOne(id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentDao.getAll();
	}

	@Override
	public Collection<Student> getAllStudentsInDepartement(String departement, String lastNameLike) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Student student) {
		// TODO Auto-generated method stub

	}

}
