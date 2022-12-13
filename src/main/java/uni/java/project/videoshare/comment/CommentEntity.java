package uni.java.project.videoshare.comment;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import uni.java.project.videoshare.user.UserEntity;
import uni.java.project.videoshare.video.VideoEntity;

@Entity
@Table(name = "comments")
public class CommentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 1000)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private VideoEntity video;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity owner;
	
	
	public CommentEntity() {}
	
	public CommentEntity(String content,VideoEntity video, UserEntity owner)
	{
		this.content = content;
		this.owner = owner;
		this.video = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public VideoEntity getVideo() {
		return video;
	}

	public void setVideo(VideoEntity video) {
		this.video = video;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}
	
	
}
