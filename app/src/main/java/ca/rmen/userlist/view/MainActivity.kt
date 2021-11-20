package ca.rmen.userlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.rmen.userlist.R
import ca.rmen.userlist.databinding.ActivityMainBinding
import ca.rmen.userlist.databinding.UserListItemBinding
import ca.rmen.userlist.model.UserModel
import ca.rmen.userlist.viewmodel.UserListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel =
            ViewModelProvider(this, UserListViewModel.factory()).get(UserListViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = UsersAdapter()
        binding.users.adapter = adapter
        viewModel.users.observe(this) {
            adapter.submitList(it.users)
        }
    }

    companion object {

        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<UserModel>() {
                override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return oldItem == newItem
                }
            }
    }

    class UsersAdapter :
        ListAdapter<UserModel, UserViewHolder>(DIFF_CALLBACK) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
            UserViewHolder(
                UserListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val userModel = getItem(position)
            holder.binding.data = userModel
            holder.binding.userCard.setOnClickListener {
                DetailsActivity.start(holder.binding.root.context, userModel)
            }
        }
    }


    class UserViewHolder(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root)

}