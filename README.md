# android-template
An Android example using a bunch of the new libraries available at Google I/O 2018. This includes:
  - Android Databinding Library
  - Navigation Library
  - Paging Library
  - ViewModel / LifeCycles / LiveData
  - Room persistance

It also includes the following:
  - Dagger Support
  - Kotlin
  - Retrofit Networking (with coroutine support)
  - Moshi JSON parsing

The architecture is attempting to adhere to Google's MVVM architecture guidelines.
The base ideas are:
  - Single Activity App
  - Fragments only observe ViewModels and update Views using that data.
  - ViewModels handle observing Repository changes, and implement most of the logic
  - Repositories handle fetching data from the API and writing that data to the Database
  - The Database is the single source of truth for read-only data
