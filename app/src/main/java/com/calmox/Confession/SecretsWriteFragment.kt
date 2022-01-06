package com.calmox.Confession

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.calmox.R
import com.calmox.databinding.FragmentSecretsWriteBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.navigation.fragment.findNavController


class SecretsWriteFragment : Fragment() {
    lateinit var bind : FragmentSecretsWriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentSecretsWriteBinding.inflate(inflater, container, false)

        val database = Firebase.database
        val myRef = database.getReference("Secrets")
        val sp : SharedPreferences = requireContext().getSharedPreferences("buttonState",Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sp.edit()
        val buttonState = sp.getBoolean("buttonState", true)
        bind.ButtonConfess.isEnabled = buttonState
        bind.secret.isEnabled = buttonState
        if(!buttonState){
            bind.ButtonConfess.text = "banned"
            bind.secret.hint = "banned"
        }
        bind.ButtonConfess.setOnClickListener {
            val secret = bind.secret.text.toString().lowercase().trim()
            when {
                secret.length < 10 -> {
                    Toast.makeText(
                        context,
                        "secret too short",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                secret.length>500 -> {
                    Toast.makeText(
                        context,
                        "secret too long",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                invalidKeyword() -> {
                    Toast.makeText(
                        context,
                        "banned for violation",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.gotoFeedSecret)
                    editor.putBoolean("buttonState",false)
                    editor.apply()
                }
                else -> {
                    val id = myRef.push().key
                    myRef.child(id!!).setValue(bind.secret.text.toString().lowercase().trim())
                        .addOnCompleteListener {
                            bind.secret.text = null
                        }
                    Toast.makeText(
                        context,
                        "secret confessed, now does it feels better",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.gotoFeedSecret)
                }
            }
        }
        bind.ButtonRules.setOnClickListener{
            findNavController().navigate(R.id.gotoRuleSecret)
        }

        return bind.root
    }
    private fun invalidKeyword(): Boolean {
        val keywords = arrayOf<String>("buy", "buy now", "coupon code", "horny", "fuck me", "num",
            "@", ".com", ".in",".org", "contact", "_", "#", "asd",
            "123", "456", "258","789","987","654","321","=", "hello world", ";",
            "binod", "rasoda", "rashi","chod", "chood", "lund", "laura",
            "bhen", "jaat", "jat", "bal", "jhat", "jhath","tit","chd",
            "bsdk", "bhos", "randi","chut", "kamin","/","muh","lele",
            "fucker","dick","shit","cum","cunt","pussy","nigger",
            "fawk","zv","hs","dd","xy","bm","vu","dv","sx","ys","bx", "cj", "cx", "dx",
            "fq", "fx", "gq", "gx", "hx", "jc", "jf", "jg", "jp", "jq", "jv", "jw", "jx", "jz", "kq", "kx",
            "kz", "mx", "pz", "qb", "qc", "qd", "qf", "qg", "qh", "qj", "qk", "ql", "qm", "qn", "qp", "qs",
            "qt", "qv", "qw", "qx", "qy", "qz", "sx", "vb", "vd", "vf", "vg", "vh", "vj", "vm", "vp", "vq", "vt",
            "vw", "vx", "wv", "wx", "xj", "xx", "zj", "zq", "zx","111","000","01","dkdk","djdj","ttt","uuu","qqq","qqq",
            "gkgk","aaa","bbb","ccc","ddd","\n\n")
        for(keyword in keywords) {
            if (bind.secret.text.toString().trim().contains(keyword,ignoreCase = true)) {
                return true
            }
        }
        return false
    }
}