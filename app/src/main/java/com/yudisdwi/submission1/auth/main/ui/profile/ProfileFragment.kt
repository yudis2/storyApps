package com.yudisdwi.submission1.auth.main.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yudisdwi.submission1.auth.ViewModelFactory
import com.yudisdwi.submission1.auth.main.ui.home.dataStore
import com.yudisdwi.submission1.auth.welcome.WelcomeActivity
import com.yudisdwi.submission1.databinding.FragmentProfileBinding
import com.yudisdwi.submission1.model.UserPreference

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModels { viewModelFactory }
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelFactory = ViewModelFactory(UserPreference.getInstance(requireContext()!!.dataStore))

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAction()

        return root
    }

    private fun setupAction() {
        binding.logoutButton.setOnClickListener {
            profileViewModel.logout()
            intentFragment()
        }
    }

    private fun intentFragment() {
        activity?.let {
            val intent = Intent(it, WelcomeActivity::class.java)
            it.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}