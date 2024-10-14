# Test Utils

This module has been created to facilitate testing of view models.

In order to test a view model, the module must include the following dependency:
```
testImplementation(project(":testutils"))
```

Then you need to add the following just above the `class` definition in the View Model test:
```
@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
```

This will make sure that all Coroutines are run synchronously, meaning that you can write and verify functionality with tests. 