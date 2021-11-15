package com.cookandroid.udonpro

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainForm : Fragment() {

    var viewPager: ViewPager2? =null
    private var tabLayout: TabLayout? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onAttach(context: Context) { super.onAttach(context) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.mainform, container, false)

        viewPager= view.findViewById<ViewPager2>(R.id.viewPager)
        tabLayout= view.findViewById<TabLayout>(R.id.tablayout2)

        var viewpagerFragmentAdapter = ViewpagerFragmentAdapter(this)
        viewPager?.adapter =  viewpagerFragmentAdapter

        var tabTitles = listOf<String>("공유도서", "요청도서")

        TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager!!.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewPager!!.currentItem = tab!!.position
            }
        })
        return view
    }
}

