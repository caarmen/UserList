package ca.rmen.userlist.view

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DataBindingAdapters {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }

    @BindingAdapter("app:visible")
    @JvmStatic
    fun setVisible(view: View, visible: Boolean) {
        view.isVisible = visible
    }
}