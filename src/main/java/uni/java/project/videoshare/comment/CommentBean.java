package uni.java.project.videoshare.comment;

import java.io.Serializable;

import uni.java.project.videoshare.user.UserBean;

public class CommentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	private UserBean owner;
	
	public CommentBean() {}
	
	public CommentBean(CommentEntity comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.owner = new UserBean(comment.getOwner());
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

	public UserBean getOwner() {
		return owner;
	}

	public void setOwner(UserBean owner) {
		this.owner = owner;
	}

	
}
