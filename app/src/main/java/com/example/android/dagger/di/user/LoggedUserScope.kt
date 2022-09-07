package com.example.android.dagger.di.user

import javax.inject.Scope

//Мы можем аннотировать и UserComponent, и UserDataRepository этой аннотацией,
// чтобы UserComponent всегда мог предоставить один и тот же экземпляр UserDataRepository.
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class LoggedUserScope