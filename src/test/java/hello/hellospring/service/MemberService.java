package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberService {
	
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	
	/*
	 * 회원 가입 
	 */
	public Long join(Member member) {
		validateDuplicateMember(member);
		
		memberRepository.save(member);
		return member.getId();
	}


	/*
	 * 중복 회원 검증 
	 * - 같은 이름의 회원은 중복 회원으로 간주하고 block
	 */
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	/*
	 * 전체 회원 조회 
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Optional<Member> findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

}
