package sample.cafekiosk.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
//JPA 엔티티의 생성일자(created date) 및
// 수정일자(modified date)를 자동으로 관리하기 위해 사용되는 설정
@SpringBootApplication
public class CafekioskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafekioskApplication.class, args);
	}

}
