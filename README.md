# Project Name
Insta App

# Frameworks and Language Used
**Spring Boot** 3.1.4
**Java** 17
**Maven** 3.8.1

# Data Flow


_**Controller:**_ The controller has endpoints for signup user, signin user, update user, signout user, make a post, get a list of posts. 

The @PostMapping annotation is used for the signup, signin/{email}/{password}, post endpoint to handle HTTP POST requests with a JSON request body containing required parameters.
```
@PostMapping("signup")
    public String UserSignUp(@RequestBody User user)
    {
        return userService.UserSignUp(user);
    }

    @PostMapping("signin/{email}/{password}")
    public String UserSignIn(@PathVariable String email, @PathVariable String password)
    {
        return userService.UserSignIn(email,password);
    }
```
```
 @PostMapping("post")
    public String UserPost(@RequestParam String email, @RequestParam String tokenValue, @RequestBody Post post)
    {
        return postService.UserPost(email,tokenValue,post);
    }
```

The @GetMapping annotation is used for the post endpoints to handle HTTP GET request with RequestParam as email and tokenValue, respectively. The @RequestParam annotation is used to extract the User email and User tokenValue from the request URL and pass it to the GetUserPosts method.
```
@GetMapping("post")
    public List<Post> GetUserPosts(@RequestParam String email, @RequestParam String tokenValue)
    {
        return postService.GetUserPosts(email,tokenValue);
    }
```

The @PutMapping annotation is used for the user endpoint to handle HTTP UPDATE requests with a requestbody as User object and PathVariable as email and tokenValue.
```
@PutMapping("user")
    public String UpdateUser(@RequestParam String email, @RequestParam String tokenValue, @RequestBody User user)
    {
        return userService.UpdateUser(email,tokenValue,user);
    }
```

The @DeleteMapping annotation is used for the signout endpoint to handle HTTP DELETE requests with a path variable as user email and user tokenValue.
```
 @DeleteMapping("signout")
    public String UserSignOut(@RequestParam String email, @RequestParam String tokenValue)
    {
        return userService.UserSignOut(email,tokenValue);
    }
```

The controller class also has an autowired all the required Service interface to handle business logic for the Insta App.

This implementation demonstrates a basic setup for a REST API controller in Spring Boot, but it can be expanded upon and customized based on specific requirements for the Insta App.


_**Services**:_ The services layer contains the business logic of the application. It receives requests from the controller, performs necessary computations or data manipulations, and interacts with the repository layer to access data.
```
@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;
```
```
@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;
```
```
@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepo authenticationRepo;

    public void createToken(AuthenticationToken token) {
        authenticationRepo.save(token);
    }

    public boolean Authenticate(String email, String tokenValue)
    {
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(tokenValue);
        if(token!=null) {
            return token.getUser().getUserEmail().equals(email);
        }
        else {
            return false;
        }
    }

    public void DeleteToken(String tokenValue) {
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(tokenValue);
        authenticationRepo.delete(token);
    }
}
```

_**Repository:**_ The repository layer is responsible for interacting with the database. It uses Spring Data JPA to perform CRUD (create, read, update, delete) operations on entities.

In the application.properties all the text required for connection with MySQL database are written.
```
spring.datasource.url=jdbc:mysql://localhost:3306/Insta_App
spring.datasource.username=root
spring.datasource.password=Ravi.067456
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true
```

# Database Structure Used
I have used MySQL as DataBase

# Project Summary
In this project i have created different endpoints, custom finders, @OneToOne mapping between AuthenticationToken and User model and @ManyToOne mapping between Post and User Model.

