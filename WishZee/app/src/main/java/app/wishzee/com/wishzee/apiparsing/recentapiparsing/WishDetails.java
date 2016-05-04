package app.wishzee.com.wishzee.apiparsing.recentapiparsing;

import java.util.ArrayList;

/**
 * Created by Ajit Gupta on 5/4/2016.
 */
public class WishDetails {

    private String pid;
    private String description;
    private String postType;
    private String type;
    private String sharePostId;
    private String friendId;
    private String createDate;
    private String postDate;
    private String share;
    private String wow;
    private String wowStatus;
    private String silly;
    private String sillyStatus;
    private String comment;
    private String fulfill;
    private UserDetails userDetails;
    private NotificationDetails notificationDetails;
    private ArrayList<PostImage> postImageArrayList;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSharePostId() {
        return sharePostId;
    }

    public void setSharePostId(String sharePostId) {
        this.sharePostId = sharePostId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getWow() {
        return wow;
    }

    public void setWow(String wow) {
        this.wow = wow;
    }

    public String getWowStatus() {
        return wowStatus;
    }

    public void setWowStatus(String wowStatus) {
        this.wowStatus = wowStatus;
    }

    public String getSilly() {
        return silly;
    }

    public void setSilly(String silly) {
        this.silly = silly;
    }

    public String getSillyStatus() {
        return sillyStatus;
    }

    public void setSillyStatus(String sillyStatus) {
        this.sillyStatus = sillyStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFulfill() {
        return fulfill;
    }

    public void setFulfill(String fulfill) {
        this.fulfill = fulfill;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }

    public void setNotificationDetails(NotificationDetails notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    public ArrayList<PostImage> getPostImageArrayList() {
        return postImageArrayList;
    }

    public void setPostImageArrayList(ArrayList<PostImage> postImageArrayList) {
        this.postImageArrayList = postImageArrayList;
    }
}
