# Kezia Salsalina Agtyra Sebayang - 2306172086

<Details>
<Summary><b>Module 1</b></Summary>

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

</Details>

<Details>
<Summary><b>Module 2</b></Summary>

### Reflection

The issues that I fixed:
- Removing Public Modifiers – I eliminated unnecessary public modifiers where they were redundant, following modern coding conventions.
- Grouping Dependencies – I structured and grouped dependencies more logically, improving readability and maintainability in my project configuration files.
- Removing Unnecessary Exception Declarations – I identified and removed redundant throws declarations in method signatures to simplify exception handling.
- Cleaning Up Unused Imports – I removed unused imports to declutter the codebase and improve readability.
- Removed Field Injection – I initially removed field injection but had to make additional changes to fully eliminate it from the project. This was necessary to align with best practices and improve code maintainability.
- Added Assertions to Test Cases – Some of my test cases were incomplete, so I added at least one assertion to ensure proper validation of expected behavior.
- Implemented Logic in Empty Methods – There were methods in the code that had no implementation, which could lead to unintended behavior. I addressed this by adding the necessary functionality.

Looking at my current CI/CD workflows, I think they mostly meet the definition of Continuous Integration and Continuous Deployment. The ci.yml workflow ensures that every push and pull request triggers automated tests, which aligns with CI by making sure new changes don’t break the code. The deploy.yml workflow also supports CD because it automatically redeploys the application to Koyeb whenever changes are pushed to the main branch, removing the need for manual deployment. Additionally, the scorecard.yml and sonarcloud.yml workflows contribute to maintaining security and code quality, which reinforces CI by catching potential issues early.

</Details>

<Details>
<Summary><b>Module 3</b></Summary>

## Reflection

### 1. Principles Applied to the Project

The Single Responsibility Principle (SRP) was the first principle I implemented. Initially, ProductController handled both request processing and business logic, for Product and Car. To address this, I moved CarController to a separate file. 

Next, I applied the Open/Closed Principle (OCP) by introducing the ICarRepository and IProductRepository interfaces.

The Liskov Substitution Principle (LSP) was inherently followed in the project, even though I did not explicitly modify the code to apply it. Since CarServiceImpl correctly implements CarService, and ProductServiceImpl implements ProductService, any subclass or alternative implementation can replace them without breaking the system.

The Interface Segregation Principle (ISP) was also applied by splitting the repository interface into two smaller, more focused interfaces: ReadOnlyRepository for retrieval operations and WriteRepository for data modification.

Finally, I ensured compliance with the Dependency Inversion Principle (DIP) by modifying CarController to depend on the CarService interface instead of CarServiceImpl. 

### 2. Advantages of Applying SOLID Principles

Applying the SOLID principles made the project easier to maintain, test, and scale. By following SRP, CarController now focuses only on handling requests, making the code cleaner and more manageable. 

The use of OCP ensures that repository implementations can be extended without modifying existing code, allowing for future enhancements without breaking functionality.

The LSP-compliant structure ensures that alternative implementations of CarService or ProductService can be used interchangeably, supporting better code reusability and testing

With ISP, repositories now only implement the methods they actually need, preventing unnecessary dependencies and making the code more modular. 

Additionally, DIP allows CarController to work with any CarService implementation, making the system flexible and test-friendly.

### 3. Disadvantages of Not Applying SOLID Principles

If SOLID principles had not been applied, the project would have been harder to maintain and scale. Without SRP, ProductController would still handle multiple responsibilities, making modifications error-prone and confusing. 

Ignoring OCP would mean every new repository implementation requires modifying existing repository classes, increasing the risk of breaking functionality.

If LSP were violated, replacing CarServiceImpl with another implementation could lead to unexpected failures, reducing code flexibility.

Without ISP, repository interfaces would be filled with unnecessary methods, forcing classes to implement operations they do not need. 

Lastly, neglecting DIP would result in CarController being tightly coupled to CarServiceImpl, making it difficult to replace the service for testing or future upgrades.

</Details>
