package uni.java.project.videoshare.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
