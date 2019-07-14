package com.everis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.entities.StudentEntity;
import com.everis.repository.StudentRepository;
import com.everis.serviceInterfaz.IStudentService;

@Service
public class StudentServiceImp implements IStudentService{

	@Autowired
	private StudentRepository studentRepository;
	

	@Override
	public StudentEntity saveStudent(StudentEntity student) {

		return studentRepository.save(student);
	}
	
	
	@Override
	public List<StudentEntity> getStudents() {

		return studentRepository.findAll();
	}
	
	
	@Override
	public StudentEntity updateStudent(StudentEntity student) {
		
		/* Lambda
		 studentRepository.findById(student.getStudent_id()).ifPresent((x) -> {
			studentRepository.save(student);
		});
		*/
		
		StudentEntity student_updated = null;
		
		if(studentRepository.existsById(student.getStudent_id())) {
			student_updated = studentRepository.save(student);
		}
		
		return student_updated;
	}
	

	@Override
	public void deleteStudent(StudentEntity student) {

		studentRepository.delete(student);;
	}
	
	
	@Override
	public StudentEntity findById_student(int studentid) {
		
		return studentRepository.findById(studentid).get();
	}

}
