package uni.java.project.videoshare.video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {
	
	public List<VideoEntity> findByOwnerId(int ownerId);
	public List<VideoEntity> findByOwnerUsernameContainingAndTitleContaining(String username, String title);
	public long deleteByOwnerId(int ownerId);
}
