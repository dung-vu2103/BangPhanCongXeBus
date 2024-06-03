package com.example.bangphancongxebus;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;


public class Tuyen {
    private static final AtomicInteger maTuyenCounter = new AtomicInteger(100);
    private int maTuyen;
    private int khoangCach;
    private int soDiemDung;

    public Tuyen(int khoangCach, int soDiemDung) {
        this.maTuyen = maTuyenCounter.getAndIncrement();
        this.khoangCach = khoangCach;
        this.soDiemDung = soDiemDung;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public int getKhoangCach() {
        return khoangCach;
    }

    @Override
    public String toString() {
        return maTuyen + " - " + khoangCach + " km - " + soDiemDung + " điểm dừng";
    }
}
