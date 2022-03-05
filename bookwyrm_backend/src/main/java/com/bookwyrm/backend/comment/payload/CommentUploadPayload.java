package com.bookwyrm.backend.comment.payload;

import java.util.List;

public class CommentUploadPayload {

    private List<String> messages;
    private String commentId;
    private String commentAuthor;
    private String commentContent;
    private boolean commentAnonymousFlag;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public boolean getCommentAnonymousFlag() {
        return commentAnonymousFlag;
    }

    public void setCommentAnonymousFlag(boolean commentAnonymousFlag) {
        this.commentAnonymousFlag = commentAnonymousFlag;
    }
}
