package com.example.task1_android_components.item_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task1_android_components.Item
import com.example.task1_android_components.databinding.FragmentItemDetailsBinding

class ItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemDetailsBinding.inflate(inflater, container, false)

        binding.textViewId.text = arguments?.getInt("id").toString()
        binding.textViewName.text = arguments?.getString("name").toString()
        binding.textViewDescription.text = arguments?.getString("description").toString()

        return binding.root
    }


    companion object {
        fun newInstance(item: Item) = ItemDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt("id", item.id)
                putString("name", item.name)
                putString("description", item.description)
            }
        }
    }
}