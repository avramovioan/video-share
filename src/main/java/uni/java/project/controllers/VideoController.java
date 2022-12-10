package uni.java.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uni.java.project.services.UserService;
import uni.java.project.services.VideoService;

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
