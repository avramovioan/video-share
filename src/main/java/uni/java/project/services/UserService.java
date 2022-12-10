package uni.java.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.java.project.entities.UserEntity;
import uni.java.project.repos.BaseRepository;

@Service
public class UserService {
	
	private BaseRepository<UserEntity> repo;
	
	@Autowired
	public UserService(BaseRepository<UserEntity> repo){
		this.repo = repo;
	}
}
