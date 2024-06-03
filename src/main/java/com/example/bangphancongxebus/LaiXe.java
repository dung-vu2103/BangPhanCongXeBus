package com.example.bangphancongxebus;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;


public class LaiXe {
    private static final AtomicInteger maLXCounter = new AtomicInteger(10000);
    private int maLX;
    private String hoTen;
    private String diaChi;
    private String sdt;
    private String trinhDo;

    public LaiXe(String hoTen, String diaChi, String sdt, String trinhDo) {
        this.maLX = maLXCounter.getAndIncrement();
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.trinhDo = trinhDo;
    }

    public int getMaLX() {
        return maLX;
    }

    public String getHoTen() {
        return hoTen;
    }

    @Override
    public String toString() {
        return maLX + " - " + hoTen + " - " + diaChi + " - " + sdt + " - " + trinhDo;
    }
}

