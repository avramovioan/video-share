package uni.java.project.videoshare.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService{

	private VideoRepository repo;
	@Autowired
	public VideoService(VideoRepository repo) {
		this.repo = repo;
	}
	
}
