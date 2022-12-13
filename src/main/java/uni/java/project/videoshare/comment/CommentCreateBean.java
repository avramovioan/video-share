package uni.java.project.videoshare.comment;

import java.io.Serializable;

public class CommentCreateBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String content;
	
	public CommentCreateBean() {}
	
	public CommentCreateBean(CommentEntity comment) {
		this.content = comment.getContent();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
