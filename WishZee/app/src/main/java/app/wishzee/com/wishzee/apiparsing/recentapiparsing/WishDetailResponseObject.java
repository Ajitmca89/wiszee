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

    public  void responseParseMethod(Object response){
        WishDetailResponseObject wishDetailResponseObject = new WishDetailResponseObject();
        try {
            JSONObject jsonObject = new JSONObject(response.toString());
            wishDetailResponseObject.setMsg(jsonObject.optString("msg"));
            wishDetailResponseObject.setStatus(jsonObject.optBoolean("status"));
            if (wishDetailResponseObject.getStatus().equals("true"))
            wishDetailResponseObject.setWishDetailsArrayList(getWishDetailInMethod(jsonObject.optJSONArray("wishDetail")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<WishDetails> getWishDetailInMethod(JSONArray jsonArray) {
        ArrayList<WishDetails> wishDetailsArrayList = new ArrayList<>();
        WishDetails wishDetails;
        for (int i = 0; i < jsonArray.length(); i++){
            wishDetails = new WishDetails();
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            wishDetails.setPid(jsonObject.optString("pid"));
            wishDetails.setDescription(jsonObject.optString("description"));
            wishDetails.setPostType(jsonObject.optString("post_type"));
            wishDetails.setType(jsonObject.optString("type"));
            wishDetails.setSharePostId(jsonObject.optString("sharePostId"));
            wishDetails.setFriendId(jsonObject.optString("friend_id"));
            wishDetails.setCreateDate(jsonObject.optString("createDate"));
            wishDetails.setPostDate(jsonObject.optString("postDate"));
            wishDetails.setShare(jsonObject.optString("share"));
            wishDetails.setWow(jsonObject.optString("wow"));
            wishDetails.setWowStatus(jsonObject.optString("wowStatus"));
            wishDetails.setSilly(jsonObject.optString("silly"));
            wishDetails.setSillyStatus(jsonObject.optString("sillyStatus"));
            wishDetails.setComment(jsonObject.optString("comment"));
            wishDetails.setFulfill(jsonObject.optString("fulfill"));
            wishDetails.setUserDetails(getUserDetailsMethod(jsonObject.optJSONObject("userDetail")));
            wishDetails.setNotificationDetails(getNotificationDetailsMethod(jsonObject.optJSONObject("notificationDetail")));
            wishDetails.setPostImageArrayList(getPostImageMethod(jsonObject.optJSONArray("postimage")));

            wishDetailsArrayList.add(wishDetails);
        }
        return wishDetailsArrayList;
    }

    private ArrayList<PostImage> getPostImageMethod(JSONArray jsonArray) {
        ArrayList<PostImage> postImageArrayList = new ArrayList<>();
        PostImage postImage;
        for (int i = 0; i < jsonArray.length(); i++){
            postImage = new PostImage();
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            postImage.setId(jsonObject.optString("id"));
            postImage.setModuleId(jsonObject.optString("moduleId"));
            postImage.setPostUrl(jsonObject.optString("posturl"));
            postImage.setCreateDate(jsonObject.optString("createDate"));

            postImageArrayList.add(postImage);
        }
        return postImageArrayList;
    }

    private NotificationDetails getNotificationDetailsMethod(JSONObject jsonObject) {
        NotificationDetails notificationDetails = new NotificationDetails();
        notificationDetails.setId(jsonObject.optString("id"));
        notificationDetails.setType(jsonObject.optString("type"));
        notificationDetails.setNotUid(jsonObject.optString("notUid"));
        notificationDetails.setDescription(jsonObject.optString("description"));
        notificationDetails.setModule(jsonObject.optString("module"));
        notificationDetails.setInsertDate(jsonObject.optString("insertDate"));
        notificationDetails.setReach(jsonObject.optString("reach"));
        notificationDetails.setCheckStatus(jsonObject.optString("checkStatus"));
        notificationDetails.setIdOth(jsonObject.optString("IdOth"));
        notificationDetails.setStatus(jsonObject.optString("status"));

        return notificationDetails;
    }

    private UserDetails getUserDetailsMethod(JSONObject jsonObject) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUid(jsonObject.optString("uid"));
        userDetails.setFullName(jsonObject.optString("fullname"));
        userDetails.setProfilePicUrl(jsonObject.optString("profile_pic_url"));

        return userDetails;
    }
}
