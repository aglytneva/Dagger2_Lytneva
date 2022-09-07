/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.dagger.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.dagger.MyApplication
import com.example.android.dagger.R
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.enterdetails.EnterDetailsFragment
import com.example.android.dagger.registration.termsandconditions.TermsAndConditionsFragment
import javax.inject.Inject

//Если вы откроете RegistrationActivity.kt, мы создадим ViewModel в методе onCreate непосредственно
// перед вызовом supportFragmentManager. Мы не хотим создавать его вручную, мы хотим, чтобы Dagger
// предоставил его. Для этого нам необходимо:
// Аннотируйте поле с помощью @Inject.
// Удалите его экземпляр из метода onCreate.

class RegistrationActivity : AppCompatActivity() {

//    Stores an instance of RegistrationComponent so that its Fragments can access it
    // Сохраняет экземпляр RegistrationComponent, чтобы его фрагменты могли получить к нему доступ
    // lateinit var RegistrationComponent: RegistrationComponent
    lateinit var registrationComponent: RegistrationComponent

    // @Inject annotated fields will be provided by Dagger
//    когда @Inject аннотируется в конструкторе класса, он сообщает Dagger, как предоставлять экземпляры этого класса.
//    Когда он аннотируется в поле класса, он сообщает Dagger, что ему нужно заполнить поле экземпляром этого типа.
    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

//    Важно: при использовании Activity внедряйте Dagger в метод onCreate Activity перед вызовом super.onCreate,
    //    чтобы избежать проблем с восстановлением фрагментов.
    //    В super.onCreate Activity на этапе восстановления будет присоединять фрагменты,
    //    которым может потребоваться доступ к привязкам активности.

    override fun onCreate(savedInstanceState: Bundle?) {
//        Актуально до внедрения субкомпонента
//          Ask Dagger to inject our dependencies
//        // Просим даггер внедрить наши зависимости
//        // Вызов appComponent.inject(this) заполняет поля, аннотированные RegistrationActivity
//        // с помощью @Inject (т.е. RegistrationViewModel).
//        (application as MyApplication).appComponent.inject(this)

        // Creates an instance of Registration component by grabbing the factory from the app graph
        registrationComponent = (application as MyApplication).appComponent.registrationComponent().create()
        // Injects this activity to the just created registration component
        registrationComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

//       Аннотация @Inject позволяет каждый раз не инициализировать VIEWModel
//        registrationViewModel = RegistrationViewModel((application as MyApplication).userManager)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, EnterDetailsFragment())
            .commit()
    }

    /**
     * Callback from EnterDetailsFragment when username and password has been entered
     */
    fun onDetailsEntered() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, TermsAndConditionsFragment())
            .addToBackStack(TermsAndConditionsFragment::class.java.simpleName)
            .commit()
    }

    /**
     * Callback from T&CsFragment when TCs have been accepted
     */
    fun onTermsAndConditionsAccepted() {
        registrationViewModel.registerUser()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
