package com.angelika.lesson_37

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angelika.lesson_37.databinding.FragmentFruitBinding

class FruitFragment : Fragment() {

    private var _binding: FragmentFruitBinding? = null
    private val binding get() = _binding!!

    private var fruits = ArrayList<Fruit>()

    private val fruitAdapter = FruitAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFruitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFruit.adapter = fruitAdapter

        addFruit()
        getData()
        addDataToRecycle()
    }

    private fun addFruit() = with(binding) {
        btnAddFruit.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container, AddingFruitFragment())
                .addToBackStack("First Fragment")
                .commit()
        }
    }

    private fun getData() = with(binding) {
        arguments?.let {
            val url = it.getString(URL_KEY)
            val newNameFruit = it.getString(NAME_KEY)
            etName.setText(newNameFruit)
            etLinkToPhoto.setText(url)
        }
    }

    private fun addDataToRecycle() {
        binding.btnAdd.setOnClickListener {
            val url = binding.etLinkToPhoto.text.toString()
            val newNameFruit = binding.etName.text.toString()
            val fruit = Fruit(newNameFruit, url)
            fruits.add(fruit)
            fruitAdapter.addImage(fruit)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val IMAGE_KEY = "image"
        const val NAME_KEY = "text"
        const val URL_KEY = "url"
        private const val KEY_FRUITS = "fruits"
    }
}