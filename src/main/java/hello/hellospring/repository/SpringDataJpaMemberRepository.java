package hello.hellospring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import hello.hellospring.domain.Member;


// 1. interface 를 만들고 spring-data-jpa 가 제공하는 JpaRepository를 상속받는다 
// 이렇게 되면 spring-data-jpa 가 interface에 대한 구현체를 자동으로 만들어내고, 스프링 빈에 등록해둔다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
	
	// JPQL SELECT m from Member m where m.name = ?
	// findBy 뒤의 값을 컬럼으로 인식한다
	// cf. findByNameAndAge ...
	@Override
	Optional<Member> findByName(String name);
}