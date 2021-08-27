package hello.hellospring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;


@Configuration
public class SpringConfig {
	
	//private DataSource dataSource;
	/*
	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	*/

	private final MemberRepository memberRepository;
	
	public SpringConfig(MemberRepository memberRepository) { 
		this.memberRepository = memberRepository;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
	/*
	@Bean
	public MemberRepository memberRepository() {
		//return new MemberRepository();
		//return new JdbcMemberRepository(dataSource);
		//return new JdbcTemplateMemberRepository(dataSource);
	}
	*/
	
	// AOP는 일반적인 경우는 아니기 때문에
	// Spring Bean 에 직접 등록해줘서 인식할 수 있도록 하는게 좋다. 
	/*
	@Bean
	public TimeTraceAop timeTraceAop() {
		return new TimeTraceAop();
	}
	*/
}
