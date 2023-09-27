package com.example.task1_android_components.item_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.task1_android_components.databinding.FragmentItemDetailsBinding
import com.example.task1_android_components.model.Item

private const val ARG_ID = "id"
private const val ARG_NAME = "name"
private const val ARG_DESCRIPTION = "description"

class ItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailsBinding

    private val viewModel: ItemDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        loadArguments()
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            state.item?.id?.let {
                binding.textViewId.text = it.toString()
            }
            state.item?.name.let {
                binding.textViewName.text = it
            }
            state.item?.description.let {
                binding.textViewDescription.text = it
            }
        }
    }

    private fun loadArguments() {
        val itemId = arguments?.getInt(ARG_ID)
        val itemName = arguments?.getString(ARG_NAME) ?: ""
        val itemDescription = arguments?.getString(ARG_DESCRIPTION) ?: ""
        if (itemId != null) {
            viewModel.onEvent(ItemDetailsEvent.LoadItem(Item(itemId, itemName, itemDescription)))
        }
    }

    companion object {
        fun newInstance(item: Item) = ItemDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, item.id)
                putString(ARG_NAME, item.name)
                putString(ARG_DESCRIPTION, item.description)
            }
        }
    }
}