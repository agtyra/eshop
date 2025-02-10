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