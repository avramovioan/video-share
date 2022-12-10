package uni.java.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.java.project.entities.VideoEntity;


@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {
	
}
