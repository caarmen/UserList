package ca.rmen.userlist.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ca.rmen.userlist.R
import ca.rmen.userlist.databinding.UserDetailsBinding
import ca.rmen.userlist.model.UserModel

class DetailsActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_USER_DETAILS = "user_details"
        fun start(context: Context, userModel: UserModel) {
            val intent = Intent(context, DetailsActivity::class.java)
                .putExtra(EXTRA_USER_DETAILS, userModel)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userModel = intent.getParcelableExtra<UserModel>(EXTRA_USER_DETAILS)!!
        supportActionBar?.let { actionBar ->
            actionBar.title = userModel.name.first
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        val binding =
            DataBindingUtil.setContentView<UserDetailsBinding>(this, R.layout.user_details)
        binding.data = userModel
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}