package ca.rmen.userlist.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DataBindingAdapters {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, url: String?) {
        Glide.with(view.context).load(url).into(view)
    }
}