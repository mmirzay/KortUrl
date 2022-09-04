package com.project.my.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ActionResult<T> {

    private T data;
    private boolean success;
    private String message;

    public ActionResult(boolean success) {
        this.success = success;
    }

    public ActionResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
