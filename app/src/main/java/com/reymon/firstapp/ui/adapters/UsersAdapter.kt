package com.reymon.firstapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reymon.firstapp.R
import com.reymon.firstapp.data.entities.Users
import com.reymon.firstapp.databinding.LayoutUsersBinding

class UsersAdapter (val listUsers:List<Users>): RecyclerView.Adapter<UsersAdapter.ViewHolderUsers>() {

    class ViewHolderUsers(view: View): RecyclerView.ViewHolder(view) {

        private var binding: LayoutUsersBinding = LayoutUsersBinding.bind(view)

        fun render(item : Users){
            binding.txtID.text = item.userId.toString()
            binding.txtName.text = item.firstName.toString()
            binding.textLastName.text = item.lastName.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUsers {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolderUsers(inflater.inflate(R.layout.layout_users,parent,false))
    }

    override fun getItemCount(): Int = listUsers.size

    override fun onBindViewHolder(holder: ViewHolderUsers, position: Int) {
        holder.render(listUsers[position])
    }

}