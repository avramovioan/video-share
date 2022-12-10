package uni.java.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.java.project.entities.CommentEntity;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
}
