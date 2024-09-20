package io.github.muktae.be_codebase.common.translate.controller;

import io.github.muktae.be_codebase.common.translate.dto.TranslateRequestDto;
import io.github.muktae.be_codebase.common.translate.service.TranslateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/translate")
public class TranslateController {

    private final TranslateService translateService;

    /**
     * 구글 번역 API
     */
    @PostMapping("")
    public String translate(@RequestBody TranslateRequestDto translateRequest
    ) {
        log.info(translateRequest.getText());
        log.info(translateRequest.getTarget());

        String test = translateService.translateText(translateRequest.getText(), translateRequest.getTarget());
        
        log.info("text = {}", test);

        return test;
    }
}
