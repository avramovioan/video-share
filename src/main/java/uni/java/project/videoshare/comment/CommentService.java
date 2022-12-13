package uni.java.project.videoshare.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService{

	private CommentRepository repo;
	
	@Autowired
	public CommentService(CommentRepository repo) {
		this.repo = repo;
	}
	
	public CommentEntity saveComment(CommentEntity comment) {
		return repo.saveAndFlush(comment);
	}
	
	public CommentEntity getById(int id) {
		return repo.findById(id).get();
	}
	
	public Page<CommentEntity> getAllVideos(Pageable pagable) {
		return repo.findAll(pagable);
	}
	
	public List<CommentEntity> getAllByVideoId(int videoId, Pageable pageable){
		return repo.findByVideoId(videoId, pageable);
	}
	
	public boolean deleteComment(CommentEntity comment) {
		try{
			repo.delete(comment);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteAllByVideoId(int videoId) {
		try{
			repo.deleteByVideoId(videoId);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteAllByOwnerId(int ownerId) {
		try{
			repo.deleteByOwnerId(ownerId);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
