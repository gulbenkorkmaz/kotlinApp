package com.example.instakotlinapp.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.instakotlinapp.R
import kotlinx.android.synthetic.main.fragment_profile_edit.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileEditFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =inflater.inflate(R.layout.fragment_profile_edit, container, false)

        view.imgClose.setOnClickListener {

            activity?.onBackPressed()


        }


        return view


    }

}
