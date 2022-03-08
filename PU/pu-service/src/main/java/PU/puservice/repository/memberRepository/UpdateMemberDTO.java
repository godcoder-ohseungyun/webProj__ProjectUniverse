package PU.puservice.repository.memberRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateMemberDTO {
    private String name;
    private String email = "";
    private String photo; // 이부분은 추후에 파일 업로드 공부해서 바꿔야 할듯
    private String body;
    private String links;
    private String tags;
}
