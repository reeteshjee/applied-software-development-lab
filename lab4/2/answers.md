# Spring Framework Q&A

## What is Spring?

Spring is a comprehensive application framework for Java that provides infrastructure support for developing enterprise applications. It offers features like dependency injection, data access, transaction management, web applications, and more.

**Example:**
```java
// Spring manages object creation and wiring
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User findUser(Long id) {
        return userRepository.findById(id);
    }
}
```

---

## What is Spring Boot?

Spring Boot is an opinionated framework built on top of Spring that simplifies application setup and development. It provides auto-configuration, embedded servers, and production-ready features out of the box.

**Example:**
```java
// A complete Spring Boot application in one class
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

---

## What is the relation between Spring platform and Spring Boot?

Spring Boot is part of the Spring platform. The Spring platform is the umbrella term for all Spring projects (Spring Framework, Spring Boot, Spring Data, Spring Security, etc.). Spring Boot uses the Spring Framework core but adds conventions and automation to reduce boilerplate configuration.

**Visualization:**
```
Spring Platform
├── Spring Framework (core)
├── Spring Boot (simplified setup)
├── Spring Data (database access)
├── Spring Security (authentication)
└── Spring Cloud (microservices)
```

---

## What is the relation between Spring platform and Spring framework?

Spring Framework is the foundational core of the entire Spring platform. All other Spring projects (Spring Boot, Spring Data, Spring Security, etc.) build upon the Spring Framework's core features like dependency injection and IoC container.

**Example:**
```java
// Spring Framework provides the @Component annotation
@Component
public class EmailService { }

// Spring Boot auto-configures based on Framework features
@SpringBootApplication // Uses Framework underneath
public class App { }
```

---

## What is Dependency Injection and how is it done in the Spring platform/framework?

Dependency Injection (DI) is a design pattern where objects receive their dependencies from an external source rather than creating them. Spring provides DI through its IoC container, which creates and injects dependencies automatically.

**Example:**
```java
// Without DI - tight coupling
public class OrderService {
    private EmailService emailService = new EmailService(); // Creates dependency
}

// With Spring DI - loose coupling
@Service
public class OrderService {
    private final EmailService emailService;
    
    @Autowired // Constructor injection (recommended)
    public OrderService(EmailService emailService) {
        this.emailService = emailService; // Injected by Spring
    }
}

@Service
public class EmailService {
    public void sendEmail(String message) { }
}
```

**DI Methods in Spring:**
- Constructor injection (preferred)
- Setter injection
- Field injection

---

## What is Inversion of Control (IoC) and how is it related to Spring?

Inversion of Control is a principle where the control of object creation and lifecycle is inverted from the application code to a framework. Instead of your code creating objects, the framework creates and manages them. Spring's IoC container (ApplicationContext) implements this principle through DI.

**Example:**
```java
// Traditional control flow - you control object creation
public class Main {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        OrderService orderService = new OrderService(emailService);
    }
}

// IoC with Spring - Spring controls object creation
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = 
            SpringApplication.run(Main.class, args);
        
        // Spring already created and wired OrderService
        OrderService orderService = context.getBean(OrderService.class);
    }
}

@Service
public class OrderService {
    @Autowired
    private EmailService emailService; // Spring creates and injects
}
```

**Key Point:** Spring's IoC container manages the complete lifecycle of beans (creation, configuration, dependency injection, destruction).

---

## Summary

- **Spring** = Enterprise Java framework
- **Spring Boot** = Spring + auto-configuration + embedded server
- **Platform vs Framework** = Platform is the ecosystem, Framework is the core
- **DI** = Objects receive dependencies instead of creating them
- **IoC** = Framework controls object lifecycle (Spring does this via its container)