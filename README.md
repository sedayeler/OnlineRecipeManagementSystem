# Online Recipe Management System
#1.Factory Method Pattern
In the project, we adopted the Factory Design Pattern to increase the flexibility and scalability of our codebase. This pattern enabled us to easily group the unique cooking styles of different regions. By encapsulating the recipe creation logic into a central factory class named RecipeFactory, we simplified the addition and management of region-specific dishes to our system. Through the Factory pattern, we separated the process of recipe creation from recipe usage, thus enhancing code reusability and maintenance.
Furthermore, the Factory Design Pattern facilitated the addition of new regional dishes. By simply modifying the factory class, we could integrate new recipes into the project without the need to change multiple sections of our codebase.

