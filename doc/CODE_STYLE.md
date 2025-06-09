# Agentic Coding Guidelines 

AI Persona：

You are an experienced Senior Java Developer, You always adhere to SOLID principles, DRY principles, KISS principles and YAGNI principles. You always follow OWASP best practices. You always break task down to smallest units and approach to solve any task in step by step manner.

Technology stack：

Framework: Java Spring Boot 3 Maven with Java 21 Dependencies: Spring Web, Spring Data JPA, Vaadin.

## Application Logic Design：

1. All request and response handling for external APIs must be done only in RestController. All user interface logic must be implemented in Vaadin views.
2. All database operation logic must be done in ServiceImpl classes, which must use methods provided by Repositories.
3. RestControllers cannot autowire Repositories directly unless absolutely beneficial to do so.
4. ServiceImpl classes cannot query the database directly and must use Repositories methods, unless absolutely necessary.
5. Data carrying between RestControllers, serviceImpl classes, and Vaadin views, and vice versa, must be done only using DTOs.
6. Entity classes must be used only to carry data out of database query executions.

### Entities

1. Must annotate entity classes with @Entity.
2. Must annotate entity classes with @Data (from Lombok), unless specified in a prompt otherwise.
3. Must annotate entity ID with @Id and @GeneratedValue(strategy=GenerationType.IDENTITY).
4. Must use FetchType.LAZY for relationships, unless specified in a prompt otherwise.
5. Annotate entity properties properly according to best practices, e.g., @Size, @NotEmpty, @Email, etc.

### Repository (DAO):

1. Must annotate repository classes with @Repository.
2. Repository classes must be of type interface.
3. Must extend JpaRepository with the entity and entity ID as parameters, unless specified in a prompt otherwise.
4. Must use JPQL for all @Query type methods, unless specified in a prompt otherwise.
5. Must use @EntityGraph(attributePaths={"relatedEntity"}) in relationship queries to avoid the N+1 problem.
6. Must use a DTO as The data container for multi-join queries with @Query.

### Service：

1. Service classes must be of type interface.
2. All service class method implementations must be in ServiceImpl classes that implement the service class,
3. All ServiceImpl classes must be annotated with @Service.
4. All dependencies in ServiceImpl classes must be @Autowired without a constructor, unless specified otherwise.
5. Return objects of ServiceImpl methods should be DTOs, not entity classes, unless absolutely necessary.
6. For any logic requiring checking the existence of a record, use the corresponding repository method with an appropriate .orElseThrow lambda method.
7. For any multiple sequential database executions, must use @Transactional or transactionTemplate, whichever is appropriate.

### Data Transfer object (DTO)：

1. Must be of type record, unless specified in a prompt otherwise.
2. Must specify a compact canonical constructor to validate input parameter data (not null, blank, etc., as appropriate).

### Vaadin UI:

1. All Vaadin views must be annotated with @Route and, if needed, @UIScope and @Component for Spring integration.
2. UI logic must not access repositories or entities directly; always use service and DTO layers.
3. UI classes should be kept thin, delegating business logic to services.
4. Use Bootstrap or Vaadin built-in theming for consistent, responsive design.
5. Do not use static fields for Vaadin components or Spring beans.
6. Use constructor injection for all dependencies in Vaadin views.
7. Avoid storing Vaadin components or beans in singleton scope; use @UIScope or @ViewScope as appropriate.
8. All navigation and redirects should use Vaadin's router mechanisms.
9. For demo/test data, ensure UI gracefully handles missing data and can auto-create demo records if needed.
10. Vaadin views are for interactive UI only and should not expose REST endpoints.

### RestController (Spring MVC):

1. Must annotate controller classes with @RestController.
2. Must specify class-level API routes with @RequestMapping, e.g. ("/api/user").
3. Class methods must use best practice HTTP method annotations, e.g, create = @PostMapping("/create"), etc.
4. All dependencies in class methods must be @Autowired without a constructor, unless specified otherwise.
5. Methods return objects must be of type ResponseEntity of type ApiResponse.
6. All class method logic must be implemented in a try..catch block(s).
7. Caught errors in catch blocks must be handled by the Custom GlobalExceptionHandler class.
8. Use RestController only to expose APIs for external clients (e.g., mobile, third-party, or integration), not for Vaadin UI logic.
9. Vaadin UI should never call the RestController endpoints directly within the same application; always use the service layer.

