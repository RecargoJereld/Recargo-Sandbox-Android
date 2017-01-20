# Android Sandbox App

### Goals
A foundation project that demonstrates best practices [(SOLID)] (https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)) to create an app that is easy to extend, maintain, and test.

![architecture](http://i.imgur.com/yFjfCuW.png)

![flow](http://i.imgur.com/zyM52Cb.png)

### Summary
Each screen is composed of a view (defined by an Activity and Fragment) and a Presenter object. The screen's contract defines the interface for the view and presenter.

In the data layer, the repository object provides an interface to the data source. Requests are sent to the backend service and the responses are cached in a local database and can be used for offline access.

The Dagger 2 framework is used to implement dependency injection to make it easy to create reusable, interchangeable modules. This helps us with unit testing or using mock objects for feature development. The network stack is provided as a singleton tied to the Application lifecycle and is used across the whole app giving us an entry point to the data layer. We could also define a singleton scope to share dependencies between fragments (e.g. shared preferences).

### Extensibility and Maintainability
New features should be easy to add due to the modularity of the project and should look very similar to existing ones. Dependencies can be added or changed by modifying the module that defines the dependency.

### Testing
The data layer is isolated with the repository and can be unit tested easily. Presenters can be unit tested by mocking views and models with Mockito. Views are tested with instrumentation tests through Espresso.

Build flavors are used to swap out classes for testing. The "mock" build variant injects FakePlugShareDataSource to provide fake data for automated testing.
