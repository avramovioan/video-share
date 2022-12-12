package uni.java.project.videoshare.comment;

import java.io.Serializable;

public class CommentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	
	public CommentBean() {}
	
	public CommentBean(CommentEntity comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
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

	
}
