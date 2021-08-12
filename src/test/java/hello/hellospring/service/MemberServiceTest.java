package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {
	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		// repo 신규 할당 
		memberRepository = new MemoryMemberRepository();
		memberRepository.clearStore();
	}
	
	/*
	 * 회원 가입 
	 */
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("hello");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findMember(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}


	/*
	 * 중복 회원 검증 
	 * - 같은 이름의 회원은 중복 회원으로 간주하고 block
	 */
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
		
		
		
		/*
		try {
			memberService.join(member2);
			fail("예외가 발생해야 합니다.");
		} catch (IllegalStateException e) {
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		}
		*/
		
		//then
	}	
	
	
}
