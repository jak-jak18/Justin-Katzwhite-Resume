package com.example.justin.resume_internal

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ContactFrag : Fragment() {

    companion object{
        fun newInstance() = ContactFrag()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.contact_layout, container, false)
    }
}