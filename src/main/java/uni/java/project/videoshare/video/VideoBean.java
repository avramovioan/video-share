package uni.java.project.videoshare.video;

import java.io.Serializable;

public class VideoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String url;
	private String description;
	private int ownerId;
	
	public VideoBean() {}
	
	public VideoBean(VideoEntity video) {
		this.id = video.getId();
		this.title = video.getTitle();
		this.url = video.getUrl();
		this.description = video.getDescription();
		this.ownerId = video.getOwner().getId();
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

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	

}
