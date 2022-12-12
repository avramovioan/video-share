package uni.java.project.videoshare.video;

import java.io.Serializable;

public class VideoCreateBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String title;
	private String url;
	private String description;

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
	
	

}
