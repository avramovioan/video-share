package uni.java.project.videoshare.comment;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	public List<CommentEntity> findByVideoId(int videoId, Pageable pageable);
	public long deleteByVideoId(int videoId);
	public long deleteByOwnerId(int ownerId);
}
