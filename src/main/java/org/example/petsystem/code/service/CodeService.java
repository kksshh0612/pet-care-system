package org.example.petsystem.code.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.code.domain.CodeDetail;
import org.example.petsystem.code.domain.CodeGroup;
import org.example.petsystem.global.exception.CustomException;
import org.example.petsystem.global.exception.ErrorCode;
import org.example.petsystem.code.dto.request.CodeDetailModifyRequest;
import org.example.petsystem.code.dto.request.CodeDetailRegisterRequest;
import org.example.petsystem.code.dto.request.CodeGroupModifyRequest;
import org.example.petsystem.code.dto.request.CodeGroupRegisterRequest;
import org.example.petsystem.code.dto.response.CodeDetailListResponse;
import org.example.petsystem.code.dto.response.CodeDetailResponse;
import org.example.petsystem.code.dto.response.CodeGroupListResponse;
import org.example.petsystem.code.dto.response.CodeGroupResponse;
import org.example.petsystem.code.repository.CodeDetailRepository;
import org.example.petsystem.code.repository.CodeGroupRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CodeService {

    private final CodeGroupRepository codeGroupRepository;
    private final CodeDetailRepository codeDetailRepository;

    /**
     * 코드 그룹 등록
     * @param codeGroupRegisterRequest
     */
    @Transactional
    public void registerCodeGroup(CodeGroupRegisterRequest codeGroupRegisterRequest){

        CodeGroup codegroup = codeGroupRegisterRequest.toEntity();

        codeGroupRepository.save(codegroup);
    }

    /**
     * 코드 그룹 수정
     * @param codeGroupModifyRequest
     */
    @Transactional
    public void modifyCodeGroup(CodeGroupModifyRequest codeGroupModifyRequest){

        CodeGroup codeGroup = codeGroupRepository.findById(codeGroupModifyRequest.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.CODE_GROUP_NOT_FOUND));

        codeGroup.modify(codeGroupModifyRequest.getCode(), codeGroupModifyRequest.getGroupName(),
                codeGroupModifyRequest.getDescription());
    }

    /**
     * 코드 그룹 목록 조회 (페이징 10개)
     * @param page
     * @param size
     * @return
     */
    public CodeGroupListResponse findCodeGroupList(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        List<CodeGroup> codeGroups = codeGroupRepository.findAll(pageRequest).getContent();

        return CodeGroupListResponse.of(
                codeGroups.stream()
                        .map(codeGroup -> CodeGroupResponse.from(codeGroup))
                        .collect(Collectors.toList())
        );
    }

    /**
     * 코드 상세 등록
     * @param codeDetailRegisterRequest
     */
    @Transactional
    public void registerCodeDetail(CodeDetailRegisterRequest codeDetailRegisterRequest){

        CodeGroup codeGroup = codeGroupRepository.findById(codeDetailRegisterRequest.getCodeGroupId())
                .orElseThrow(() -> new CustomException(ErrorCode.CODE_GROUP_NOT_FOUND));

        CodeDetail codeDetail = codeDetailRegisterRequest.toEntity(codeGroup);

        codeDetailRepository.save(codeDetail);
    }

    /**
     * 코드 상세 수정
     * @param codeDetailModifyRequest
     */
    @Transactional
    public void modifyCodeDetail(CodeDetailModifyRequest codeDetailModifyRequest){

        CodeDetail codeDetail = codeDetailRepository.findById(codeDetailModifyRequest.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.CODE_DETAIL_NOT_FOUND));

        codeDetail.modify(codeDetailModifyRequest.getCodeName(), codeDetailModifyRequest.getCodeValue(),
                codeDetailModifyRequest.isActive());
    }

    /**
     * 코드 상세 활성/비활성화 toggle
     * @param codeDetailId
     */
    @Transactional
    public void toggleCodeDetailActivation(Long codeDetailId){

        CodeDetail codeDetail = codeDetailRepository.findById(codeDetailId)
                .orElseThrow(() -> new CustomException(ErrorCode.CODE_DETAIL_NOT_FOUND));

        codeDetail.toggleActiveStatus();
    }

    /**
     * 코드 상세 목록 조회 (페이징 10개씩)
     * @param codeGroupId
     * @param page
     * @param size
     * @return
     */
    public CodeDetailListResponse findCodeDetailList(Long codeGroupId, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        CodeGroup codeGroup = codeGroupRepository.findById(codeGroupId)
                .orElseThrow(() -> new CustomException(ErrorCode.CODE_GROUP_NOT_FOUND));

        List<CodeDetail> codeDetails = codeDetailRepository.findAllByCodeGroup(codeGroup, pageRequest).getContent();

        return CodeDetailListResponse.of(
                codeDetails.stream()
                        .map(codeDetail -> CodeDetailResponse.of(codeDetail))
                        .collect(Collectors.toList())
        );
    }



}
