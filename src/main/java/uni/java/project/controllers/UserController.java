package uni.java.project.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import uni.java.project.beans.UserBean;
import uni.java.project.entities.UserEntity;
import uni.java.project.services.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserBean>> getAllUsers(@RequestParam(value ="page") int page,
														@RequestParam(value ="itemCount") int itemCount){
		List<UserEntity> users = userService.getAll(PageRequest.of(page, itemCount)).toList();
		List<UserBean> userBeans = new ArrayList<>();
		for(UserEntity user : users) {
			userBeans.add(new UserBean(user));
		}
		return new ResponseEntity<List<UserBean>>(userBeans, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserBean> register(@RequestParam(value = "email") String email, 
											@RequestParam(value = "username") String username, 
											@RequestParam(value = "password") String password){
		
		if(email.isBlank() || username.isBlank() || password.isBlank())return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		UserEntity userToCreate = new UserEntity(username, password, email);
		userToCreate = userService.saveUser(userToCreate);
		
		return new ResponseEntity<UserBean>(new UserBean(userToCreate),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam(value = "email") String email,
										  @RequestParam(value = "password") String password){
		
		if(email.isBlank() || password.isBlank()) return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
		
		UserEntity loggedUser = userService.getByEmailAndPassword(email, password);
		
		if(loggedUser == null) return new ResponseEntity<Unauthorized>(HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<UserBean>(new UserBean(loggedUser), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestParam(value = "email") String email,
									  @RequestParam(value = "username") String username,
									  @RequestParam(value = "password") String password,
									  @RequestHeader(value = "Authorizaion") String authToken ){
	
		
		UserEntity loggedUser = userService.getUserByToken(authToken);
		if(loggedUser == null) return new ResponseEntity<Unauthorized>(HttpStatus.UNAUTHORIZED);
		
		if(!email.isBlank()) loggedUser.setEmail(email);
		if(!username.isBlank()) loggedUser.setEmail(username);
		if(!password.isBlank()) loggedUser.setEmail(password);
		
		loggedUser = userService.saveUser(loggedUser);
		
		return new ResponseEntity<UserBean>(new UserBean(loggedUser), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestHeader(value = "Authorization") String authToken){
		UserEntity loggedUser = userService.getUserByToken(authToken);
		if(loggedUser == null) return new ResponseEntity<Unauthorized>(HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	
}
