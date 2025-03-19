package com.campusdual_grupo3.bookandgo.presentation.register.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.campusdual_grupo3.bookandgo.databinding.ItemDropDownBinding
import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity

class DropdownAdapter(
    private val onSelectCategory: (category: CategoryEntity) -> Unit
): ListAdapter<CategoryEntity, DropdownAdapter.CategoryViewHolder>(ListAdapterCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDropDownBinding.inflate(layoutInflater, parent, false)

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


     inner class CategoryViewHolder(
         private val binding: ItemDropDownBinding
     ): RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryEntity) {
            binding.textTerms.text = category.name
            binding.checkboxTerms.setOnCheckedChangeListener { _, isChecked ->
                onSelectCategory(category)
            }
        }
    }

    class ListAdapterCallback: DiffUtil.ItemCallback<CategoryEntity>() {
        override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem == newItem
        }

    }
}