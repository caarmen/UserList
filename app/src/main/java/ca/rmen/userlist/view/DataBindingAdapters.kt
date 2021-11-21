package ca.rmen.userlist.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

object DataBindingAdapters {
    @BindingAdapter("app:avatarUrl")
    @JvmStatic
    fun setAvatarUrl(view: ImageView, url: String?) {
        Glide.with(view.context).load(url).transform(CircleCrop()).into(view)
    }
}