package sample.cafekiosk.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
//JPA 엔티티의 생성일자(created date) 및
// 수정일자(modified date)를 자동으로 관리하기 위해 사용되는 설정
@Configuration
public class JpaAuditingConfig {
}
