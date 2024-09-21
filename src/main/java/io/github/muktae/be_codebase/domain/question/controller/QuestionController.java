package io.github.muktae.be_codebase.domain.question.controller;

import io.github.muktae.be_codebase.common.resolver.AuthUser;
import io.github.muktae.be_codebase.common.security.jwt.JwtTokenInfo;
import io.github.muktae.be_codebase.domain.question.dto.QuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class QuestionController
{
    @PostMapping("")
    public void createQuiz(
            @AuthUser JwtTokenInfo jwtTokenInfo,
            @RequestPart(name = "quizRequest") QuestionRequest.Create quizRequest,
            @RequestPart(name = "files") List<MultipartFile> files)
    {

    }
}
