package org.example.petsystem.code.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.code.dto.request.CodeDetailModifyRequest;
import org.example.petsystem.code.dto.request.CodeDetailRegisterRequest;
import org.example.petsystem.code.dto.request.CodeGroupModifyRequest;
import org.example.petsystem.code.dto.request.CodeGroupRegisterRequest;
import org.example.petsystem.code.dto.response.CodeDetailListResponse;
import org.example.petsystem.code.dto.response.CodeGroupListResponse;
import org.example.petsystem.code.service.CodeService;
import org.example.petsystem.member.dto.response.MypageMemberInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/code")
@RequiredArgsConstructor
@Tag(name = "코드 API")
public class CodeController {

    private final CodeService codeService;

    @Operation(summary = "코드 그룹 등록", description = "관리자가 코드 그룹을 저장한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "필수 입력 정보를 입력하지 않은 경우 OR 아이디/비밀번호가 틀린 경우")}
            )),
            @ApiResponse(responseCode = "403", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "권한이 없는 사용자가 접근할 경우")}
            ))
    })
    // 코드 그룹 등록
    @PostMapping("/group")
    public ResponseEntity<?> registerCodeGroup(@Valid @RequestBody CodeGroupRegisterRequest codeGroupRegisterRequest) {

        codeService.registerCodeGroup(codeGroupRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "코드 그룹 수정", description = "관리자가 코드 그룹을 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "필수 입력 정보를 입력하지 않은 경우 OR 아이디/비밀번호가 틀린 경우")}
            )),
            @ApiResponse(responseCode = "403", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "권한이 없는 사용자가 접근할 경우")}
            ))
    })
    // 코드 그룹 수정
    @PatchMapping("/group")
    public ResponseEntity<?> modifyCodeGroup(@Valid @RequestBody CodeGroupModifyRequest codeGroupModifyRequest) {

        codeService.modifyCodeGroup(codeGroupModifyRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "코드 그룹 목록 조회", description = "관리자가 코드 그룹 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CodeGroupListResponse.class))),
            @ApiResponse(responseCode = "403", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "권한이 없는 사용자가 접근할 경우")}
            ))
    })
    // 코드 그룹 목록 조회
    @GetMapping("/group")
    public ResponseEntity<?> getCodeGroupList(@RequestParam("page") int page, @RequestParam("size") int size){

        return ResponseEntity.ok(codeService.findCodeGroupList(page, size));
    }

    @Operation(summary = "상세 코드 등록", description = "관리자가 상세 코드를 저장한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "필수 입력 정보를 입력하지 않은 경우 OR 아이디/비밀번호가 틀린 경우")}
            )),
            @ApiResponse(responseCode = "403", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "권한이 없는 사용자가 접근할 경우")}
            ))
    })
    // 코드 상세 등록
    @PostMapping("/detail")
    public ResponseEntity<?> registerCodeDetail(@Valid @RequestBody CodeDetailRegisterRequest codeDetailRegisterRequest) {

        codeService.registerCodeDetail(codeDetailRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상세 코드 수정", description = "관리자가 상세 코드를 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "필수 입력 정보를 입력하지 않은 경우 OR 아이디/비밀번호가 틀린 경우")}
            )),
            @ApiResponse(responseCode = "403", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "권한이 없는 사용자가 접근할 경우")}
            ))
    })
    // 코드 상세 수정
    @PatchMapping("/detail")
    public ResponseEntity<?> modifyCodeDetail(@Valid @RequestBody CodeDetailModifyRequest codeDetailModifyRequest) {

        codeService.modifyCodeDetail(codeDetailModifyRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상세 코드 목록 조회", description = "관리자가 상세 코드 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CodeDetailListResponse.class))),
            @ApiResponse(responseCode = "403", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "권한이 없는 사용자가 접근할 경우")}
            ))
    })
    // 코드 상세 목록 조회
    @GetMapping("/detail/{code-group-id}")
    public ResponseEntity<?> getCodeDetailList(@RequestParam("page") int page, @RequestParam("size") int size,
                                               @PathVariable("code-group-id") Long codeGroupId){

        return ResponseEntity.ok(codeService.findCodeDetailList(codeGroupId, page, size));
    }

    @Operation(summary = "상세 코드 활성화/비활성화 전환", description = "관리자가 상세 코드를 활성화/비활성화 조치한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "권한이 없는 사용자가 접근할 경우")}
            ))
    })
    // 코드 상세 활성/비활성화 토글
    @PatchMapping("/detail/activation-toggle/{code-detail-id}")
    public ResponseEntity<?> toggleActivation(@PathVariable("code-detail-id") Long codeDetailId) {

        codeService.toggleCodeDetailActivation(codeDetailId);
        return ResponseEntity.ok().build();
    }


}