## deleteAll vs deleteAllInBatch
- deleteAll() : SimpleJpaRepository()에 오버라이딩되어 있으며, 
MyBatis, JdbcTemplate 등 기술에 상관없이 사용가능하다.
또, deleteAll()이 실행되면, findAll()의 결과로 얻은 리스트를 순회하며, **데이터를 한개씩 삭제함.**
즉, **N개의 데이터가 존재하면 N개릐 DELETE쿼리가 실행된다.** -> 테스트 실행시간이 오래 걸림.

- deleteAllInBatch() : **테이블에 있는 데이터를 전부 지우는 DELETE쿼리가 실행된다.** 
즉, 데이터 크기와 관계없이 한번의 쿼리로 clear가 가능함.

```java
package org.springframework.data.jpa.repository.support;

@Repository
@Transactional(readOnly = true)
public class SimpleJpaRepository<T, ID> implements JpaRepositoryImplementation<T, ID> {

    ...

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    @Override
    public List<T> findAll() {
        return getQuery(null, Sort.unsorted()).getResultList();
    }
	
    ...
	
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.Repository#deleteAll()
     */
    @Override
    @Transactional
    public void deleteAll() {
    
        for (T element : findAll()) {
            delete(element);
        }
    }
	
    ...

}
```

```java
package org.springframework.data.jpa.repository.support;

import static org.springframework.data.jpa.repository.query.QueryUtils.*;

@Repository
@Transactional(readOnly = true)
public class SimpleJpaRepository<T, ID> implements JpaRepositoryImplementation<T, ID> {

    ...

    private String getDeleteAllQueryString() {
        return getQueryString(DELETE_ALL_QUERY_STRING, entityInformation.getEntityName());
    }
    
    ...
	
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#deleteAllInBatch()
     */
    @Override
    @Transactional
    public void deleteAllInBatch() {
        em.createQuery(getDeleteAllQueryString()).executeUpdate();
    }
	
    ...

}
```

```java
package org.springframework.data.jpa.repository.query;

public abstract class QueryUtils {

    ...

    public static final String DELETE_ALL_QUERY_STRING = "delete from %s x";
    
    ...

}
```