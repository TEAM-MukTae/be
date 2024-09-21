package io.github.muktae.be_codebase.domain.workbook.controller;

import io.github.muktae.be_codebase.common.resolver.AuthUser;
import io.github.muktae.be_codebase.common.response.SuccessResponse;
import io.github.muktae.be_codebase.common.security.jwt.JwtTokenInfo;
import io.github.muktae.be_codebase.domain.workbook.dto.QuestionRequest;
import io.github.muktae.be_codebase.domain.workbook.dto.WorkbookRequest;
import io.github.muktae.be_codebase.domain.workbook.dto.WorkbookResponse;
import io.github.muktae.be_codebase.domain.workbook.service.WorkbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class WorkbookController {

    private final WorkbookService questionService;

    @GetMapping("")
    public ResponseEntity<SuccessResponse<List<WorkbookResponse.Create>>> getCreatedQuiz(
            @AuthUser JwtTokenInfo jwtToken
    ) {
        return SuccessResponse.of(
                questionService.getWorkbook(jwtToken.getUserId())
        ).setStatus(HttpStatus.OK);
    }

    @GetMapping("/{workbookId}")
    public ResponseEntity<SuccessResponse<WorkbookResponse.Detail>> getQuizDetail(
            @AuthUser JwtTokenInfo jwtToken,
            @PathVariable("workbookId") Long workbookId
    ) {
        return SuccessResponse.of(
                questionService.getWorkbookDetail(jwtToken.getUserId(), workbookId)
        ).setStatus(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<SuccessResponse<Void>> createQuiz(
            @AuthUser JwtTokenInfo jwtToken,
            @RequestPart(name = "quizRequest") QuestionRequest.Create quizRequest,
            @RequestPart(name = "files") List<MultipartFile> files
    ) {
        questionService.uploadWithKafka(quizRequest.getTitle(), quizRequest.getIdList(), files, quizRequest.getLanguage());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{workbookId}")
    public ResponseEntity<SuccessResponse<Void>> updateQuizTitle(
            @AuthUser JwtTokenInfo jwtTokenInfo,
            @PathVariable("workbookId") Long workbookId,
            @RequestBody WorkbookRequest.EditTitle quizEditRequest
    ) {
        questionService.editTitle(jwtTokenInfo.getUserId(), workbookId, quizEditRequest.getTitle());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{workbookId}")
    public ResponseEntity<SuccessResponse<Void>> deleteQuiz(
            @AuthUser JwtTokenInfo jwtTokenInfo,
            @PathVariable("workbookId") Long workbookId
    ) {
        questionService.deleteWorkbook(jwtTokenInfo.getUserId(), workbookId);
        return ResponseEntity.noContent().build();
    }
}
