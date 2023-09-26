package com.example.task1_android_components.items_list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task1_android_components.Item
import com.example.task1_android_components.R
import com.example.task1_android_components.databinding.FragmentItemsListBinding
import com.example.task1_android_components.item_details.ItemDetailsFragment
import com.example.task1_android_components.utils.Constants

class ItemsListFragment : Fragment() {

    private lateinit var binding: FragmentItemsListBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemsListBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("item_id", Context.MODE_PRIVATE)

        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        val itemsList: List<Item> = Constants.getItemsList()
        val listAdapter = MyItemRecyclerViewAdapter(itemsList) {
            onItemClick(it)
        }
        binding.list.adapter = listAdapter

    }

    private fun onItemClick(item: Item) {
        saveItemInLocalStorage(item.id)
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainerView, ItemDetailsFragment.newInstance(item))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun saveItemInLocalStorage(itemId: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(Constants.ITEM_ID_KEY, itemId)
        editor.apply()
    }
}