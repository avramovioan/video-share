package uni.java.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.java.project.entities.VideoEntity;
import uni.java.project.repos.VideoRepository;

@Service
public class VideoService{

	private VideoRepository repo;
	@Autowired
	public VideoService(VideoRepository repo) {
		this.repo = repo;
	}
	
}
