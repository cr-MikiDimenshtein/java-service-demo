# Spring boot workshop

### Prerequisites
- Java JDK 15 and above
- Maven 3.6.3 and above
- IDE (IntelliJ IDEA, Eclipse, etc.)
- Postman
- Docker

### Creating a project 
- Go to [Spring Initializr](https://start.spring.io/)
- Fill in the details - install the following dependencies:
    - Spring Web
    - Spring Data JPA
    - PostgreSQL
    - Lombok
    - Spring Boot DevTools
- Click on Generate
- Download the project
- Extract the project
- Open the project in your IDE

### Setting up the database
- Add a docker-compose.yml file in the root of the project with the following content:
```yaml
version: '3'
services:
  postgres:
    image: postgres:latest
    ports:
      - 5432:5432
    volumes:
      - ~/apps/postgres:/var/lib/postgresql/data
    environment:
      - POSTGRES_PASSWORD=S3cret
      - POSTGRES_USER=user
      - POSTGRES_DB=postgres
```
- Run the following command in the terminal to start the database:
```shell
docker-compose up
```
- in the application.properties file, add the following properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=user
spring.datasource.password=S3cret
```

### Expose an endpoint to insert and get from the db
- Create a new package called model
- Create a new class called `JobStatus` with the following content:
```java
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "job_status")
public class JobStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "job_runtime_id")
    private UUID jobRuntimeId;
    private String status;
    @Column(name = "job_name")
    private String jobName;

    @Column(name = "last_updated")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date lastUpdated;

}
```
- Create a new package called repository
- Create a new interface called `JobStatusRepository` with the following content:
```java
@Repository
public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {
}
```
- Create a new package called service
- Create a new class called `JobService` with the following content:
```java
@Service
public class JobService {

    private final JobStatusRepository jobStatusRepository;

    @Autowired
    public JobService(JobStatusRepository jobStatusRepository) {
        this.jobStatusRepository = jobStatusRepository;
    }

    public void addJobStatus(JobStatus jobStatus) {
        jobStatus.setLastUpdated(new java.sql.Date(System.currentTimeMillis()));
        jobStatusRepository.save(jobStatus);
    }

    public List<JobStatus> getAll() {
        return jobStatusRepository.findAll();
    }
}
```
- Create a new package called controller
- Create a new class called `JobController` with the following content:
```java
@RestController("JobsController")
@RequestMapping(JobsController.PATH)
public class JobsController {

    public static final String PATH = "/job";

    private final JobService jobService;

    @Autowired
    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<JobStatus> getInfo() {
        return jobService.getAll();
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public String addJobStatus(@RequestBody JobStatus jobStatus) {
        jobService.addJobStatus(jobStatus);
        return "Job status added successfully";
    }
}
```
- Run the application
- Open Postman and send a POST request to `http://localhost:8080/job/new` with the following body:
```json
{
    "status": "RUNNING",
    "jobName": "Job 1"
}
```
- Send a GET request to `http://localhost:8080/job/all` and you should see the job status you just added