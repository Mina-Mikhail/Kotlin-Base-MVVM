Android Clean Architecture 
=========================


Clean Architecture:
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
- Using Kotlin-KTS & buildSrc to handle project dependencies.


Tech Stack & Libraries:
-----------------
- Navigation component - navigation graph for navigating and replacing screens/fragments
- DataBinding - allows to more easily write code that interacts with views and replaces ```findViewById```.
- ViewModel - UI related data holder, lifecycle aware.
- LiveData - Build data objects that notify views when the underlying database changes.
- Dagger-Hilt for dependency injection. Object creation and scoping is handled by Hilt.
- Kotlin Coroutines - for managing background threads with simplified code and reducing needs for callbacks
- Retrofit2 & OkHttp3 - to make REST requests to the web service integrated.
- Coil - for image loading.
- Material Bottom Navigation - to handle bottom tabs with support for multiple backStack.


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


Code Style
-----------
- Following official kotlin code style


Apply Git Hooks
-----------
- To apply git hooks in order to automate process of styling and checking your code, just follow this steps:
  - Copy ```pre-commit``` file from ```myGitHooks```.
  - Paste it into ```.git/hooks``` in your project.
- Now each time you commit your changes, ```ktlintFormat``` and  ```ktlintCheck``` will automatically run


TO DO
-----------
- [X] Apply ktlint for checking code style.
- [X] Use git hooks to automate code checking and styling before any new commit.
- [ ] Use Flow instead of LiveData.
- [ ] Use UseCases.
- [X] Handle Different Build Variants.
- [ ] Explore full MVI implementation.
- [ ] Add some unit tests.



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
