package com.project.my.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateShortUrlInDto {
    @NotEmpty(message = "in.url.create.short.url.not.empty")
    private String url;
}
