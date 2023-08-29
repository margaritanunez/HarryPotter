package com.example.harrypotter.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.harrypotter.R
import com.example.harrypotter.data.local.HPEntity
import com.example.harrypotter.databinding.ItemListBinding

class AdapterCharacter: RecyclerView.Adapter<AdapterCharacter.ItemListViewHolder>() {
    lateinit var binding: ItemListBinding
    private var listCharacters = mutableListOf<HPEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCharacter.ItemListViewHolder {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterCharacter.ItemListViewHolder, position: Int) {
        val characterHP = listCharacters[position]
        holder.bind(characterHP)
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    fun setData(charactersHP: List<HPEntity>){
        this.listCharacters.clear()
        this.listCharacters.addAll(charactersHP)
        notifyDataSetChanged()
    }

    class ItemListViewHolder (val characterBinding: ItemListBinding): RecyclerView.ViewHolder(characterBinding.root){
        fun bind(character: HPEntity){
            characterBinding.tvName.text = character.name
            characterBinding.imgCharacter.load(character.image)

            characterBinding.cvList.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", character.id)
                Navigation.findNavController(characterBinding.root).navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }

    }
}