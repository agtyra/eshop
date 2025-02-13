# Kezia Salsalina Agtyra Sebayang - 2306172086

## Module 1

### Reflection 1
One of the main principles I followed in this project was the Single Responsibility Principle (SRP), 
ensuring that each class has a distinct and clear purpose. 
I also made sure that variable and method names were descriptive and meaningful. 
For example, method names like findProductById() and createProductPost() clearly indicate their functionality, 
making the code easier to read and understand.
To avoid hardcoded values, 
I used UUID.randomUUID().toString() to generate unique product IDs dynamically instead of assigning them manually. 
For security, I added a confirmation prompt before deleting a product to prevent accidental actions. 
Additionally, I handled null values in findProductById() to prevent crashes. 
However, I later realized that a better approach would be to throw a custom exception like ProductNotFoundException 
to provide a clearer error message.

### Reflection 2

After writing the unit test, I realized how important unit test is for maintaining a reliable and bug-free application. Unit tests help ensure that individual methods function correctly and prevent regressions when making changes to the codebase.
The number of unit tests needed depends on the complexity of the class. To verify that our unit tests are sufficient, we can use code coverage tools to measure how much of our code is tested. However, having 100% code coverage does not mean the code is bug-free, as it only measures whether the code was executed, not whether it behaves correctly in all scenarios.

When writing another functional test for creating product, I noticed that the structure was almost identical to the previous test. This leads to code duplication, which reduces maintainability. The setup procedures, instance variables, and WebDriver initialization were repeated across different test classes. This means that if any configuration changes, multiple files would need to be updated, increasing the risk of inconsistencies. Instead of repeating the same setup logic, I think a better approach would be to extract common functionalities into a base class that all functional tests can inherit from. This would keep the test suite modular and easier to maintain.
