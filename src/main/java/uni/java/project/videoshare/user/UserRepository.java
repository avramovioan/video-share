package uni.java.project.videoshare.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findUserByEmailAndPassword(String email, String password);
	public UserEntity findUserByUsername(String username);
	public UserEntity findUserByEmail(String email);
}
