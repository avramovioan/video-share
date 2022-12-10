package uni.java.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.java.project.entities.CommentEntity;
import uni.java.project.repos.BaseRepository;

@Service
public class CommentService {

	private BaseRepository<CommentEntity> repo;
	
	@Autowired
	public CommentService(BaseRepository<CommentEntity> repo) {
		this.repo = repo;
	}
}
