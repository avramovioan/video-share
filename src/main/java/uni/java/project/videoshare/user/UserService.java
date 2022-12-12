package uni.java.project.videoshare.user;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService{

	private UserRepository repo;
	
	@Autowired
	public UserService(UserRepository repo) {
		this.repo = repo;
	}	
	
	public UserEntity saveUser(UserEntity user) {
		return repo.saveAndFlush(user);
	}
	
	public UserEntity getById(int id) {
		return repo.findById(id).get();
	}
	
	public UserEntity getByEmailAndPassword(String email, String password) {
		return repo.findUserByEmailAndPassword(email, password);
	}
	
	public Page<UserEntity> getAll(Pageable pagable){
		return repo.findAll(pagable);
	}
	
	public UserEntity getUserByToken(String authorization) {
		if (authorization == null || !authorization.toLowerCase().startsWith("basic")) return null;
		
	    // Authorization: Basic base64credentials
	    String base64Credentials = authorization.substring("Basic".length()).trim();
	    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
	    String credentials = new String(credDecoded);
	    // credentials = email:password
	    String[] values = credentials.split(":", 2);
		String email = values[0];
		String password = values[1];
		return getByEmailAndPassword(email, password);
	}
}
