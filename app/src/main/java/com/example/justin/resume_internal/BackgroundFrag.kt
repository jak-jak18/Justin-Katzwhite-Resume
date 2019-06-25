package com.example.justin.resume_internal

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils

import kotlinx.android.synthetic.main.background_scroll.*

class BackgroundFrag : Fragment(){

    companion object{
        fun newInstance() = BackgroundFrag()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.background_scroll, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = resources.getString(R.string.background_information)
        textView.typeface = Typeface.createFromAsset(context?.assets,
            "font/EB_Garamond/EBGaramond-Regular.ttf")
        textView.setTextColor(resources.getColor(android.R.color.black))
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return AnimationUtils.loadAnimation(activity, if (enter) android.R.anim.fade_in else android.R.anim.fade_out)
    }
}