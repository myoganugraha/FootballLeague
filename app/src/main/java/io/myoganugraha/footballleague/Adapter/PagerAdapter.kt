package io.myoganugraha.footballleague.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.myoganugraha.footballleague.View.NextMatch.NextMatch
import io.myoganugraha.footballleague.View.PreviousMatch.PreviousMatch

class PagerAdapter(fragment: FragmentManager) : FragmentPagerAdapter(fragment) {

    private val pages = listOf(
        PreviousMatch(),
        NextMatch()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Previous Match"
            else -> "Next Match"
        }
    }

}