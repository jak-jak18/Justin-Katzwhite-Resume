package com.example.justin.resume_internal

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.contact_layout.*

class ContactFrag : Fragment() {

    companion object{
        fun newInstance() = ContactFrag()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.contact_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = resources.getString(R.string.contact);
        textView.typeface = Typeface.createFromAsset(
            context?.assets,
            "font/EB_Garamond/EBGaramond-Regular.ttf"
        )
        val prefs: SharedPreferences? = activity?.getPreferences(Context.MODE_PRIVATE)
        if (prefs?.getBoolean("flag", false) == false){
            prefs.edit().putBoolean("flag", true).apply()
            firstAttempt()
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return AnimationUtils.loadAnimation(activity, if (enter) android.R.anim.fade_in else android.R.anim.fade_out)
    }

    fun firstAttempt(){
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle(R.string.first_attempt_title)
            .setMessage(R.string.first_attempt)
            .setPositiveButton("Okay",
                DialogInterface.OnClickListener { dialog, which ->  reviewIntent()})
            .setNegativeButton("Nope",
                DialogInterface.OnClickListener { dialog, which ->  secondAttempt()})
            .show()
    }

    fun secondAttempt(){
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle(R.string.second_attempt_title)
            .setMessage(resources.getString(R.string.second_attmpt))
            .setPositiveButton("Only a minute, right?",
                DialogInterface.OnClickListener { dialog, which ->  reviewIntent()})
            .setNegativeButton("Still not happening.",
                DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(context, "O well", Toast.LENGTH_SHORT).show()})
            .show()
    }

    fun reviewIntent() {
        val webpage: Uri = Uri.parse("https://play.google.com/store/apps/details?id=com.justin.resume")
        val sendIntent = Intent(Intent.ACTION_VIEW, webpage)
        val title: String = resources.getString(R.string.chooser_title)
        val chooser: Intent = Intent.createChooser(sendIntent, title)
        startActivity(chooser)

    }
}