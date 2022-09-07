package com.example.android.dagger.di

import com.example.android.dagger.di.user.UserComponent
import com.example.android.dagger.login.LoginComponent
import com.example.android.dagger.registration.RegistrationComponent
import dagger.Module

// This module tells a Component which are its subcomponents
@Module(subcomponents = [RegistrationComponent::class, LoginComponent::class, UserComponent::class])
class AppSubcomponents
