package io.myoganugraha.footballleague.view.Favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import io.myoganugraha.footballleague.R
import kotlinx.android.synthetic.main.activity_favorite.*
import java.util.ArrayList

class Favorite : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = "Favorite Match"
        }

        viewPager = viewpager_favorite
        tabLayout = tabs
        setupViewPagger(viewPager)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPagger(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PreviousMatchFavoriteFragment(), "Previous")
        adapter.addFragment(NextMatchFavoriteFragment(), "Next")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()


        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }
    }
}
