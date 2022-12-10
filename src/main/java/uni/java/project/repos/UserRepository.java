package uni.java.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.java.project.entities.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findByUserByEmailAndPassword(String email, String password);
}
