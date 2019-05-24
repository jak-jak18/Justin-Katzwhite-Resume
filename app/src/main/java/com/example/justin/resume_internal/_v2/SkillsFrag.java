package com.example.justin.resume_internal._v2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justin.resume_internal.R;

public class SkillsFrag extends Fragment {

    public SkillsFrag() {}

    public static ContactFrag newInstance() {
        ContactFrag fragment = new ContactFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.contact, container, false);
    }
}
