# FlightBargain

### App purpose
This app allows user to get 5 unique flight journey offers each day. It connects with https://api.skypicker.com/.

### Project structure

There are 3 modules:
- `model` contains all entities and interface for repository,
- `core` implements repository with REST client (retrofit),
- `app` UI and viewmodels of the app (currently only one screen with list of offers)

### Architecture
Project is done with MVI architecture. Each screen is defined by:
- `ViewEvent` sealed class containing all actions (implicit and explicit) that can be done (ViewModel's argument,input)
- `ViewState` it's data class that defines current UI state, it's repeated on configuration changes
like rotations (ViewModel's result, output)

Contact to developer: wiktorwardzichowski@gmail.com

