package com.project.my.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorModel {
    static final ObjectMapper objectMapper = new ObjectMapper();

    @JsonProperty("errorReason")
    private String errorReason;

    @JsonProperty("errorReasons")
    private List<String> errorReasons;


    public ErrorModel(String errorReason) {
        this.errorReason = errorReason;
    }

    public String createErrorAsString() {
        try {
            return new String(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(this), StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            log.error("could not create json object", e);
            throw new RuntimeException();
        }
    }
}