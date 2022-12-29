package uni.java.project.videoshare.video;

import java.io.Serializable;

import uni.java.project.videoshare.user.UserBean;

public class VideoFullBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String url;
	private String description;
	private UserBean owner;
	
	public VideoFullBean() {}
	
	public VideoFullBean(VideoEntity video) {
		this.id = video.getId();
		this.title = video.getTitle();
		this.url = video.getUrl();
		this.description = video.getDescription();
		this.owner = new UserBean(video.getOwner());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserBean getOwner() {
		return owner;
	}

	public void setOwner(UserBean owner) {
		this.owner = owner;
	}

	

}
