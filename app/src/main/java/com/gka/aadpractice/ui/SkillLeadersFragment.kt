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
import com.gka.aadpractice.adapters.SkillLeaderRecyclerAdapter
import com.gka.aadpractice.models.SkillLeader
import com.gka.aadpractice.services.EndPoints
import com.gka.aadpractice.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_skill_leaders.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SkillLeadersFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val skillService = ServiceBuilder.retrofitInstance(EndPoints::class.java)
        val call: Call<List<SkillLeader>> = skillService.getSkillLeaders()

        loadProgress(true)

        call.enqueue(object : Callback<List<SkillLeader>> {
            override fun onResponse(
                call: Call<List<SkillLeader>>,
                response: Response<List<SkillLeader>>
            ) {
                loadProgress(false)
                rvSkillLeaders.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = SkillLeaderRecyclerAdapter(context, response.body()!!)
                    hasFixedSize()
                }
            }

            override fun onFailure(call: Call<List<SkillLeader>>, t: Throwable) {
                Toast.makeText(context, "Failed: ${t.message}", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun loadProgress(state: Boolean) {
        skillGroup.isVisible = state
    }
}