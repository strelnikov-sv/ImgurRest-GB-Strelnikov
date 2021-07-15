package ru.strelnikovsv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostImageResponse {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("data")
    private Data data;
}