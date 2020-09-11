package com.gka.aadpractice.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gka.aadpractice.R
import com.gka.aadpractice.SubmitActivity
import com.gka.aadpractice.adapters.ViewPagerAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_leader_board.*

class LeaderBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)

        tabLayout.setupWithViewPager(viewPager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, 0)


        viewPagerAdapter.addFragment(LearningLeadersFragment(), getString(R.string.learning_leaders))
        viewPagerAdapter.addFragment(SkillLeadersFragment(), getString(R.string.skill_iq_leaders))

        viewPager.adapter = viewPagerAdapter

        submitButton.setOnClickListener {
            startActivity(Intent(this, SubmitActivity::class.java))
        }

    }
}