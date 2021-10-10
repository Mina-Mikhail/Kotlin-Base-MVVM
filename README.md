<h1 align="center">
Android Clean Architecture 
</h1>


<div align="center">
<a name="open_source">
  <img src="https://badges.frapsoft.com/os/v1/open-source.svg?v=102?style=for-the-badge">
</a>
<a name="code_factor" href="https://www.codefactor.io/repository/github/mina-mikhail/kotlin-base-mvvm">
  <img src="https://www.codefactor.io/repository/github/mina-mikhail/kotlin-base-mvvm/badge?style=for-the-badge">
</a>  
<a name="platform">
  <img src="https://img.shields.io/badge/platform-Android-blue.svg?style=for-the-badge">
</a>
<a name="stars">
  <img src="https://img.shields.io/github/stars/Mina-Mikhail/Kotlin-Base-MVVM?style=for-the-badge"></a>
<a name="forks">
  <img src="https://img.shields.io/github/forks/Mina-Mikhail/Kotlin-Base-MVVM?logoColor=green&style=for-the-badge">
</a>
<a name="contributions">
  <img src="https://img.shields.io/github/contributors/Mina-Mikhail/Kotlin-Base-MVVM?logoColor=green&style=for-the-badge">
</a>
<a name="last_commit">
  <img src="https://img.shields.io/github/last-commit/Mina-Mikhail/Kotlin-Base-MVVM?style=for-the-badge">
</a>
<a name="issues">
  <img src="https://img.shields.io/github/issues-raw/Mina-Mikhail/Kotlin-Base-MVVM?style=for-the-badge">
</a>
<a name="license">
  <img src="https://img.shields.io/github/license/sadanandpai/javascript-code-challenges?style=for-the-badge">
</a>
<a name="linked_in" href="https://www.linkedin.com/in/minasamirgerges/">
  <img src="https://img.shields.io/badge/Support-Recommed%2FEndorse%20me%20on%20Linkedin-yellow?style=for-the-badge&logo=linkedin" alt="Recommend me on LinkedIn"/>
</a>
</div>


:point_right: Clean Architecture:
-----------------
<div align="center">
<img src="https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/blob/master/images/architecture.png">
</div>


:point_right: Domain & Data Layer:
-----------------
<div align="center">
<img src="https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/blob/master/images/data_layer.png">
</div>


:point_right: Presentation Layer:
-----------------
<div align="center">
<img src="https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/blob/master/images/ui_layer.png">
</div>


:point_right: Architecture:
-----------------
- Following Clean Architecture.
- MVVM Architecture.
- Repository pattern.
- Use Cases.
- Applying SOLID principles, each class has a single job with separation of concerns by making classes independent
  of each other and communicating with interfaces.
- Using Kotlin-KTS & buildSrc to handle project dependencies.


:point_right: Tech Stack & Libraries:
-----------------
- Navigation component - navigation graph for navigating and replacing screens/fragments
- DataBinding - allows to more easily write code that interacts with views and replaces ```findViewById```.
- ViewModel - UI related data holder, lifecycle aware.
- Flow & StateFlow - Build data objects that notify views when the underlying database changes.
- Dagger-Hilt for dependency injection. Object creation and scoping is handled by Hilt.
- Kotlin Coroutines - for managing background threads with simplified code and reducing needs for callbacks
- Retrofit2 & OkHttp3 - to make REST requests to the web service integrated.
- Coil - for image loading.
- Material Bottom Navigation - to handle bottom tabs with support for multiple backStack.


:point_right: Project Structure:
-----------------
- Sample includes some basic features required in each project like :
  - Splash.
  - App Tutorial.
  - Login - (With Business Logic).
  - Sign Up - (Blank Screens).
  - Forgot Password -(Blanck Screens).
  - Home Screen - (Contains 3 Tabs with 3 NavGraphs).


:point_right: Extra Modules:
-----------------
- You will find extra modules also developed by me like :
  - AppTutorial - (To handle onBoarding tutorial screens).
  - ActionChooser - (A customized pop up with recyclerView of single selection).
  - PrettyPopUp (A customized pop up to display message to user with two actions (positive & negative buttons)).
  - ImagesSlider (An images slider supports auto scrolling for images from url and support GIF images).


:point_right: Code Style:
-----------
- Following official kotlin code style


:point_right: Apply Git Hooks:
-----------
- To apply git hooks in order to automate process of styling and checking your code, just follow this steps:
  - Copy ```pre-commit``` file depending on your OS from ```myGitHooks```.
  - Paste it into ```.git/hooks``` in your project.
- Now each time you commit your changes, ```ktlintFormat``` and  ```ktlintCheck``` will automatically run


:point_right: Local Development:
-----------
- Here are some useful Gradle commands for executing this example:
  - `./gradlew clean` - Deletes build directory.


:point_right: TO DO:
-----------
- [X] Apply ktlint for checking code style.
- [X] Use git hooks to automate code checking and styling before any new commit.
- [X] Use Flow in Domain layer.
- [X] Use StateFlow in Presentation layer.
- [X] Use UseCases.
- [X] Handle Different Build Variants.
- [ ] Use Ktor as network client instead of Retrofit & OKHTTP.
- [ ] Use Data Store instead of Shared Preferences.
- [ ] Explore full MVI implementation.
- [ ] Add some unit tests.


:point_right: Contributing to Project:
-----------
- Just fork this repository and contribute back using pull requests.
- Any contributions, large or small, major features, bug fixes, are welcomed and appreciated but will be thoroughly reviewed .


:point_right: Find this project useful ? :heart:
-----------
- Support it by clicking the :star: button on the upper right of this page. :v:


:point_right: Stargazers: :star:
-----------
[![Stargazers repo roster for @sadanandpai/javascript-code-challenges](https://reporoster.com/stars/Mina-Mikhail/Kotlin-Base-MVVM)](https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/stargazers)


:point_right: Forkers: :hammer_and_pick:
-----------
[![Forkers repo roster for @sadanandpai/javascript-code-challenges](https://reporoster.com/forks/Mina-Mikhail/Kotlin-Base-MVVM)](https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/network/members)


:point_right: Donation:
-----------
If this project help you reduce time to develop, you can give me a cup of coffee :) 

<a href="https://www.buymeacoffee.com/mina.mikhail" target="_blank"><img src="https://bmc-cdn.nyc3.digitaloceanspaces.com/BMC-button-images/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>



:warning: License:
--------
```
   Copyright (C) 2021 MINA MIKHAIL PRIVATE LIMITED

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
