package uni.java.project.videoshare.user;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import uni.java.project.videoshare.comment.CommentEntity;
import uni.java.project.videoshare.video.VideoEntity;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String username;
	
	@Column(length = 32, nullable = false)
	private String password;
	
	@Column(length = 260, nullable = false, unique = true)
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	private List<VideoEntity> videos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	private List<CommentEntity> comments;
	
	public UserEntity(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public UserEntity() {	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<VideoEntity> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoEntity> videos) {
		this.videos = videos;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}
	
	

	
	
}
