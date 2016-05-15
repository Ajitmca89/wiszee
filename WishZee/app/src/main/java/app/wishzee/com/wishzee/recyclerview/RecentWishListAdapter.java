package app.wishzee.com.wishzee.recyclerview;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.apiparsing.recentapiparsing.PostImage;
import app.wishzee.com.wishzee.apiparsing.recentapiparsing.WishDetails;
import app.wishzee.com.wishzee.preference.SharedPreferencesManager;
import app.wishzee.com.wishzee.volleycustomrequest.VolleySingleTon;

/**
 * Created by Ajit Gupta on 5/4/2016.
 */
public class RecentWishListAdapter extends RecyclerView.Adapter<RecentWishListAdapter.ViewHolder> {
    List<Object> list;
    Context context;

    public RecentWishListAdapter(List<Object> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecentWishListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_screen_row_layout, parent, false);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        VolleySingleTon volleySingleTon = VolleySingleTon.getInstance(context);
        WishDetails wishDetails = (WishDetails) list.get(position);
        //ArrayList<WishDetails> imageArray = wishDetails.getPostImageArrayList();
        String userName = wishDetails.getUserDetails().getFullName();
        String userProfilePic = wishDetails.getUserDetails().getProfilePicUrl();
        String description = wishDetails.getDescription();
        String date = wishDetails.getCreateDate();
        String gossip = wishDetails.getComment();
        String share = wishDetails.getShare();
        String wow = wishDetails.getWow();
        String pid = wishDetails.getPid();
        String userId = SharedPreferencesManager.getID(context);
        String userDetailsId = wishDetails.getUserDetails().getUid();
        String fullFill = wishDetails.getFulfill();
        String type = wishDetails.getType();
        String pendingRequest = wishDetails.getFulFillPendingRequest();

        viewHolder.txtName.setText(userName);
        viewHolder.imgUser.setImageUrl(userProfilePic, volleySingleTon.getImageLoader());
        viewHolder.txtDescription.setText(description);
        viewHolder.txtDate.setText(date);
        viewHolder.txtGossip.setText(gossip + "Gossip");
        viewHolder.txtShare.setText(share + "Shares");
        viewHolder.txtWow.setText(wow + "Wow");

        if (userId.equals(userDetailsId) && fullFill == String.valueOf(0)){
                    //full nnot show
        }
        else if (type.equalsIgnoreCase("other")){
                //fullfill button show
        }
        else {
            //not showing
        }






       /* //String imageUrl = (imageArray != null && imageArray.size() > 0) ? imageArray.get(0).getPostUrl() : null;

        if (TextUtils.isEmpty(imageUrl)) {
            viewHolder.imgViewIcon.setDefaultImageResId(R.mipmap.ic_launcher);
        } else {
            viewHolder.imgViewIcon.setImageUrl(imageUrl, volleySingleTon.getImageLoader());
        }*/
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public NetworkImageView imgUser;
        public TextView txtName, txtDate, txtDescription,txtWow, txtGossip, txtShare, txtImages;
        AppCompatButton btnFullFill, btnFullShowFullFill, btnFullFilled;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imgUser = (NetworkImageView) itemLayoutView.findViewById(R.id.imgUser);
            txtDate = (TextView) itemLayoutView.findViewById(R.id.txtDate);
            txtDescription = (TextView) itemLayoutView.findViewById(R.id.txtDescription);
            txtWow = (TextView) itemLayoutView.findViewById(R.id.txtWow);
            txtGossip = (TextView) itemLayoutView.findViewById(R.id.txtGossip);
            txtShare = (TextView) itemLayoutView.findViewById(R.id.txtShare);
            txtImages = (TextView) itemLayoutView.findViewById(R.id.txtImages);
            txtName = (TextView) itemLayoutView.findViewById(R.id.txtName);
            btnFullFill = (AppCompatButton) itemLayoutView.findViewById(R.id.btnFullFill);
            btnFullFilled = (AppCompatButton) itemLayoutView.findViewById(R.id.btnFullFilled);
            btnFullShowFullFill = (AppCompatButton) itemLayoutView.findViewById(R.id.btnFullShow);

        }
    }
    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}

