package com.example.justin.resume_internal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TastebudzFrag : Fragment() {

    companion object{
        fun newInstance() = TastebudzFrag()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tastebudz_layout, container, false)
    }
}