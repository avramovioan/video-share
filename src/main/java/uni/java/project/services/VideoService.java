package uni.java.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.java.project.entities.VideoEntity;
import uni.java.project.repos.BaseRepository;

@Service
public class VideoService {

	private BaseRepository<VideoEntity> repo;
	
	@Autowired
	public VideoService(BaseRepository<VideoEntity> repo) {
		this.repo = repo;
	}
}
