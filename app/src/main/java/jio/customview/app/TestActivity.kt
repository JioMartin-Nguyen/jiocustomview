package jio.customview.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager


class TestActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        tabLayout = findViewById(R.id.tabPagerNews)
        viewPager = findViewById(R.id.viewPagerNews)
        tabLayout.setupWithViewPager(viewPager)

        val pagerAdapter =  MyPagerAdapter(supportFragmentManager,this)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        /*for (i in 0 until tabLayout.getTabCount()) {
            val tab = tabLayout.getTabAt(i)
            if (tab != null)
                tab.setCustomView(pagerAdapter.getTabView(i))
        }

        tabLayout.getTabAt(0)!!.getCustomView()!!.setSelected(true)

        pagerAdapter.SetOnSelectView(tabLayout, 0)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val c = tab.position
                pagerAdapter.SetOnSelectView(tabLayout, c)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val c = tab.position
                pagerAdapter.SetUnSelectView(tabLayout, c)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })*/

    }


}
