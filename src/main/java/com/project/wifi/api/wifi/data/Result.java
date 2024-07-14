package com.project.wifi.api.wifi.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Result {

    @SerializedName("CODE")
    @Expose
    private String code;
    @SerializedName("MESSAGE")
    @Expose
    private String message;


}
