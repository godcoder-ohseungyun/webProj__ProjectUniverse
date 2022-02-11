package PU.puservice.controller.applyController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 프로젝트 지원 및 승인 절차를 다루는 컨트롤러 입니다.
 */
@RestController
@Slf4j
public class ApplyController {

    @ApiOperation(value = "프로젝트 지원", notes = "지원시 프로젝트 게시자에게 알림과 지원자 정보를 전달합니다. \n - 지원자 정보는 게시물 하단에 프로필 링크로 표시됩니다.")
    @GetMapping
    public void apply(){

    }

    @ApiOperation(value = "지원지 승인", notes = "지원자 승인시 지원자에게 간단한 메세지와 함께 승인 알림을 전송합니다 \n - 지원자의 프로필 목록에 해당 프로젝트가 저장됩니다." )
    @GetMapping
    public void approve(){

    }
}
