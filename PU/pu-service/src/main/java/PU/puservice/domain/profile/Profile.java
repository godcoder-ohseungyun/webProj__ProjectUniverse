package PU.puservice.domain.profile;

import PU.puservice.domain.member.Member;
import PU.puservice.domain.post.Post;
import lombok.Data;

import java.util.List;

@Data //생성자 등 자동 구현해주긴 하지만 getter setter만 쓸것
public class Profile {
    private Long id;
    private String photo; // 이부분은 추후에 파일 업로드 공부해서 바꿔야 할듯
    private String body;
    private String links;
    private String tags;
    private List<String> projectList; //참여하고있는 공고 진행중인 프로젝트 리스트 -> 이게 Post 객체를 저장하는게 맞나?
    //포스트 링크를 보유해야지;;

    public Profile(Long id, String photo, String body, String links, String tags) {
        this.id = id;
        this.photo = photo;
        this.body = body;
        this.links = links;
        this.tags = tags;
    }
}
