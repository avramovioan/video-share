package uni.java.project.videoshare.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideoService{

	private VideoRepository repo;
	
	@Autowired
	public VideoService(VideoRepository repo) {
		this.repo = repo;
	}
	
	public VideoEntity saveVideo(VideoEntity video) {
		return repo.saveAndFlush(video);
	}
	
	public VideoEntity getById(int id) {
		return repo.findById(id).get();
	}
	
	public Page<VideoEntity> getAllVideos(Pageable pagable) {
		return repo.findAll(pagable);
	}
	
	public List<VideoEntity> getAllByUserId(int userId){
		return repo.findByUserId(userId);
	}
	
	public boolean deleteVideo(VideoEntity video) {
		try {
			repo.delete(video);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteAllByOwnerId(int ownerId) {
		try {
			repo.deleteByOwnerId(ownerId);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
