package com.bookwyrm.backend.comment.input;

public class CommentUploadInput {
    private String commentId;
    private String author;
    private String content;
    private boolean anonymousFlag;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(boolean anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }
}
