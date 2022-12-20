package com.sample.github.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.github.databinding.ItemUsersListBinding
import com.sample.github.network.model.UserListItem
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class UserPagingAdapter  @Inject constructor()  : PagingDataAdapter<UserListItem, UserPagingAdapter.UserViewHolder>(
    COMPARATOR
) {


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
      //  val item = getItem(position)
        if(item!=null){
            holder.bind(item)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUsersListBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    class UserViewHolder(private val binding: ItemUsersListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserListItem) {
            binding.data = item
            binding.executePendingBindings()

        }
    }



    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<UserListItem>(){
            override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}