package uni.java.project.videoshare.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService{

	private CommentRepository repo;
	
	@Autowired
	public CommentService(CommentRepository repo) {
		this.repo = repo;
	}

}
