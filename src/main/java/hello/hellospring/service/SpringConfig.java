package hello.hellospring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
