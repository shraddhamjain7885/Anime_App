# Anime App
Anime App compose code

/*
README.md Overview:
-This app contains two screens : One for displaying a list of Anime which fetched from a public API and 
 other for showing the details of selected Anime item.
- This is a modular MVVM clean architecture app that implements a structured and scalable approach using multiple
  modules, ensuring maintainability, testability, and separation of concerns.
- Folder Structure
- App Module
- di: AppModule provides global dependencies. 
- navigation: Manages screen navigation (AppNavigation). 
- ui.screen: Screens for the app:
- MainActivity. App entry point. 
- ItemListScreen: Displays news list. 
- Item DetailScreen: Displays selected news details. 
- viewmodel: MainViewModel holds business logic. 
- MyApplication: Initializes the app. Core Module 
- di: CoreModule provides core dependencies. 
- model: Defines shared models (Result). 
- util: Common utilities (Constants,Resource). 
- api: ApiService for API calls, Base Response for API responses. 
- mapper: Maps ItemDto to domain models.
- repository: Anime Repository fetches
- model: Defines domain model Item. 
- repository: ItemRepository for fetching data
- App uses Modularization, Hilt, Coroutines, Flow, Unit Tests, Error Handling
- Modules: app, core, common, data, domain
- Made Use of public API to display list and detail page
- Screenshots: [Attach screenshots of Splash screen, AnimeList and AnimeDetail screens here]
*/

![Anime_list_screen.png](Anime_list_screen.png)
![Anime_detail_screen.png](Anime_detail_screen.png)