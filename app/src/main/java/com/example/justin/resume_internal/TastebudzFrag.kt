package com.example.justin.resume_internal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class TastebudzFrag : Fragment() {

    companion object{
        fun newInstance() = TastebudzFrag()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tastebudz_layout, container, false)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return AnimationUtils.loadAnimation(activity, if (enter) android.R.anim.fade_in else android.R.anim.fade_out)
    }
}