package uni.java.project.videoshare.video;

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

import uni.java.project.videoshare.comment.CommentService;
import uni.java.project.videoshare.user.UserEntity;
import uni.java.project.videoshare.user.UserService;

@RestController
@RequestMapping("/api/video")
public class VideoController {
	
	private UserService userService;
	private VideoService videoService;
	private CommentService commentService;
	
	@Autowired
	public VideoController(UserService userService,
						   VideoService videoService,
						   CommentService commentService) {
		this.userService = userService;
		this.videoService = videoService;
		this.commentService = commentService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<VideoBean>> getAllVideos(@RequestParam(value = "page") int page,
														@RequestParam(value = "itemCount") int itemCount){
		
		List<VideoEntity> videos = videoService.getAllVideos(PageRequest.of(page, itemCount)).toList();
		List<VideoBean> videoBeans = new ArrayList<>();
		for(VideoEntity video : videos) {
			videoBeans.add(new VideoBean(video));
		}
		return new ResponseEntity<List<VideoBean>>(videoBeans, HttpStatus.OK);
	}
	
	@GetMapping("{videoId}")
	public ResponseEntity<VideoBean> getVideoById(@PathVariable(value="videoId") int videoId){
		
		VideoEntity video = videoService.getById(videoId);
		if(video == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<VideoBean>(new VideoBean(video), HttpStatus.OK);		
	}
	
	@GetMapping("/myVideos")
	public ResponseEntity<List<VideoBean>> getAllUserVideos(@RequestHeader(name = "Authorization") String authToken){
		
		UserEntity loggedUser = userService.getUserByToken(authToken);
		if(loggedUser == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		List<VideoEntity> videos = videoService.getAllByUserId(loggedUser.getId());
		List<VideoBean> videoBeans = new ArrayList<>();
		for(VideoEntity video : videos) {
			videoBeans.add(new VideoBean(video));
		}
		return new ResponseEntity<List<VideoBean>>(videoBeans, HttpStatus.OK);
	}
	
	@PostMapping()	
	public ResponseEntity<VideoBean> createVideo(@RequestBody(required = true) VideoCreateBean videoCreate,
												 @RequestHeader(value="Authorization") String authToken){
		
		UserEntity loggedUser = userService.getUserByToken(authToken);
		if(loggedUser == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		VideoEntity videoToCreate = new VideoEntity(videoCreate.getTitle(),
													videoCreate.getUrl(), 
													videoCreate.getDescription(),
													loggedUser);
		
		videoToCreate = videoService.saveVideo(videoToCreate);
		return new ResponseEntity<VideoBean>(new VideoBean(videoToCreate), HttpStatus.OK);
	}
	
	@PutMapping("{videoId}")
	public ResponseEntity<VideoBean> updateVideo(@PathVariable int videoId,
												 @RequestBody(required = true) VideoCreateBean videoUpdate,
												 @RequestHeader(value="Authorization") String authToken){
		
		UserEntity loggedUser = userService.getUserByToken(authToken);
		VideoEntity videoToUpdate = videoService.getById(videoId);
		if(videoToUpdate == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if(videoToUpdate.getOwner().getId() != loggedUser.getId()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		if(!videoUpdate.getTitle().isBlank()) videoToUpdate.setTitle(videoUpdate.getTitle());
		if(!videoUpdate.getUrl().isBlank()) videoToUpdate.setUrl(videoUpdate.getUrl());
		if(!videoUpdate.getDescription().isBlank()) videoToUpdate.setDescription(videoUpdate.getDescription());
		
		videoService.saveVideo(videoToUpdate);
		
		return new ResponseEntity<VideoBean>(new VideoBean(videoToUpdate), HttpStatus.OK);
	}
	
	@DeleteMapping("{videoId}")
	public ResponseEntity<?> deleteVideo(@PathVariable int videoId,
										@RequestHeader(value="Authorization") String authToken){
		
		UserEntity loggedUser = userService.getUserByToken(authToken);
		VideoEntity video = videoService.getById(videoId);
		if(video == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if(video.getOwner().getId() != loggedUser.getId())return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		if(!commentService.deleteAllByVideoId(videoId)) 
			return new ResponseEntity<>("Something went wrong with comment deletions", HttpStatus.BAD_REQUEST);
		
		if(!videoService.deleteVideo(video))
			return new ResponseEntity<>("Something went wrong with comment deletions", HttpStatus.BAD_REQUEST);
			
		return new ResponseEntity<>(HttpStatus.OK);
				
	}
	
}
