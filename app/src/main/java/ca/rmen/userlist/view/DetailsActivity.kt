package ca.rmen.userlist.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ca.rmen.userlist.R
import ca.rmen.userlist.databinding.UserDetailsBinding
import ca.rmen.userlist.viewmodel.UserDisplayData

class DetailsActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_USER_DETAILS = "user_details"

        @VisibleForTesting
        fun getLaunchIntent(context: Context, userDisplayData: UserDisplayData) =
            Intent(context, DetailsActivity::class.java)
                .putExtra(EXTRA_USER_DETAILS, userDisplayData)

        fun start(context: Context, userDisplayData: UserDisplayData) {
            context.startActivity(getLaunchIntent(context, userDisplayData))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDisplayData = intent.getParcelableExtra<UserDisplayData>(EXTRA_USER_DETAILS)!!
        supportActionBar?.let { actionBar ->
            actionBar.title = userDisplayData.name
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        val binding =
            DataBindingUtil.setContentView<UserDetailsBinding>(this, R.layout.user_details)
        binding.data = userDisplayData
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