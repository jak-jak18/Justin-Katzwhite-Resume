package com.example.justin.resume_internal

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class TastebudzFrag : Fragment() {

    val LOG_TAG = javaClass.simpleName
    val data = listOf(
        Pair(R.mipmap.tastebudz_shadows, R.string.tastebudz_intro),
        Pair(R.mipmap.nfc_tags, R.string.tastebudz_background),
        Pair(R.mipmap.course_fragment, R.string.tastebudz_course),
        Pair(R.mipmap.item_fragment, R.string.tastebudz_item),
        Pair(R.mipmap.order_dialog, R.string.tastebudz_order),
        Pair(R.mipmap.waiter_fragment, R.string.tastebudz_waiter)
    )

    companion object{
        fun newInstance() = TastebudzFrag()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.tastebudz_layout, container, false)
        view.findViewById<ListView>(R.id.tastebudz_listview).adapter = TastebudzAdapter(inflater)
        return view
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return AnimationUtils.loadAnimation(activity, if (enter) android.R.anim.fade_in else android.R.anim.fade_out)
    }

    inner class TastebudzAdapter(val inflater: LayoutInflater) : BaseAdapter(){

        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Pair<Int,Int> {
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return 1
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            if(position == 0){
                val view = inflater.inflate(R.layout.initial_card, parent, false)
                view.findViewById<ImageView>(R.id.imageView)
                    .setImageResource(getItem(position).first)
                val textView = view.findViewById<TextView>(R.id.textView)
                textView.text = context?.resources?.getString(getItem(position).second)
                textView.typeface = Typeface.createFromAsset(
                    context?.assets,
                    "font/EB_Garamond/EBGaramond-Regular.ttf"
                )
                return view
            }

            val view = inflater.inflate(R.layout.card, parent, false)
            view.findViewById<ImageView>(R.id.imageView)
                .setImageResource(getItem(position).first)
            val textView = view.findViewById<TextView>(R.id.textView)
            textView.text = context?.resources?.getString(getItem(position).second)
            textView.typeface = Typeface.createFromAsset(
                context?.assets,
                "font/EB_Garamond/EBGaramond-Regular.ttf"
            )
            return view
        }
    }
}