package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.login.LoginComponent
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.registration.enterdetails.EnterDetailsFragment
import com.example.android.dagger.registration.termsandconditions.TermsAndConditionsFragment
import com.example.android.dagger.settings.SettingsActivity
import com.example.android.dagger.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

//Мы хотим, чтобы Dagger создавал граф зависимостей нашего проекта, управлял ими за нас и мог получать зависимости из графа.
//Чтобы заставить Dagger сделать это, нам нужно создать интерфейс и аннотировать его с помощью @Component.
// Dagger создаст контейнер, как мы сделали бы с ручным внедрением зависимостей.
// Интерфейс, аннотированный @Component, заставит Dagger генерировать код со всеми зависимостями,
// необходимыми для удовлетворения параметров предоставляемых им методов.
// Внутри этого интерфейса мы можем сообщить Dagger, что RegistrationActivity запрашивает инъекцию.

//С помощью метода inject(activity: RegistrationActivity) в интерфейсе @Component мы сообщаем Dagger,
// что RegistrationActivity запрашивает внедрение и что он должен предоставить зависимости,
// которые аннотированы с помощью @Inject (т.е. RegistrationViewModel, как мы определили на предыдущем шаге).

//Интерфейс @Component предоставляет информацию, необходимую Dagger для создания графа во время компиляции.
// Параметр методов интерфейса определяет, какие классы запрашивают инъекцию.


//Граф приложения должен знать о StorageModule.
// Для этого мы включаем его в AppComponent с параметром modules внутри аннотации @Component

//Как мы можем указать Dagger, как предоставлять контекст?
// Контекст предоставляется системой Android и поэтому строится вне графика.
// Поскольку Context уже доступен в то время, когда мы будем создавать экземпляр графа, мы можем передать его.
// Его можно передать с помощью фабрики компонентов и использования аннотации @BindsInstance.
//Мы объявляем интерфейс с аннотацией @Component.Factory.
// Внутри есть метод, который возвращает тип компонента (т. е. AppComponent) и имеет параметр типа
// Context с аннотацией @BindsInstance.
// @BindsInstance сообщает Dagger, что ему нужно добавить этот экземпляр в граф,
// и всякий раз, когда требуется контекст, предоставляйте этот экземпляр.
//Используйте Scopes @Singleton, чтобы иметь уникальный экземпляр типа в компоненте.
// Это то, что также называется "привязать тип к жизненному циклу Компонента".
// Привязка типа к компоненту означает, что один и тот же экземпляр этого типа будет использоваться каждый раз,
// когда необходимо предоставить тип.

// Definition of a Dagger component that adds info from the StorageModule to the graph

@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    //  Чтобы RegistrationActivity создавала экземпляры RegistrationComponent(субкомпонента),
    //  нам нужно выставить его Factory в интерфейсе AppComponent
    //  Expose RegistrationComponent factory from the graph

    // Types that can be retrieved from the graph
    fun registrationComponent(): RegistrationComponent.Factory
    fun loginComponent(): LoginComponent.Factory
    fun userManager(): UserManager

//    // Classes that can be injected by this Component
//    fun inject(activity: MainActivity)
//    fun inject(activity: SettingsActivity)


//    В AppComponent мы должны удалить методы, которые могут внедрять классы представления регистрации,
//    потому что они больше не будут использоваться, эти классы будут использовать RegistrationComponent.

//    fun inject(activity: RegistrationActivity)
//    fun inject(fragment: EnterDetailsFragment)
//    fun inject(fragment: TermsAndConditionsFragment)
}