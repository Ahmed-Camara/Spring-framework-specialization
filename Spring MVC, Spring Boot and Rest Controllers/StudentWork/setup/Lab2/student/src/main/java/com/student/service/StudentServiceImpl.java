package com.student.service;

import java.util.Collection;
import java.util.stream.Collectors;

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
	public Collection<Student> getAllStudentsInDepartement(String departement, String lastNameLike){
		
		return studentDao.getAll()
				.stream()
				.filter(p -> p.getDept().equals(departement))
				.filter(p -> p.getSurname().contains(lastNameLike))
				.collect(Collectors.toList());
	}

	@Override
	public void add(Student student) {
		
		if(student.getFirstName() != null && student.getSurname() != null && student.getDept() != null)
			System.out.println("Valid");
			studentDao.add(student);

	}

}
