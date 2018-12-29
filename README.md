# Spring-Reference


## 1. Arguments

Run/Debug Configuration에서 VM options와 Program arguments를 줄 수 있는데 jvm arguments는 못 가져온다.
```java
@Component
public class SampleListener {

    public SampleListener(ApplicationArguments arguments) {
        System.out.println("arg1 : " + arguments.containsOption("arg1"));
    }
}
```

## 2. Custom Properties

```java
@Component
@ConfigurationProperties("jay")
public class JayProperties {
    
}
```
프로퍼티를 Component로 정의해주고 빈 규약에 따르는 클래스를 만든다. 그러면 application.properties의 값을 매핑하여 사용할 수 있다.

## 3. Profiles

application-[customeName].properties파일은 application.properties를 오버라이딩한다. 

```java
@Configuration
@Profile("test")
public class BaseConfiguration {

    @Bean
    public String hello() {
        return "hello";
    }
}
``` 
Profile을 정의해주고 배포되는 jar파일에 java -jar [jar이름] --spring.profile.active=[이름] 으로 각각 실행할 수 있다.

## 4. Test
@RunWith (SpringRunner.class)는 Spring의 테스트 지원을 사용하여 JUnit을 실행하도록 지시한다.

```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello_테스트() throws Exception{
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello jay"))
                .andDo(print());
    }
}
```
서버 컨테이너는 Mock으로 받는다. 실제 내장톰캣이 이용되지 않는다.

```java
@MockBean
SampleService mockSampleService;

@Test
public void hello_테스트() throws Exception{
    when(mockSampleService.getName()).thenReturn("hellojay");
}
```
혹은 이런 식으로 서비스를 mocking해서 분리하여 사용할 수 있다.

```java
@WebMvcTest
```
를 이용하면 계층을 나눌 수 있다. 이것은 하나의 컨트롤러에만 제한되며 Mock과 조합하여 사용한다.