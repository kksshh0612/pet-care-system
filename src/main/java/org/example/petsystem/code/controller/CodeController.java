package org.example.petsystem.code.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.code.dto.request.CodeDetailModifyRequest;
import org.example.petsystem.code.dto.request.CodeDetailRegisterRequest;
import org.example.petsystem.code.dto.request.CodeGroupModifyRequest;
import org.example.petsystem.code.dto.request.CodeGroupRegisterRequest;
import org.example.petsystem.code.service.CodeService;
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
public class CodeController {

    private final CodeService codeService;

    // 코드 그룹 등록
    @PostMapping("/group")
    public ResponseEntity<?> registerCodeGroup(@Valid @RequestBody CodeGroupRegisterRequest codeGroupRegisterRequest) {

        codeService.registerCodeGroup(codeGroupRegisterRequest);
        return ResponseEntity.ok().build();
    }

    // 코드 그룹 수정
    @PatchMapping("/group")
    public ResponseEntity<?> modifyCodeGroup(@Valid @RequestBody CodeGroupModifyRequest codeGroupModifyRequest) {

        codeService.modifyCodeGroup(codeGroupModifyRequest);
        return ResponseEntity.ok().build();
    }

    // 코드 그룹 목록 조회
    @GetMapping("/group")
    public ResponseEntity<?> getCodeGroupList(@RequestParam("page") int page, @RequestParam("size") int size){

        return ResponseEntity.ok(codeService.findCodeGroupList(page, size));
    }

    // 코드 상세 등록
    @PostMapping("/detail")
    public ResponseEntity<?> registerCodeDetail(@Valid @RequestBody CodeDetailRegisterRequest codeDetailRegisterRequest) {

        codeService.registerCodeDetail(codeDetailRegisterRequest);
        return ResponseEntity.ok().build();
    }

    // 코드 상세 수정
    @PatchMapping("/detail")
    public ResponseEntity<?> modifyCodeDetail(@Valid @RequestBody CodeDetailModifyRequest codeDetailModifyRequest) {

        codeService.modifyCodeDetail(codeDetailModifyRequest);
        return ResponseEntity.ok().build();
    }

    // 코드 상세 목록 조회
    @GetMapping("/detail")
    public ResponseEntity<?> getCodeDetailList(@RequestParam("page") int page, @RequestParam("size") int size,
                                               @RequestParam("code-group-id") Long codeGroupId){

        return ResponseEntity.ok(codeService.findCodeDetailList(codeGroupId, page, size));
    }

    // 코드 상세 활성/비활성화 토글
    @PatchMapping("/detail/activation-toggle/{code-detail-id}")
    public ResponseEntity<?> toggleActivation(@PathVariable("code-detail-id") Long codeDetailId) {

        codeService.toggleCodeDetailActivation(codeDetailId);
        return ResponseEntity.ok().build();
    }


}