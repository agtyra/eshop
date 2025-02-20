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