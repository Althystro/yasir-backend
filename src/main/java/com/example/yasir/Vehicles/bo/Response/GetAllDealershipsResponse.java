package com.example.yasir.Vehicles.bo.Response;

import com.example.yasir.Vehicles.entity.DealershipEntity;

import java.util.List;

public class GetAllDealershipsResponse {

    private List<DealershipEntity> dealerships;
    private String error;

    public List<DealershipEntity> getDealerships() {
        return dealerships;
    }

    public void setDealerships(List<DealershipEntity> dealerships) {
        this.dealerships = dealerships;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
