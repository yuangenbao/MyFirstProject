package com.example.ygb.myfirstproject.entity;

/**
 * Created by YuanBuyuan on 2017/11/23 0023.
 */

public class MemoItemData {
    private String status;
    private String timeYmd;
    private String timeHm;

    public MemoItemData(String status,String timeYmd,String timeHm){
        this.status=status;
        this.timeYmd=timeYmd;
        this.timeHm=timeHm;

    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeYmd() {
        return timeYmd;
    }

    public void setTimeYmd(String timeYmd) {
        this.timeYmd = timeYmd;
    }

    public String getTimeHm() {
        return timeHm;
    }

    public void setTimeHm(String timeHm) {
        this.timeHm = timeHm;
    }
}


