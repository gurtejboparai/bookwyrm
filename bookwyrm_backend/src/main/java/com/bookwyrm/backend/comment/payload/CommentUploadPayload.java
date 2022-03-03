package com.bookwyrm.backend.comment.payload;

import java.util.List;

public class CommentUploadPayload {

    private List<String> messages;
    private String commentAuthor;
    private String commentDescription;
    private boolean commentAnonymousFlag;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public boolean getCommentAnonymousFlag() {
        return commentAnonymousFlag;
    }

    public void setCommentAnonymousFlag(boolean commentAnonymousFlag) {
        this.commentAnonymousFlag = commentAnonymousFlag;
    }
}
