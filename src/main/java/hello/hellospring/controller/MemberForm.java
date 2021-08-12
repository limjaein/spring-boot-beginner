package hello.hellospring.controller;

// createMemberForm.html 의 form 태그 안 input값 매핑 
// input의 name값을 통해 매핑된다 (setName 함수 호출)
public class MemberForm {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
