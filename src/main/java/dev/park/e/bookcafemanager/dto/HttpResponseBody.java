package dev.park.e.bookcafemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HttpResponseBody {
    private String message;
    private Object data;
}
