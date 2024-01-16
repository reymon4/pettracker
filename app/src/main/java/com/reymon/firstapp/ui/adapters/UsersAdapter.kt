package com.reymon.firstapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.reymon.firstapp.R
import com.reymon.firstapp.data.local.entities.Users
import com.reymon.firstapp.databinding.LayoutUsersBinding

class UsersAdapter (val listUsers:List<Users>): RecyclerView.Adapter<UsersAdapter.ViewHolderUsers>() {

    class ViewHolderUsers(view: View): RecyclerView.ViewHolder(view) {

        private var binding: LayoutUsersBinding = LayoutUsersBinding.bind(view)

        fun render(item : Users){
            binding.image.load("https://cdn4.iconfinder.com/data/icons/avatars-xmas-giveaway/128/batman_hero_avatar_comics-512.png"){
                placeholder(R.drawable.batman_90804)
                crossfade(enable = true)
            }

            binding.txtID.text = item.userId.toString()
            binding.txtName.text = item.firstName.toString() + " "+item.lastName.toString()

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