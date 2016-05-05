package app.wishzee.com.wishzee.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.apiparsing.recentapiparsing.PostImage;
import app.wishzee.com.wishzee.apiparsing.recentapiparsing.WishDetails;
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
                .inflate(R.layout.image_show_layout, parent, false);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        VolleySingleTon volleySingleTon = VolleySingleTon.getInstance(context);
        WishDetails wishDetails = (WishDetails) list.get(position);
        ArrayList<PostImage> imageArray = wishDetails.getPostImageArrayList();
        String imageUrl = (imageArray != null && imageArray.size() > 0) ? imageArray.get(0).getPostUrl() : null;

        if (TextUtils.isEmpty(imageUrl)) {
            viewHolder.imgViewIcon.setDefaultImageResId(R.mipmap.ic_launcher);
        } else {
            viewHolder.imgViewIcon.setImageUrl(imageUrl, volleySingleTon.getImageLoader());
        }
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public NetworkImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imgViewIcon = (NetworkImageView) itemLayoutView.findViewById(R.id.imageView);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}

