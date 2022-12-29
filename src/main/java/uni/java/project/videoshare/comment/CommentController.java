package uni.java.project.videoshare.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.java.project.videoshare.user.UserEntity;
import uni.java.project.videoshare.user.UserService;
import uni.java.project.videoshare.video.VideoEntity;
import uni.java.project.videoshare.video.VideoService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	private UserService userService;
	private VideoService videoService;
	private CommentService commentService;
	
	@Autowired
	public CommentController(UserService userService,
						   VideoService videoService,
						   CommentService commentService) {
		this.userService = userService;
		this.videoService = videoService;
		this.commentService = commentService;
	}
	
	@GetMapping("{videoId}")
	public ResponseEntity<List<CommentBean>> getAllVideoComments(@PathVariable int videoId,
																 @RequestParam(value = "page") int page,
																 @RequestParam(value = "itemCount") int itemCount){
		VideoEntity video = videoService.getById(videoId);
		if(video == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<CommentEntity> comments = commentService.getAllByVideoId(videoId, PageRequest.of(page, itemCount));
		
		List<CommentBean> commentBeans = new ArrayList<>();
		for(CommentEntity comment : comments) {
			commentBeans.add(new CommentBean(comment));
		}
		return new ResponseEntity<List<CommentBean>>(commentBeans, HttpStatus.OK);
	}
	
	@PostMapping("{videoId}")
	public ResponseEntity<CommentBean> createComment(@PathVariable int videoId,
												   @RequestBody(required = true) CommentCreateBean commentCreate,
												   @RequestHeader(value="Authorization") String authToken){
		
		UserEntity loggedUser = userService.getUserByToken(authToken);
		VideoEntity video = videoService.getById(videoId);
		if(video == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		CommentEntity commentToCreate = new CommentEntity(commentCreate.getContent(), video, loggedUser);
		
		commentToCreate = commentService.saveComment(commentToCreate);
		return new ResponseEntity<CommentBean>(new CommentBean(commentToCreate), HttpStatus.OK);
	}
	
	@PutMapping("{commentId}")
	public ResponseEntity<CommentBean> updateComment(@PathVariable int commentId,
												 @RequestBody(required = true) CommentCreateBean commentUpdate,
												 @RequestHeader(value="Authorization") String authToken){
		
		UserEntity loggedUser = userService.getUserByToken(authToken);
		CommentEntity commentToUpdate = commentService.getById(commentId);
		if(commentToUpdate == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if(commentToUpdate.getOwner().getId() != loggedUser.getId()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		if(commentUpdate != null && commentUpdate.getContent().isBlank()) 
			return new ResponseEntity<>(new CommentBean(commentToUpdate), HttpStatus.OK);
		
		commentToUpdate.setContent(commentUpdate.getContent());
		commentToUpdate = commentService.saveComment(commentToUpdate);
		return new ResponseEntity<CommentBean>(new CommentBean(commentToUpdate), HttpStatus.OK);
	}
	
	@DeleteMapping("{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable int commentId,
											@RequestHeader(value="Authorization") String authToken){
		
		UserEntity loggedUser = userService.getUserByToken(authToken);
		CommentEntity comment = commentService.getById(commentId);
		if(comment.getOwner().getId() != loggedUser.getId() && 
		   comment.getVideo().getOwner().getId() != loggedUser.getId()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		if(!commentService.deleteComment(comment)) return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
