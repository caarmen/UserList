package ca.rmen.userlist;


import androidx.lifecycle.LiveData;
import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingAdapters {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    // In 2017 we don't yet have full databinding support for livedata.
    @BindingAdapter("refreshing")
    public static void setRefreshing(SwipeRefreshLayout view, LiveData<Boolean> liveData) {
        if (liveData != null && liveData.getValue() != null) {
            view.setRefreshing(liveData.getValue());
        }
    }

    // In 2017 we don't yet have full databinding support for livedata.
    @BindingAdapter("visible")
    public static void setVisible(View view, LiveData<Boolean> liveData) {
        if (liveData != null && liveData.getValue() == Boolean.TRUE) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
