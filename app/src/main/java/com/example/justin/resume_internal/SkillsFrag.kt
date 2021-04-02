package com.example.justin.resume_internal

import android.graphics.Typeface
import android.os.Bundle
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
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
        val icons = arrayOf(
            R.mipmap.android3,
            R.mipmap.cloud,
            R.mipmap.firebase4,
            R.mipmap.intellij,
            R.mipmap.mint2,
            R.mipmap.github
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
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

        val titles : ArrayList<String> =  ArrayList()
        titles.addAll(text.keys)
        for (i in 0..tabLayout.tabCount-1){
            val customV = LayoutInflater.from(context).inflate(R.layout.title_layout, container)
            customV.findViewById<ImageView>(R.id.icon).setImageResource(icons[i])
            customV.findViewById<TextView>(R.id.title).text = titles[i]
            tabLayout.getTabAt(i)!!.customView = customV
        }
    }

    override fun onCreateAnimation(
        transit: Int, enter: Boolean, nextAnim: Int
    ): Animation {
        return AnimationUtils.loadAnimation(activity,
            if (enter) android.R.anim.fade_in else android.R.anim.fade_out)
    }

    inner class PagerAdapter(
        fm: FragmentManager, val skill_titles: Array<String>
    ): FragmentPagerAdapter(fm) {
        override fun getItem(i: Int): Fragment {
            return Skill.newInstance(skill_titles[i])
        }

        override fun getCount(): Int {
            return skill_titles.size
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
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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

    class SkillAdapter(
        val text: Array<String>?, val images: IntArray?, val inflater: LayoutInflater
    ) : BaseAdapter(){
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