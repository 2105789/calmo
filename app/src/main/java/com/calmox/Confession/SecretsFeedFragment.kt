package com.calmox.Confession

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmox.Adapter.SecretsAdapter
import com.calmox.Model.SecretsModel
import com.calmox.R
import com.calmox.databinding.FragmentSecretsFeedBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SecretsFeedFragment : Fragment() {
    private lateinit var bind : FragmentSecretsFeedBinding
    private lateinit var  recyclerView: RecyclerView
    private lateinit var  secretsArrayList: ArrayList<SecretsModel>
    private lateinit var  secretsAdapter: SecretsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentSecretsFeedBinding.inflate(inflater, container, false)

        if(!isOnline(requireContext())){
            bind.secretsRecyclerView.isVisible = false
            bind.head.isVisible = false
            bind.ButtonWrite.isVisible = false
            bind.head2.isVisible = false
            bind.networkError.isVisible = true
        }

        recyclerView = bind.secretsRecyclerView
        //val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val staggeredGridLayoutManager = LinearLayoutManager(context)
        staggeredGridLayoutManager.reverseLayout = true
        staggeredGridLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.setHasFixedSize(true)

        secretsArrayList = firebaseSecretsData() as ArrayList<SecretsModel>
        secretsAdapter = SecretsAdapter(secretsArrayList)
        recyclerView.adapter = secretsAdapter

        bind.ButtonWrite.setOnClickListener {
            findNavController().navigate(R.id.gotoWriteSecret)
        }

        return bind.root
    }

    private  fun firebaseSecretsData() : List<SecretsModel>{
        val list = ArrayList<SecretsModel>()
        val dbref = FirebaseDatabase.getInstance().getReference("Secrets")
        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                secretsArrayList.clear()
                if(snapshot.exists()){
                    for(i in snapshot.children){
                        val item = SecretsModel(i.value.toString())
                        list+=item
                    }
                }
                secretsAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return list
    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}