**Documentation**

**Structure of application**

**Libraries:**
- Dagger2: Dependency Injection
- Retrofit/Okhttp: Networking
- Moshi: JSON serialization/deserialization 
- Glide: Image Loading
- Coroutines: Asynchronous programming 

**Architecture:**
The project is structured using the MVVM pattern.

**Classes:**

**Application:**
StackExchangeApplication: Used to start initialise Dagger for dependency injection.

**Data:**
Api: ApiService: Declares the endpoints for the application.

**Model:**

- UsersResponse: Holds the response of the Users.
- TagsResponse: Holds the response of the Tags.
- Tag: Hold the Tag data.
- Item: Holds the User data.
- BadgeCounts: Holds the Badge Count data.

**Repository:**
StackExchangeRepository: Handles data operations, such as getting the data from the server.

**DI:**
- Component: AppComponent: Provides the modules for dependency injection.

**Module:**

- AppModule: Provides the Context dependency.
- NetworkModule: Provides the Retrofit, ApiService, OkHttpClient and NetworkHelper dependencies
- RepositoryModule: Provides the StackExchangeRepository dependency.
- ViewModelModule: Provides the SearchViewModel and UserDetailViewModel dependencies.

**UI:**
Adapter: 
- TagsAdapter: Handles the Tags data.
- UsersAdapter: Handles the Users data

**View:**

- Activity: MainActivity: Acts as container for the SearchFragment and UserDetailFragment.

**Fragment:**

- SearchFragment: Displays the list of users based on search query.
- UserDetailFragment: Displays the user details.

**ViewModel:**

- SearchViewModel:  Holds and manages the Users data.
- UserDetailViewModel: Holds and manages the Tags data.

**Utils:**

- Const: Holds base url other constant variables.
- DateUtils: Declares a method to convert the creation date value to a formatted date.
- NetworkHelper: Declares a method to check if device is connected to the internet.
- Resource: Contains data and status about loading data.
- Sort: Declares sorting types for endpoints.
- Status: Declares data status.	

