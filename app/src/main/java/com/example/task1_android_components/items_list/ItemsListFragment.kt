package com.example.task1_android_components.items_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.task1_android_components.R
import com.example.task1_android_components.databinding.FragmentItemsListBinding
import com.example.task1_android_components.item_details.ItemDetailsFragment
import com.example.task1_android_components.items_list.adapter.MyItemRecyclerViewAdapter
import com.example.task1_android_components.model.Item
import com.example.task1_android_components.preferences.PreferencesManager

class ItemsListFragment : Fragment() {

    private lateinit var binding: FragmentItemsListBinding

    private val viewModel: ItemsListViewModel by viewModels {
        ItemsListViewModelFactory(PreferencesManager(requireContext().applicationContext))
    }

    private val listAdapter by lazy {
        MyItemRecyclerViewAdapter {
            onItemClick(it)
        }
    }

    private fun onItemClick(item: Item) {
        saveItemInLocalStorage(itemId = item.id)
        showItemDetailsScreen(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = listAdapter

        initObserver()

        viewModel.loadItems()
    }

    private fun initObserver() {
        viewModel.itemsList.observe(viewLifecycleOwner) { itemsList ->
            showItems(itemsList)
        }
    }

    private fun saveItemInLocalStorage(itemId: Int) {
        viewModel.saveLastOpenedItemId(itemId)
    }

    private fun showItems(itemsList: List<Item>) {
        listAdapter.submitList(itemsList)
    }

    private fun showItemDetailsScreen(item: Item) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainerView, ItemDetailsFragment.newInstance(item))
            ?.addToBackStack(null)
            ?.commit()
    }
}