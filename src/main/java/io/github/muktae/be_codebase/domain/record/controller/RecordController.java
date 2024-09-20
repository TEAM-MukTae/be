package io.github.muktae.be_codebase.domain.record.controller;

import io.github.muktae.be_codebase.common.resolver.AuthUser;
import io.github.muktae.be_codebase.common.response.SuccessResponse;
import io.github.muktae.be_codebase.common.security.jwt.JwtTokenInfo;
import io.github.muktae.be_codebase.domain.record.dto.RecordRequest;
import io.github.muktae.be_codebase.domain.record.dto.RecordResponse;
import io.github.muktae.be_codebase.domain.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/audio")
public class RecordController {

    private final RecordService recordService;

    @GetMapping("")
    public ResponseEntity<SuccessResponse<List<RecordResponse.GetVoiceList>>> getRecords(
            @AuthUser JwtTokenInfo token
    ) {
        return SuccessResponse.of(
                recordService.getRecords(token.getUserId())
        ).setStatus(HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<SuccessResponse<RecordResponse.Create>> uploadVoice(
            @AuthUser JwtTokenInfo jwtTokenInfo,
            @RequestPart(name = "recordRequest") RecordRequest.Create recordRequest,
            @RequestPart(name = "file") MultipartFile file
    ) {
        return SuccessResponse.of(
                recordService.uploadWithKafka(jwtTokenInfo.getUserId(), recordRequest, file)
        ).setStatus(HttpStatus.OK);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<SuccessResponse<RecordResponse.Detail>> detail(
            @AuthUser JwtTokenInfo jwtTokenInfo,
            @PathVariable("recordId") Long recordId
    ) {
        return SuccessResponse.of(
                recordService.getRecordDetail(jwtTokenInfo.getUserId(), recordId)
        ).setStatus(HttpStatus.OK);
    }
}
