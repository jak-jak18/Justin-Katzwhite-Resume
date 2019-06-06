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
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.skills_layout.*

class SkillsFrag : Fragment() {

    val LOG_TAG = this.javaClass.simpleName
    val skill_titles: ArrayList<String> = ArrayList()

    init {
        images["Android"] = intArrayOf(R.mipmap.java, R.mipmap.kotlin)
        images["Cloud"] = intArrayOf(R.mipmap.python, R.mipmap.gcp, R.mipmap.aws)
        images["Firebase"] = intArrayOf(R.mipmap.firebase)
        images["IDE"] = intArrayOf(R.mipmap.studio, R.mipmap.pycharm)
        images["OS"] = intArrayOf(R.mipmap.windows, R.mipmap.mint)
        images["Github"] = intArrayOf(R.mipmap.github)
    }

    companion object{
        fun newInstance() = SkillsFrag()
        val images : HashMap<String, IntArray> = HashMap()
        val text : HashMap<String, Array<String>> = HashMap()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        text["Android"] = context!!.resources.getStringArray(R.array.Android)
        text["Cloud"] = context.resources.getStringArray(R.array.Cloud)
        text["Firebase"] = context.resources.getStringArray(R.array.Firebase)
        text["IDE"] = context.resources.getStringArray(R.array.IDE)
        text["OS"] = context.resources.getStringArray(R.array.OS)
        text["Github"] = context.resources.getStringArray(R.array.Github)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.skills_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        textView.setText("Skill Frag")
        skill_titles.addAll(resources.getStringArray(R.array.skill_titles))
        pager.adapter = CoursePagerAdapter(childFragmentManager, context!!)
        pager.currentItem = 0
        tabLayout.setupWithViewPager(pager)
    }

    internal inner class CoursePagerAdapter(fm: FragmentManager, var context: Context) : FragmentPagerAdapter(fm) {

        override fun getItem(i: Int): Fragment {
            return Skill.newInstance(skill_titles[i])
        }

        override fun getCount(): Int {
            return skill_titles.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
//            Log.d(LOG_TAG, skill_titles[position])
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
//            val images : ArrayList<Int> = ArrayList()
//            images.add(R.mipmap.java1)
//            images.add(R.mipmap.kotlin)
//
//            val text : ArrayList<String> = ArrayList()
//            text.add(resources.getString(R.string.java_text))
//            text.add(resources.getString(R.string.kotlin_text))
            Log.d(LOG_TAG, SKILL)
            val view = inflater.inflate(R.layout.skills_list, container, false)
            val list = view.findViewById<ListView>(R.id.skills_list)
            list.adapter = SkillAdapter(SkillsFrag.text[arguments!![SKILL]],
                SkillsFrag.images[arguments!![SKILL]],
                inflater)
            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//            val textView = view.findViewById<TextView>(R.id.holderView)
//            textView.setText(arguments!!.getString(SKILL))
//            textView.typeface = Typeface.createFromAsset(context!!.assets, "font/EB_Garamond/EBGaramond-Regular.ttf")
        }
    }

    class SkillAdapter(val text: Array<String>?, val images: IntArray?,
                       val inflater: LayoutInflater) : BaseAdapter(){
        val LOG_TAG = this.javaClass.simpleName
//        val test_arry : Array<Int> = Array(2) {3;2}

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