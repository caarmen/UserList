package ca.rmen.userlist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ca.rmen.userlist.R
import ca.rmen.userlist.databinding.ActivityMainBinding
import ca.rmen.userlist.viewmodel.UserListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel =
            ViewModelProvider(this, UserListViewModel.factory()).get(UserListViewModel::class.java)

        binding.viewModel = viewModel

    }
}