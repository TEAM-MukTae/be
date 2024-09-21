package io.github.muktae.be_codebase.domain.workbook.controller;

import io.github.muktae.be_codebase.common.resolver.AuthUser;
import io.github.muktae.be_codebase.common.response.SuccessResponse;
import io.github.muktae.be_codebase.common.security.jwt.JwtTokenInfo;
import io.github.muktae.be_codebase.domain.workbook.dto.QuestionRequest;
import io.github.muktae.be_codebase.domain.workbook.service.WorkbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class QuestionController {

    private final WorkbookService questionService;

    @PostMapping("")
    public ResponseEntity<SuccessResponse<Void>> createQuiz(
            @AuthUser JwtTokenInfo jwtToken,
            @RequestPart(name = "quizRequest") QuestionRequest.Create quizRequest,
            @RequestPart(name = "files") List<MultipartFile> files
    ) {
        questionService.uploadWithKafka(quizRequest.getIdList(), files);
        return ResponseEntity.noContent().build();

    }
}
