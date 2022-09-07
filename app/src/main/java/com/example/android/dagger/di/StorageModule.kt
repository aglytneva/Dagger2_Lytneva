package com.example.android.dagger.di

import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module

//Способ, которым мы сообщаем Dagger, как предоставить хранилище, отличается, потому что хранилище —
// это интерфейс, и поэтому его нельзя создать напрямую. Нам нужно сообщить Dagger, какую реализацию
// Storage мы хотим использовать.
// В данном случае это SharedPreferencesStorage. Для этого мы будем использовать модуль Dagger.
//
// Модуль Dagger — это класс, помеченный @Module. Подобно компонентам, модули Dagger сообщают Dagger,
// как предоставлять экземпляры определенного типа. Зависимости определяются с помощью аннотаций @Provides и @Binds.

// Tells Dagger this is a Dagger module
// Because of @Binds, StorageModule needs to be an abstract class

@Module
abstract class StorageModule {
    // Makes Dagger provide SharedPreferencesStorage when a Storage type is requested

//    Используйте @Binds, чтобы сообщить Dagger, какую реализацию необходимо использовать при предоставлении интерфейса.
//    @Binds должен аннотировать абстрактную функцию. Тип возвращаемого значения абстрактной функции
//    — это интерфейс, для которого мы хотим предоставить реализацию (т. е. хранилище).
//    Реализация указывается путем добавления параметра с типом реализации интерфейса (например, SharedPreferencesStorage)
//    В приведенном ниже коде мы сказали Dagger: «Когда вам нужен объект Storage, используйте SharedPreferencesStorage».


    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}