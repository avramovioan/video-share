package uni.java.project.videoshare.video;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.java.project.videoshare.user.UserEntity;
import uni.java.project.videoshare.user.UserService;

@RestController
@RequestMapping("/api/video")
public class VideoController {
	
	private UserService userService;
	private VideoService videoService;
	
	@Autowired
	public VideoController(UserService userService,
						   VideoService videoService) {
		this.userService = userService;
		this.videoService = videoService;
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
	
	@PostMapping()
	public ResponseEntity<VideoBean> createVideo(@RequestBody(required = true) VideoCreateBean videoCreate,
												 @RequestHeader(value="Authorization") String authToken){
		
		UserEntity owner = userService.getUserByToken(authToken);
		VideoEntity videoToCreate = new VideoEntity(videoCreate.getTitle(),
													videoCreate.getUrl(), 
													videoCreate.getDescription(),
													owner);
		
		videoToCreate = videoService.saveVideo(videoToCreate);
		return new ResponseEntity<VideoBean>(new VideoBean(videoToCreate), HttpStatus.OK);
	}
	
//	@GetMapping("/all")
//	public ResponseEntity<List<VideoBean>> getAllVideos(@RequestParam(value = "page") int page,
//														@RequestParam(value = "itemCount") int itemCount){
//		
//		List<VideoEntity> videos = videoService.getAllVideos(PageRequest.of(page, itemCount)).toList();
//		List<VideoBean> videoBeans = new ArrayList<>();
//		for(VideoEntity video : videos) {
//			videoBeans.add(new VideoBean(video));
//		}
//		return new ResponseEntity<List<VideoBean>>(videoBeans, HttpStatus.OK);
//	}
//	
//	@GetMapping("/all")
//	public ResponseEntity<List<VideoBean>> getAllVideos(@RequestParam(value = "page") int page,
//														@RequestParam(value = "itemCount") int itemCount){
//		
//		List<VideoEntity> videos = videoService.getAllVideos(PageRequest.of(page, itemCount)).toList();
//		List<VideoBean> videoBeans = new ArrayList<>();
//		for(VideoEntity video : videos) {
//			videoBeans.add(new VideoBean(video));
//		}
//		return new ResponseEntity<List<VideoBean>>(videoBeans, HttpStatus.OK);
//	}
	
}
