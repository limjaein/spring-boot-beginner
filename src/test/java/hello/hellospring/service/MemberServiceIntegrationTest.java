package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;


@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("jaein");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findMember(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberService.join(member1);
		assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
	}	
	
	
}
