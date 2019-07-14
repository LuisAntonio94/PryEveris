package com.everis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.entities.StudentEntity;
import com.everis.exception.ResourceNotFoundException;
import com.everis.serviceInterfaz.IStudentService;

@RestController
@RequestMapping(path="/Student")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@PostMapping(path="/Save")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentEntity createStudent(@Valid @RequestBody StudentEntity student) {
		return studentService.saveStudent(student);
	}

	@GetMapping(path="/FindAll")
	public List<StudentEntity> getStudents() {
		return studentService.getStudents();
	}

	@PutMapping(path="/Update")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentEntity updateStudent(@RequestBody StudentEntity student) {
		return studentService.updateStudent(student);
	}

	@DeleteMapping(path="/Delete/{studentid}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStudent(@PathVariable int studentid) throws ResourceNotFoundException {
		
		StudentEntity student = studentService.findById_student(studentid);
		
		if(student != null) {
			studentService.deleteStudent(student); 
		}
		else {
			throw new ResourceNotFoundException("Student", "studentId", studentid);
		}
		
	}

}
