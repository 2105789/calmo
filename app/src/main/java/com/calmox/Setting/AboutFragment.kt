package com.calmox.Setting

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.calmox.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    lateinit var bind : FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentAboutBinding.inflate(inflater, container, false)

        val sp = requireContext().getSharedPreferences("name_pref", Context.MODE_PRIVATE)
        val name_in = "dear " + sp.getString("inputName", "") + " ,"
        bind.name.text = name_in
        bind.appVersion.text = "Version".plus(" ").plus(get_version_name(requireContext()))

        return bind.root
    }
    @Throws(PackageManager.NameNotFoundException::class)
    fun get_version_name(context: Context): String? {
        val packageInfo: PackageInfo =
            context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }
}