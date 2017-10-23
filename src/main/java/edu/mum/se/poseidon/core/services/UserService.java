package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.dto.UserDto;
import edu.mum.se.poseidon.core.repositories.AdminRepository;
import edu.mum.se.poseidon.core.repositories.EntryRepository;
import edu.mum.se.poseidon.core.repositories.FacultyRepository;
import edu.mum.se.poseidon.core.repositories.StudentRepository;
import edu.mum.se.poseidon.core.repositories.UserRepository;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import edu.mum.se.poseidon.core.repositories.models.users.Admin;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import edu.mum.se.poseidon.core.repositories.models.users.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Munkhtsot Tsogbadrakh on 10/11/2017.
 *
 * @author Munkhtsot Tsogbadrakh
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private FacultyRepository facultyRepository;
    private EntryRepository entryRepository;
    private AdminRepository adminRepository;

    @Autowired
    public UserService(UserRepository userRepository, StudentRepository studentRepository,
    		FacultyRepository facultyRepository, AdminRepository adminRepository,
    		EntryRepository entryRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.adminRepository = adminRepository;
        this.entryRepository = entryRepository;
    }

    public User getUserByUsername(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            return null;
        }
        String pass = user.getPassword();

        if(!pass.equals(password)) {
            return null;
        }

        return user;
    }
    
    public User createUser(UserDto userDto) {
    	
    	User user = userRepository.findUserByUsername(userDto.getUsername());
    	
    	if(user != null) {
    		throw new RuntimeException("The user exists!");
    	}
    	
    	if(userDto.getType().equals("student")) {
    		Student student = new Student();
    		student.setFirstName(userDto.getFirstName());
    		student.setLastName(userDto.getLastName());
    		student.setUsername(userDto.getUsername());
    		student.setPassword(userDto.getPassword());
    		student.setType(userDto.getType());
    		Entry entry = entryRepository.findEntriesByDeleted(false).get(0);
    		student.setEntry(entry);
    		student = studentRepository.save(student);
    		return student;
    	}
    	else if(userDto.getType().equals("faculty")) {
    		Faculty faculty = new Faculty();
    		faculty.setFirstName(userDto.getFirstName());
    		faculty.setLastName(userDto.getLastName());
    		faculty.setUsername(userDto.getUsername());
    		faculty.setPassword(userDto.getPassword());
    		faculty.setType(userDto.getType());
    		faculty = facultyRepository.save(faculty);
    		return faculty;
    	}
    	else {
    		Admin admin = new Admin();
    		admin.setFirstName(userDto.getFirstName());
    		admin.setLastName(userDto.getLastName());
    		admin.setUsername(userDto.getUsername());
    		admin.setPassword(userDto.getPassword());
    		admin.setType(userDto.getType());
    		admin = adminRepository.save(admin);
    		return admin;
    	}
	}
    
    public User editUser(UserDto userDto) {
    	User user = userRepository.findOne(userDto.getId());
    	User u = userRepository.findUserByUsername(userDto.getUsername());
    	if(u != null && u.getId() != user.getId()) {
    		throw new RuntimeException("The username conflicts with other user's username!");
    	}
    	
    	user.setFirstName(userDto.getFirstName());
    	user.setLastName(userDto.getLastName());
    	user.setUsername(userDto.getUsername());
    	user.setPassword(userDto.getPassword());
    	// user.setType(userDto.getType());
    	user = userRepository.save(user);
    	return user;
    }
    
    public List<User> getUserList(){
    	return userRepository.findAllByDeleted(false);
    }
    
    public User getUser(long userId) {
    	return userRepository.findOne(userId);
    }
    
    public void deleteUser(long userId) {
    	User user = userRepository.findOne(userId);
    	user.setDeleted(true);
    	userRepository.save(user);
    }
}
