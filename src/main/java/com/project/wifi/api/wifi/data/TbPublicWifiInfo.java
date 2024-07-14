package com.project.wifi.api.wifi.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class TbPublicWifiInfo {
    @SerializedName("list_total_count")
    @Expose
    private Integer listTotalCount;
    @SerializedName("RESULT")
    @Expose
    private Result result;
    @SerializedName("row")
    @Expose
    private List<WifiDetail> row;


}
