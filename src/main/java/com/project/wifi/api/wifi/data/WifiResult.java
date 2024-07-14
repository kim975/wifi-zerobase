package com.project.wifi.api.wifi.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WifiResult {
    @SerializedName("TbPublicWifiInfo")
    @Expose
    private TbPublicWifiInfo tbPublicWifiInfo;
}
