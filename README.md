# Online Recipe Management System
# 1.Factory Method Pattern
In the project, we adopted the Factory Design Pattern to increase the flexibility and scalability of our codebase. This pattern enabled us to easily group the unique cooking styles of different regions. By encapsulating the recipe creation logic into a central factory class named RecipeFactory, we simplified the addition and management of region-specific dishes to our system. Through the Factory pattern, we separated the process of recipe creation from recipe usage, thus enhancing code reusability and maintenance.
Furthermore, the Factory Design Pattern facilitated the addition of new regional dishes. By simply modifying the factory class, we could integrate new recipes into the project without the need to change multiple sections of our codebase.

# 2. Strategy Pattern
We used the Strategy design pattern for the rating model in our project. This pattern encapsulates different rating strategies in separate classes, making them changeable at runtime. In recipe management, this pattern is used to calculate the impact of recipes based on user ratings. Two different strategies are implemented: Average Rating and Total Rating. The Recipe class holds a reference to the current rating strategy, allowing for dynamic switching between strategies. This design enhances the flexibility and extensibility of the code.

# 3. Singleton Pattern
In our project, we used the Singleton design pattern in the RecipeRepository class. This pattern ensures that only one instance of a class is created and provides global access to that instance.
In the RecipeRepository class, access to only one instance is achieved using the getInstance() method. This method checks for the existence of the current instance. If an instance does not exist, a new one is created and returned. If an instance already exists, it is directly returned.
This approach ensures that there is only one RecipeRepository instance throughout the system. This preserves data integrity and prevents unnecessary instances from being created. As a result, efficient and consistent recipe management is ensured.
