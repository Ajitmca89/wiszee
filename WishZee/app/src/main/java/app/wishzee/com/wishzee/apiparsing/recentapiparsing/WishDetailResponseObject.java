package app.wishzee.com.wishzee.apiparsing.recentapiparsing;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import app.wishzee.com.wishzee.apiparsing.ApiBaseData;

/**
 * Created by Ajit Gupta on 5/4/2016.
 */
public class WishDetailResponseObject extends ApiBaseData {

    ArrayList<WishDetails> wishDetailsArrayList;

    public ArrayList<WishDetails> getWishDetailsArrayList() {
        return wishDetailsArrayList;
    }

    public void setWishDetailsArrayList(ArrayList<WishDetails> wishDetailsArrayList) {
        this.wishDetailsArrayList = wishDetailsArrayList;
    }

    WishDetailResponseObject wishDetailResponseObject;

    public void responseParseMethod(Object response) {
        wishDetailResponseObject = new WishDetailResponseObject();
        try {
            JSONObject jsonObject = new JSONObject(response.toString());
            wishDetailResponseObject.setMsg(jsonObject.optString("msg"));
            wishDetailResponseObject.setStatus(jsonObject.optString("status"));
            if (jsonObject.optString("status").equals("true"))
                wishDetailResponseObject.setWishDetailsArrayList(getWishDetailInMethod(jsonObject.optJSONArray("wishDetail")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<WishDetails> getWishDetailInMethod(JSONArray jsonArray) {
        wishDetailsArrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            WishDetails wishDetails = new WishDetails();
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            if (jsonObject.has("pid"))
                wishDetails.setPid(jsonObject.optString("pid"));
            if (jsonObject.has("description"))
                wishDetails.setDescription(jsonObject.optString("description"));
            if (jsonObject.has("post_type"))
                wishDetails.setPostType(jsonObject.optString("post_type"));
            if (jsonObject.has("type"))
                wishDetails.setType(jsonObject.optString("type"));
            if (jsonObject.has("sharePostId"))
                wishDetails.setSharePostId(jsonObject.optString("sharePostId"));
            if (jsonObject.has("friend_id"))
                wishDetails.setFriendId(jsonObject.optString("friend_id"));
            if (jsonObject.has("createDate"))
                wishDetails.setCreateDate(jsonObject.optString("createDate"));
            if (jsonObject.has("postDate"))
                wishDetails.setPostDate(jsonObject.optString("postDate"));
            if (jsonObject.has("share"))
                wishDetails.setShare(jsonObject.optString("share"));
            if (jsonObject.has("wow"))
                wishDetails.setWow(jsonObject.optString("wow"));
            if (jsonObject.has("wowStatus"))
                wishDetails.setWowStatus(jsonObject.optString("wowStatus"));
            if (jsonObject.has("silly"))
                wishDetails.setSilly(jsonObject.optString("silly"));
            if (jsonObject.has("sillyStatus"))
                wishDetails.setSillyStatus(jsonObject.optString("sillyStatus"));
            if (jsonObject.has("comment"))
                wishDetails.setComment(jsonObject.optString("comment"));
            if (jsonObject.has("fulfill"))
                wishDetails.setFulfill(jsonObject.optString("fulfill"));
            if (jsonObject.has("userDetail"))
                wishDetails.setUserDetails(getUserDetailsMethod(jsonObject.optJSONObject("userDetail")));
            if (jsonObject.has("notificationDetail"))
                wishDetails.setNotificationDetails(getNotificationDetailsMethod(jsonObject.optJSONObject("notificationDetail")));
            if (jsonObject.has("postimage")) {
                wishDetails.setPostImageArrayList(getPostImageMethod(jsonObject.optJSONArray("postimage")));
            }
        }
        return wishDetailsArrayList;
    }

    private ArrayList<PostImage> getPostImageMethod(JSONArray jsonArray) {
        ArrayList<PostImage> postImageArrayList = new ArrayList<>();
        PostImage postImage;
        for (int i = 0; i < jsonArray.length(); i++) {
            postImage = new PostImage();
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            if (jsonObject.has("id"))
                postImage.setId(jsonObject.optString("id"));
            if (jsonObject.has("moduleId"))
                postImage.setModuleId(jsonObject.optString("moduleId"));
            if (jsonObject.has("posturl"))
                postImage.setPostUrl(jsonObject.optString("posturl"));
            if (jsonObject.has("createDate"))
                postImage.setCreateDate(jsonObject.optString("createDate"));

            postImageArrayList.add(postImage);
        }
        return postImageArrayList;
    }

    private NotificationDetails getNotificationDetailsMethod(JSONObject jsonObject) {
        NotificationDetails notificationDetails = new NotificationDetails();
        if (jsonObject.has("id"))
            notificationDetails.setId(jsonObject.optString("id"));
        if (jsonObject.has("type"))
            notificationDetails.setType(jsonObject.optString("type"));
        if (jsonObject.has("notUid"))
            notificationDetails.setNotUid(jsonObject.optString("notUid"));
        if (jsonObject.has("description"))
            notificationDetails.setDescription(jsonObject.optString("description"));
        if (jsonObject.has("module"))
            notificationDetails.setModule(jsonObject.optString("module"));
        if (jsonObject.has("insertDate"))
            notificationDetails.setInsertDate(jsonObject.optString("insertDate"));
        if (jsonObject.has("reach"))
            notificationDetails.setReach(jsonObject.optString("reach"));
        if (jsonObject.has("checkStatus"))
            notificationDetails.setCheckStatus(jsonObject.optString("checkStatus"));
        if (jsonObject.has("IdOth"))
            notificationDetails.setIdOth(jsonObject.optString("IdOth"));
        if (jsonObject.has("status"))
            notificationDetails.setStatus(jsonObject.optString("status"));

        return notificationDetails;
    }

    private UserDetails getUserDetailsMethod(JSONObject jsonObject) {
        UserDetails userDetails = new UserDetails();
        if (jsonObject.has("uid"))
            userDetails.setUid(jsonObject.optString("uid"));
        if (jsonObject.has("fullname"))
            userDetails.setFullName(jsonObject.optString("fullname"));
        if (jsonObject.has("profile_pic_url"))
            userDetails.setProfilePicUrl(jsonObject.optString("profile_pic_url"));

        return userDetails;
    }
}
