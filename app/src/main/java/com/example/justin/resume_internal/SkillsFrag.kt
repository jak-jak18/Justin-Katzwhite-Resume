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
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.skills_layout.*

class SkillsFrag : Fragment() {

    val LOG_TAG = this.javaClass.simpleName

    companion object{
        fun newInstance() = SkillsFrag()
        val text = mapOf(
            "Android" to R.array.Android,
            "Cloud" to R.array.Cloud,
            "Firebase" to R.array.Firebase,
            "IDE" to R.array.IDE,
            "OS" to R.array.OS,
            "Github" to R.array.Github
        )
        val images = mapOf(
            "Android" to intArrayOf(R.mipmap.java, R.mipmap.kotlin),
            "Cloud" to intArrayOf(R.mipmap.python, R.mipmap.gcp, R.mipmap.aws),
            "Firebase" to intArrayOf(R.mipmap.firebase),
            "IDE" to intArrayOf(R.mipmap.studio, R.mipmap.pycharm),
            "OS" to intArrayOf(R.mipmap.windows, R.mipmap.mint),
            "Github" to intArrayOf(R.mipmap.github)
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.skills_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pager.adapter = PagerAdapter(
            childFragmentManager,
            resources.getStringArray(R.array.skill_titles)
        )
        pager.currentItem = 0
        tabLayout.setupWithViewPager(pager)
    }

    override fun onCreateAnimation(
        transit: Int,
        enter: Boolean,
        nextAnim: Int
    ): Animation {
        return AnimationUtils.loadAnimation(activity,
            if (enter) android.R.anim.fade_in else android.R.anim.fade_out)
    }

    inner class PagerAdapter(fm: FragmentManager, val skill_titles: Array<String>
    ): FragmentPagerAdapter(fm) {
        override fun getItem(i: Int): Fragment {
            return Skill.newInstance(skill_titles[i])
        }

        override fun getCount(): Int {
            return skill_titles.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return skill_titles[position]
        }
    }

    class Skill : Fragment() {
        val LOG_TAG = this.javaClass.simpleName
        val SKILL = "SKILL"

        companion object {
            val SKILL = "SKILL"

            fun newInstance(section: String): Skill {
                val fragment = Skill()
                val args = Bundle()
                args.putString(SKILL, section)
                fragment.arguments = args
                return fragment
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val skill = arguments!!.getString(SKILL) as String
            Log.d(LOG_TAG, skill)
            val view = inflater.inflate(R.layout.skills_list, container, false)
            val list = view.findViewById<ListView>(R.id.skills_list)
            list.adapter = SkillAdapter(
                context!!.resources.getStringArray(text[skill] as Int),
                images[skill],
                inflater
            )
            return view
        }
    }

    class SkillAdapter(val text: Array<String>?, val images: IntArray?,
                       val inflater: LayoutInflater) : BaseAdapter(){
        val LOG_TAG = this.javaClass.simpleName

        override fun getCount(): Int { return text!!.size }

        override fun getItem(position: Int): Any {
            return text!![position]
        }

        override fun getItemId(position: Int): Long {
            return 1
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = inflater.inflate(R.layout.skills_view, parent, false)
            val textView = view.findViewById<TextView>(R.id.textView)
            textView.text = text!![position]
            textView.typeface = Typeface.createFromAsset(inflater.context.assets,
                "font/EB_Garamond/EBGaramond-Regular.ttf")
            val imageView = view.findViewById<ImageView>(R.id.imageView)
//            Log.d(LOG_TAG, images[position].toString())
            imageView.setImageResource(images!![position])
            return view
        }
    }

}