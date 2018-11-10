package com.mystacademy.android.modules.login

import android.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.apollographql.apollo.ApolloCall.Callback
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.mystacademy.android.R
import com.mystacademy.android.R.layout
import com.mystacademy.android.UserProfileQuery
import com.mystacademy.android.UserProfileQuery.Viewer
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepositoryImpl
import com.mystacademy.android.repositories.user.UserRepositoryImpl

class UserProfileFragment : Fragment() {
    private val handler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layout.user_profil_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view == null) return

        val callback = object : Callback<UserProfileQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                e.printStackTrace()
            }

            override fun onResponse(response: Response<UserProfileQuery.Data>) {
                if (response.data()?.viewer() == null) {
                    onFailure(ApolloException("Null Response"))
                    return
                }
                handler.post {
                    displayUserProfile(response.data()!!.viewer()!!, view)
                }
            }

        }

        UserRepositoryImpl(SharedPreferencesRepositoryImpl.getInstance(activity.applicationContext!!)).getUserProfile(
            callback
        )
    }

    private fun displayUserProfile(userProfile: Viewer, view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.profile_toolbar)
        toolbar.title = userProfile.username()

        view.findViewById<TextView>(R.id.profile_email_content_tv).text = userProfile.email()
        view.findViewById<TextView>(R.id.profile_birthday_content_tv).text = userProfile.birthDate().toString()
        view.findViewById<TextView>(R.id.profile_gender_content_tv).text = userProfile.gender()?.name
        view.findViewById<TextView>(R.id.profile_grade_content_tv).text = userProfile.grade()?.name()
        view.findViewById<TextView>(R.id.profile_location_content_tv).text = userProfile.location()?.get(0)?.toString()
        view.findViewById<TextView>(R.id.profile_register_date_content_tv).text = userProfile.registerDate()
        view.findViewById<TextView>(R.id.profile_first_name_content_tv).text = userProfile.username()


    }
}
