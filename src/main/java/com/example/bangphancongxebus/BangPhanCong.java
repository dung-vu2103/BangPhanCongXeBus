package com.example.bangphancongxebus;



public class BangPhanCong {
    private LaiXe laiXe;
    private Tuyen tuyen;
    private int soLuot;

    public BangPhanCong(LaiXe laiXe, Tuyen tuyen, int soLuot) {
        this.laiXe = laiXe;
        this.tuyen = tuyen;
        this.soLuot = soLuot;
    }

    public LaiXe getLaiXe() {
        return laiXe;
    }

    public Tuyen getTuyen() {
        return tuyen;
    }

    public int getSoLuot() {
        return soLuot;
    }

    @Override
    public String toString() {
        return laiXe.getHoTen() + " - " + tuyen.getMaTuyen() + " - " + soLuot;
    }
}
