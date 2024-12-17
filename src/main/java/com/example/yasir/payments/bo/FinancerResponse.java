package com.example.yasir.payments.bo;

import com.example.yasir.payments.entity.FinancerEntity;

import java.util.List;

public class FinancerResponse {

    private List<FinancerEntity> financer;

    private String error;

    public List<FinancerEntity> getFinancer() {
        return financer;
    }

    public void setFinancer(List<FinancerEntity> financer) {
        this.financer = financer;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
