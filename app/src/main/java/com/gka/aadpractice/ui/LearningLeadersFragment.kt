package com.gka.aadpractice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.gka.aadpractice.R
import com.gka.aadpractice.adapters.LearningLeaderRecyclerAdapter
import com.gka.aadpractice.models.LearningLeader
import com.gka.aadpractice.services.EndPoints
import com.gka.aadpractice.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_learning_leaders.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LearningLeadersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val learningLeadersService = ServiceBuilder.gadsApiInstance(EndPoints::class.java)
        val call: Call<List<LearningLeader>> = learningLeadersService.getLearningLeaders()

        loadProgress(true)

        call.enqueue(object : Callback<List<LearningLeader>> {
            override fun onResponse(
                call: Call<List<LearningLeader>>,
                response: Response<List<LearningLeader>>
            ) {
                loadProgress(false)
                if (response.isSuccessful) {
                    rvLearningLeaders.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = LearningLeaderRecyclerAdapter(context, response.body()!!)
                        hasFixedSize()
                    }
                }
            }

            override fun onFailure(call: Call<List<LearningLeader>>, t: Throwable) {
                Toast.makeText(context, "Failed: ${t.message}", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun loadProgress(state: Boolean) {
       learnersGroup.isVisible = state
    }
}