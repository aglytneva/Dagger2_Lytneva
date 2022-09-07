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

import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.user.UserManager
import javax.inject.Inject

/**
 * RegistrationViewModel is the ViewModel that the Registration flow ([RegistrationActivity]
 * and fragments) uses to keep user's input data.
 */

//Для автоматического построения графа приложения Dagger должен знать,
// как создавать экземпляры для классов в графе. Один из способов сделать это —
// аннотировать конструктор классов с помощью @Inject.
// Параметры конструктора будут зависимостями этого типа.
//Благодаря аннотации @Inject Dagger знает:
    // Как создать экземпляры типа RegistrationViewModel.
    // RegistrationViewModel имеет UserManager в качестве зависимости, поскольку конструктор принимает
    // экземпляр UserManager в качестве аргумента.
//Даггер еще не умеет создавать типы UserManager.
// Выполните тот же процесс и добавьте аннотацию @Inject в конструктор UserManager.

//class RegistrationViewModel(val userManager: UserManager) {

// Scopes this ViewModel to components that use @ActivityScope
// Область этой ViewModel для компонентов, которые используют @ActivityScope
@ActivityScope
class RegistrationViewModel @Inject constructor(val userManager: UserManager) {

    private var username: String? = null
    private var password: String? = null
    private var acceptedTCs: Boolean? = null

    fun updateUserData(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun acceptTCs() {
        acceptedTCs = true
    }

    fun registerUser() {
        assert(username != null)
        assert(password != null)
        assert(acceptedTCs == true)

        userManager.registerUser(username!!, password!!)
    }
}
