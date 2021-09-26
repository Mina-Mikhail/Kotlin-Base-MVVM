Android Clean Architecture 
=========================


Clean architecture:
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture.png)

Architectural Approach:
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers.png)

Architectural reactive approach
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers_details.png)


Architecture:
-----------------
- Following Clean Architecture.
- MVVM Architecture.
- Repository pattern.
- Applying SOLID principles, each class has a single job with separation of concerns by making classes independent
  of each other and communicating with interfaces.


Tech stack & Libraries:
-----------------
- Navigation component - navigation graph for navigating and replacing screens/fragments
- ViewBinding - allows to more easily write code that interacts with views and replaces ```findViewById```.
- ViewModel - UI related data holder, lifecycle aware.
- LiveData - Build data objects that notify views when the underlying database changes.
- Dagger-Hilt for dependency injection. Object creation and scoping is handled by Hilt.
- Kotlin Coroutines - for managing background threads with simplified code and reducing needs for callbacks
- Retrofit2 & OkHttp3 - to make REST requests to the web service integrated.
- Coil - for image loading.
- Material Bottom Navigation - to handle bottom tabs with support for multiple backstacks.


Project Structure:
-----------------
- Sample includes some basic features required in each project like :
  - Splash.
  - App Tutorial.
  - Login - (With Business Logic).
  - Sign Up - (Blank Screens).
  - Forgot Password -(Blanck Screens).
  - Home Screen - (Contains 3 Tabs with 3 NavGraphs).


Extra Modules:
-----------------
- You will find extra modules also developed by me like :
  - AppTutorial - (To handle onBoarding tutorial screens).
  - ActionChooser - (A customized pop up with recyclerView of single selection).
  - PrettyPopUp (A customized pop up to display message to user with two actions (positive & negative buttons)).
  - ImagesSlider (An images slider supports auto scrolling for images from url and support GIF images).


Code style
-----------
- Following official kotlin code style


License
--------

    Copyright 2021 Fernando Cejas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
