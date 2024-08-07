# GitHub Copilot, AI Assistant and Other Aiding Tools: Hands-On Java Springboot + JUnit + IntelliJ

In this live hands-on, developers will use AI Assistant and GitHub Copilot to explore their benefits and how these tools can boost their workflow. Specifically, we will focus on applying best practices like:
- Generation of JUnit 5 tests
- Refactoring our code from MVC to Hexagonal Architecture
- Removing the "Anemic Domain" anti-pattern
- Implementing Value objects

## Requirements

- IntelliJ IDEA
- Java SDK version 17
- Docker
- Maven 3.9.x
- Git

## Step 1: Integration Tests with TestContainers

Now, let's create an integration test to ensure that everything is working perfectly. For this, we will use TestContainers to run our services in isolation, and carry out all possible use cases on them.

Here's an example of a prompt for AI Assistant:

"You are a seasoned Java developer wanting to create integration tests for the `OrderService` class. The use of TestContainers will facilitate simulation of the full application features."

## Step 2: Moving from MVC to Hexagonal Architecture

In this step, we will refactor our application to follow Hexagonal Architecture. We will be using the Ports and Adapters design pattern to achieve this. This will help make our application more maintainable, scalable, and easy to understand.

Here's an example of a prompt for AI Assistant:

"As an experienced software architect, your task is to transition a web application from Model-View-Controller (MVC) architecture to Hexagonal Architecture. You plan on achieving this transformation by implementing Ports and Adapters techniques. The application is built on Java, utilizing Spring MVC and Spring Data MongoDB for data persistence."

## Step 3: Removing the "Anemic Domain" Anti-Pattern and Implementing Value Objects

The next step involves addressing the Anemic Domain anti-pattern in our application. We will transform our domain model to better encapsulate logic and data, and also introduce Value Objects.

Here's an example of a prompt for AI Assistant:

"You are a proactive developer tasked with enhancing an existing Java-based web application by removing the Anemic Domain anti-pattern and replacing it with proper Domain-Driven Design principles. As part of this process, you are to redesign the existing entities as Value Objects."

## Step 4: Unit Testing of Domain Classes

Finally, we will add unit testing for our domain classes to make sure they are correctly performing their responsibilities. These tests will verify both the expected outputs and the expected side effects of the methods.

Here's an example of a prompt for AI Assistant:

"As a dedicated Java developer, you understand the importance of unit testing. Your goal is to create an extensive suite of unit tests for the domain classes in your Springboot web application, with specific focus on ensuring each class behaves as expected."

With these steps, you will be well on your way to a more robust, scalable, and maintainable Java application. Happy coding!