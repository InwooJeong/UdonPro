package com.cookandroid.udonpro

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerFragmentAdapter(fr: Fragment) : FragmentStateAdapter(fr) {


    // 2. ViesPager2에서 노출시킬 Fragment 의 갯수 설정
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0-> MainForm_Share()
            else-> MainForm_Request()
        }
    }
}
