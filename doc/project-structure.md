## Project structure

I separate the top level into 4 package

```
├── features
├── foundation
├── model
└── runtime
```

### Model

In this project there are three kind of model, UI model, domain model, data source model. These three models have different purpose.

#### Domain model

This is generic model that can be reused in all place. **Use this model to build a logic where feasible**. For example a generic ToDoList model:

```kotlin
data class ToDoList(
    val id: String,
    val name: String,
    val color: ToDoColor,
    val tasks: List<ToDoTask>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
```

#### Data source model

Each data source has it is own model, eg model from sqlite db, model from data store, model from server. So that whenever any changes not impact each other. For example a model to store ToDoList data
in local storage using Room database:

```kotlin
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ToDoGroupDb::class,
            parentColumns = ["group_id"],
            childColumns = ["list_groupId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("list_groupId"),
        Index("list_name", unique = true)
    ]
)
data class ToDoListDb(
    @PrimaryKey
    @ColumnInfo(name = "list_id")
    val id: String,
    @ColumnInfo(name = "list_name")
    val name: String,
    @ColumnInfo(name = "list_color")
    val color: ToDoColor,
    @ColumnInfo(name = "list_groupId")
    val groupId: String,
    @ColumnInfo(name = "list_createdAt")
    val createdAt: LocalDateTime,
    @ColumnInfo(name = "list_updatedAt")
    val updatedAt: LocalDateTime,
)
```

#### UI model

Used as UI state source of truth. It also to simplify the rendering operation. So that the complex part is not direct in the UI implementation. This model can be useful to understand how the UI looks
like without running the app. For example a model that used in ToDo list detail screen:

```kotlin
@Immutable
data class ListDetailState(
    val list: ToDoList, // List of task, ToDoList domain model
    val newListName: String, // Configurable title of to do page
    val colors: List<ColorItem>, // Configurable selected color from list of available color
    val taskName: TextFieldValue // New introduce task for a to do
)
```

<img src="../art/ui-model-preview-1.jpg" width="260"> <img src="../art/ui-model-preview-2.jpg" width="260">

### Runtime

The top level of application. Used to initiate dependency. Used to register feature to make it accessible through navigation.

```
└── runtime
    └── navigation
```

### Feature

This is a place for our features. A feature is set of flow that created based on user requirements. A Feature Consist of 2 layers, UI and data. Data flow must be unidirectional, `UI` should render
after `Model` changes due to a `Input`.

```
└── features
   ├── dashboard
   ├── host
   ├── localized
   ├── login
   ├── logout
   ├── setting
   ├── splash
   ├── theme
   └── todo
       ├── all
       ├── detail
       ├── group
       ├── grouplist
       ├── groupmenu
       ├── main
       ├── scheduled
       ├── search
       ├── step
       └── taskreminder
```

### Foundation

Reusable component goes here.

- Reusable logic (pure functionality)
- Reusable UI component
- Consist of data source with no specific feature logic, only providing get and set operation

```
└── foundation
   ├── datasource
   ├── di
   ├── extension
   ├── localization
   ├── preview
   ├── theme
   ├── uicomponent
   ├── uiextension
   ├── viewmodel
   ├── window
   └── wrapper
```
