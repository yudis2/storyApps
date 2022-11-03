package com.yudisdwi.submission1.auth.main.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudisdwi.submission1.auth.ViewModelFactory
import com.yudisdwi.submission1.auth.welcome.WelcomeActivity
import com.yudisdwi.submission1.databinding.FragmentHomeBinding
import com.yudisdwi.submission1.model.UserPreference

val Context.dataStore:  DataStore<Preferences> by preferencesDataStore(name = "settings")

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var homeViewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupViewModel()

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        val storyAdapter = StoryAdapter()
        binding.rvList.adapter = storyAdapter
        return root
    }

    private fun setupViewModel() {
        homeViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
        )[HomeViewModel::class.java]

        homeViewModel.getUser().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                startActivity(Intent(requireContext(), WelcomeActivity::class.java))
            } else {
                Toast.makeText(context, StringBuilder("Bearer ").append(user.token), Toast.LENGTH_LONG).show()
                val token = StringBuilder("Bearer ").append(user.token).toString()
                getStory(token)
            }
        }
    }

    private fun getStory(token: String) {
        homeViewModel.listStory(token)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}