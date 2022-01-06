package com.calmox.Setting

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.calmox.Constants.Constants
import com.calmox.R
import com.calmox.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    lateinit var bind : FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentSettingBinding.inflate(inflater, container, false)

        bind.rateUs.setOnClickListener { rateApp() }
        bind.feedback.setOnClickListener { sendFeedback() }
        bind.privacyPolicy.setOnClickListener { privacyPolicy() }
        bind.termsOfUse.setOnClickListener { termsOfUse() }
        bind.about.setOnClickListener { aboutApp() }

        return bind.root
    }

    private fun rateApp() {
        /* get package name */
        val appPackageName = requireContext().packageName

        /* handle link of the Google Play Store */try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (errorException: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }
    private fun sendFeedback() {
        val feedbackIntent = Intent(Intent.ACTION_SEND)
        feedbackIntent.type = "text/pain"

        // check if GMAIL app is installed
        feedbackIntent.setPackage("com.google.android.gm")
        feedbackIntent.putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf(Constants.EMAIL_ADDRESS)
        )
        feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, arrayOf(getString(R.string.feedbackSubject)))

        // try to open gmail app, if !installed, exception will be showed
        try {
            startActivity(feedbackIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context, getString(R.string.gmailNotInstalled), Toast.LENGTH_SHORT).show()
        }
    }
    private fun privacyPolicy() {
        val privacyPolicy = Intent(Intent.ACTION_VIEW)
        privacyPolicy.data = Uri.parse(Constants.PRIVACY_POLICY_URL)
        requireActivity().startActivity(privacyPolicy)
    }
    private fun termsOfUse() {
        val termsOfUse = Intent(Intent.ACTION_VIEW)
        termsOfUse.data = Uri.parse(Constants.TERMS_OF_USE_URL)
        startActivity(termsOfUse)
    }
    private fun aboutApp() {
        findNavController().navigate(R.id.gotoAbout)
    }

}