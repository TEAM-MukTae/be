package io.github.muktae.be_codebase.domain.translate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TranslateRequestDto {

    private String text;
    private String target;

}
