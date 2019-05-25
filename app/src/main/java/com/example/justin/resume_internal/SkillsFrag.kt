package com.example.justin.resume_internal

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.skills_layout.*

class SkillsFrag : Fragment() {

    val LOG_TAG = this.javaClass.simpleName
    var skill_titles: Array<String>? = null

    companion object{
        fun newInstance() = SkillsFrag()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.skills_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        textView.setText("Skill Frag")
        skill_titles = resources!!.getStringArray(R.array.skill_titles)
        pager.adapter = CoursePagerAdapter(activity!!.supportFragmentManager, context!!)
        pager.currentItem = 0
        tabLayout.setupWithViewPager(pager)
    }

    internal inner class CoursePagerAdapter(fm: FragmentManager, var context: Context) : FragmentPagerAdapter(fm) {

        override fun getItem(i: Int): Fragment {
            return Section.newInstance(skill_titles!![i])
        }

        override fun getCount(): Int {
            return skill_titles!!.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            Log.d(LOG_TAG, skill_titles!![position])
            return skill_titles!![position]
        }
    }


    class Section : Fragment() {

        val SECTION = "SECTION"

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.holder_view, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            val textView = view.findViewById<TextView>(R.id.holderView)
            textView.setText(arguments!!.getString(SECTION))
            textView.typeface = Typeface.createFromAsset(context!!.assets, "font/EB_Garamond/EBGaramond-Regular.ttf")
        }

        companion object {

            val SECTION = "SECTION"

            fun newInstance(section: String): Section {
                val fragment = Section()
                val args = Bundle()
                args.putString(SECTION, section)
                fragment.arguments = args
                return fragment
            }
        }
    }

}