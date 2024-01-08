package sample.cafekiosk.spring.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 엔터티 클래스는 아니지만, 공통적인 매핑 정보를 가지고 있는 부모 클래스
@EntityListeners(AuditingEntityListener.class)
//AuditingEntityListener 클래스가 생성 및 수정일자를 관리하는데 사용되는 리스너 역할
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdDateTime; //생성시간

    @LastModifiedDate
    private LocalDateTime modifiedDateTime; //변경시간
}
