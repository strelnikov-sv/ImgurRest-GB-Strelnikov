package ru.strelnikovsv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostImageResponseBase64 {

    @JsonProperty("data")
    public Boolean data;
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("status")
    public Integer status;
}