package com.example.android.dagger.registration

import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.registration.enterdetails.EnterDetailsFragment
import com.example.android.dagger.registration.termsandconditions.TermsAndConditionsFragment
import dagger.Subcomponent
//Подкомпоненты — это компоненты, которые наследуют и расширяют граф объектов родительского компонента.
// Таким образом, все объекты, предоставленные в родительском компоненте, будут также предоставлены в подкомпоненте.
// Таким образом, объект из подкомпонента может зависеть от объекта, предоставленного родительским компонентом.


// Definition of a Dagger subcomponent
// Classes annotated with @ActivityScope will have a unique instance in this Component
// Классы, аннотированные @ActivityScope, будут иметь уникальный экземпляр в этом компоненте.
@ActivityScope
@Subcomponent
interface RegistrationComponent {

    // Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

//    В AppComponent мы должны удалить методы, которые могут внедрять классы представления регистрации,
//    потому что они больше не будут использоваться, эти классы будут использовать RegistrationComponent.
//    Вместо этого, чтобы RegistrationActivity создавала экземпляры RegistrationComponent,
//    нам нужно выставить его Factory в интерфейсе AppComponent.

    // Classes that can be injected by this Component
    fun inject(activity: RegistrationActivity)
    fun inject(fragment: EnterDetailsFragment)
    fun inject(fragment: TermsAndConditionsFragment)


}