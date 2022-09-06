package com.project.my.entity;

import com.project.my.configuration.Constants;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = Constants.LONG_URL_MAX_LENGTH)
    private String longUrl;

    @Column(nullable = false, unique = true, length = Constants.SHORT_URL_MAX_LENGTH)
    private String shortUrl;

}
