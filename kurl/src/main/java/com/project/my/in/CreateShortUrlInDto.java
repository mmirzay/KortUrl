package com.project.my.in;

import static com.project.my.configuration.Constants.LONG_URL_MAX_LENGTH;

import com.project.my.entity.Url;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateShortUrlInDto {
    @NotEmpty(message = "in.url.create.short.url.not.empty")
    @Size(max = LONG_URL_MAX_LENGTH, message = "in.url.create.short.url.size.limit")
    private String url;

    public Url toUrl(String shortUrl) {
        return Url.builder()
                .longUrl(url)
                .shortUrl(shortUrl)
                .build();
    }
}
