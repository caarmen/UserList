package ca.rmen.userlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ca.rmen.userlist.R
import ca.rmen.userlist.databinding.ActivityMainBinding
import ca.rmen.userlist.databinding.UserListItemBinding
import ca.rmen.userlist.viewmodel.UserDisplayData
import ca.rmen.userlist.viewmodel.UserListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        binding.swipeToRefresh.setOnRefreshListener {
            adapter.refresh()
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                binding.errorBanner.isVisible = loadState.refresh is LoadState.Error
                binding.swipeToRefresh.isRefreshing = loadState.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launch {
            viewModel.users.collect { pagingData ->
                lifecycleScope.launch {
                    adapter.submitData(pagingData)
                }
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<UserDisplayData>() {
                override fun areItemsTheSame(
                    oldItem: UserDisplayData,
                    newItem: UserDisplayData
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: UserDisplayData,
                    newItem: UserDisplayData
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    class UsersAdapter :
        PagingDataAdapter<UserDisplayData, UserViewHolder>(DIFF_CALLBACK) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
            UserViewHolder(
                UserListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val userDisplayData = getItem(position)
            if (userDisplayData != null) {
                holder.binding.data = userDisplayData
                holder.binding.userCard.setOnClickListener {
                    DetailsActivity.start(holder.binding.root.context, userDisplayData)
                }
            }
        }
    }


    class UserViewHolder(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root)

}