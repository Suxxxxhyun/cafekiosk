### @NoArgsConstructor(access = AccessLevel.PROTECTED)

### 기본 생성자를 추가할때, AccessLevel을 PRIVATE이 아닌 PROTECTED를 하는 이유는?

```java
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자를 생성하되, 접근수준을 protected로 하자!
@Entity
    public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productNumber;
}
```

@NoArgsConstructor(access = AccessLevel.PROTECTED)

= **아무런 매개변수가 없는 생성자를 생성하되 다른 패키지에 소속된 클래스는 접근을 불허한다.**

즉, 아래와 같은 코드를 생성해준다는 뜻이다.

```java
protected class Product() {

}
```

- **접근 권한을 private이 아니라, protected를 해야만, 프록시 객체를 생성할 수 있기 때문**

### 그럼 프록시 객체는 뭔데?

- **프록시** : 연관된 객체를 처음부터 DB에서 조회하는 것이 아니라, 내가 사용하는 시점에 DB로부터 조회할 수 있다.
- **프록시 객체** : 지연로딩을 통해, 실제 객체 대신 DB조회를 지연할 수 있는 가짜 객체

```java
public class Post{

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String hashTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user){
        // 기존 user와의 관계를 제거
        if (this.user != null) {
            this.user.getPostList().remove(this);
        }
        this.user = user;
        user.getPostList().add(this);
    }
}
```

```java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;
    private String name;
    private LocalDate birth;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> postList = new ArrayList<>();

}
```

```java
Post post = em.find(Post.class, "postId"); // User 프록시 객체를 생성한다.
User user = post.getUser();
System.out.println("게시글 제목 : " + post.getTitle());
```

위 코드에서는 post.getTitle()를 보면 알 수 있듯, Post의 정보만 출력하고 있다. 즉, 연관관계인 User정보는 필요하지 않음. 따라서, `@ManyToOne(fetch = FetchType.LAZY)` 와 `@OneToMany(fetch = FetchType.LAZY` 에서 알 수 있듯, User객체는 필요하지 않기때문에 지연로딩을 권장한다.

- **지연로딩** : 특정한 엔터티가 실제 사용될때까지 DB조회를 지연하는 것
    - **지연 로딩** : 연관된 엔티티를 프록시로 조회하고, 프록시를 실제 사용할 때 프록시를 초기화하면서 데이터베이스를 조회한다.
    - **즉시 로딩** : 연관된 엔티티를 즉시 조회하고, 하이버네이트는 가능하면 SQL 조인을 사용해서 한 번에 조회한다.
    - 지연로딩을 권장.

지연로딩을 사용하려면, 실제 엔터티 객체 대신 DB조회를 지연할 수 있도록 도와주는 가짜 객체가 필요한데 이를 **프록시 객체**라고 한다.

- 참조블로그
    - [https://velog.io/@kevin_/내가-지연-로딩으로-작성했던-이유](https://velog.io/@kevin_/%EB%82%B4%EA%B0%80-%EC%A7%80%EC%97%B0-%EB%A1%9C%EB%94%A9%EC%9C%BC%EB%A1%9C-%EC%9E%91%EC%84%B1%ED%96%88%EB%8D%98-%EC%9D%B4%EC%9C%A0)
    - [https://velog.io/@kevin_/내가-NoargsConstructor-access-AccessLevel.PROTECTED를-왜-작성했을까](https://velog.io/@kevin_/%EB%82%B4%EA%B0%80-NoargsConstructor-access-AccessLevel.PROTECTED%EB%A5%BC-%EC%99%9C-%EC%9E%91%EC%84%B1%ED%96%88%EC%9D%84%EA%B9%8C)