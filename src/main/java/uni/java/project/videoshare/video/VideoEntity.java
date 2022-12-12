package uni.java.project.videoshare.video;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import uni.java.project.videoshare.comment.CommentEntity;
import uni.java.project.videoshare.user.UserEntity;

@Entity
@Table(name = "videos")
public class VideoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, length = 255, nullable = false)
	private String title;
	
	@Column(unique = true, length = 1000, nullable = false)
	private String url;
	
	@Column(length = 2000, nullable = true)
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity owner;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "video")
	private List<CommentEntity> comments;
	
	public VideoEntity() {}
	
	public VideoEntity(String title, String url, String description, UserEntity owner)
	{
		this.title = title;
		this.url = url;
		this.description = description;
		this.owner = owner;
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

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	
	
	
}
