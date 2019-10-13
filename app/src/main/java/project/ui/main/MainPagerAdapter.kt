package project.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import project.ui.main.calender.CalenderFragment
import project.ui.main.classes.ClassesFragment

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var mTabCount: Int = 0

    init {
        this.mTabCount = 0
    }

    override fun getCount(): Int {
        return mTabCount
    }

    fun setCount(count: Int) {
        mTabCount = count
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ClassesFragment.newInstance()
        else -> CalenderFragment.newInstance()
    }
}
