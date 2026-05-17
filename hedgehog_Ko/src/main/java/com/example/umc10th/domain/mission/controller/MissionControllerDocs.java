package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Mission", description = "사용자 미션 조회, 도전, 취소, 인증 관련 API")
public interface MissionControllerDocs {

    @Operation(
            summary = "내 미션 목록 조회",
            description = "사용자 ID와 지역, 상태 조건으로 내 미션 목록을 필터링하고 offset 기반 페이지네이션으로 조회합니다. pageNumber는 0부터 시작하며, pageSize는 한 페이지에 조회할 데이터 개수입니다."
    )
    ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "내 미션 목록 조회 요청 정보. 현재 7주차 미션 범위에서는 userId를 Request Body로 전달합니다.",
                    required = true
            )
            MissionReqDTO.GetMyMissionsDTO request,

            @Parameter(description = "조회할 지역 ID. 미입력 시 전체 지역 기준으로 조회합니다.", example = "1")
            Long regionId,

            @Parameter(description = "미션 상태. ASSIGNED, CHALLENGING, COMPLETED, EXPIRED, CANCELED 중 하나입니다.", example = "CHALLENGING")
            MissionStatus status,

            @Parameter(description = "한 페이지에 조회할 데이터 개수", example = "10")
            Integer pageSize,

            @Parameter(description = "0부터 시작하는 페이지 번호", example = "0")
            Integer pageNumber,

            @Parameter(description = "정렬 조건. 예: id,desc 또는 deadline,asc", example = "id,desc")
            String sort
    );

    @Operation(
            summary = "미션 도전 시작",
            description = "사용자에게 할당된 UserMission을 userMissionId로 조회하고 미션 상태를 도전 중으로 변경합니다."
    )
    ApiResponse<MissionResDTO.StartMissionResultDTO> startMission(
            @Parameter(description = "도전을 시작할 사용자 미션 ID", example = "1")
            Long userMissionId
    );

    @Operation(
            summary = "미션 취소",
            description = "사용자에게 할당된 UserMission을 userMissionId로 조회하고 미션 상태를 취소로 변경합니다."
    )
    ApiResponse<MissionResDTO.CancelMissionResultDTO> cancelMission(
            @Parameter(description = "취소할 사용자 미션 ID", example = "1")
            Long userMissionId
    );

    @Operation(
            summary = "미션 인증 요청",
            description = "미션 수행 인증을 요청하고 사용자에게 인증에 필요한 정보를 반환합니다."
    )
    ApiResponse<MissionResDTO.VerificationRequestResultDTO> requestMissionVerification(
            @Parameter(description = "인증을 요청할 사용자 미션 ID", example = "1")
            Long userMissionId
    );

    @Operation(
            summary = "미션 인증 확인",
            description = "사용자의 미션 인증을 확인하고 인증 성공 시 미션 완료 처리를 수행합니다."
    )
    ApiResponse<MissionResDTO.VerificationConfirmResultDTO> confirmMissionVerification(
            @Parameter(description = "인증을 확인할 사용자 미션 ID", example = "1")
            Long userMissionId
    );
}
