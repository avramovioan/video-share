package uni.java.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.java.project.repos.CommentRepository;

@Service
public class CommentService{

	private CommentRepository repo;
	
	@Autowired
	public CommentService(CommentRepository repo) {
		this.repo = repo;
	}

}
