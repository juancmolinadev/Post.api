package app.tomapedidos.implapi.dto;

public class PostDto {
    private int userId;
    private int postId;
    private String postTitle;
    private String postBody;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return this.postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return this.postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return this.postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "userId=" + userId +
                ", id=" + postId +
                ", title='" + postTitle + '\'' +
                ", body='" + postBody + '\'' +
                '}';
    }
}
