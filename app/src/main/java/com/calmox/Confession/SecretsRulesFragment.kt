package com.calmox.Confession

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.calmox.databinding.FragmentSecretsRulesBinding

class SecretsRulesFragment : Fragment() {
    lateinit var bind :FragmentSecretsRulesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentSecretsRulesBinding.inflate(inflater, container, false)

        return bind.root
    }

}