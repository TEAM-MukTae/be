
package io.github.muktae.be_codebase.domain.question.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

@Converter
public class QuestionChoicesConverter implements AttributeConverter<List<String>, String> {

    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if (strings == null || strings.isEmpty())
            return null;

        return String.join(SPLIT_CHAR, strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {

        if (string == null || string.isEmpty())
            return null;

        return Arrays.asList(string.split(SPLIT_CHAR));
    }
}