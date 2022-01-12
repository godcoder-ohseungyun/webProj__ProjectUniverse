package PU.puservice.domain.post;

import lombok.Data;

/**
 * 사용자가 마스터 권한을 가지고있다는 표시
 *
 * 싱글톤으로 설계
 *
 * using in PostController.postdetail();
 */

@Data //getter setter를 자동으로 생성해주는 lombok annotation json으로 쓸꺼면 getter setter 다 필요함
public class MasterKey {
    private final String key = "Master";


    //미리 생성해둠, 자주 사용될 객체임 미리 생성되어도 자원 낭비 x
    //필요할때 호출해서 사용
    public static final MasterKey masterKey = new MasterKey();

    //private 생성자.
    private MasterKey() {}

}
