package com.example.bangphancongxebus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BangphancongxebusApplication {
	private static List<LaiXe> danhSachLaiXe = new ArrayList<>();
	private static List<Tuyen> danhSachTuyen = new ArrayList<>();
	private static List<BangPhanCong> danhSachBangPhanCong = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(BangphancongxebusApplication.class, args);

		int choice;
		do {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1:
					nhapDanhSachLaiXe();
					inDanhSachLaiXe();

					break;
				case 2:
					nhapDanhSachTuyen();
					inDanhSachTuyen();

					break;
				case 3:
					nhapDanhSachPhanCong();
					inDanhSachPhanCong();

					break;
				case 4:
					sapXepPhanCong();
					break;
				case 5:
					tinhTongKhoangCach();
					break;
				case 6:
					System.out.println("Thoát chương trình.");
					break;
				default:
					System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
			}
		} while (choice != 6);
	}

	private static void showMenu() {
		System.out.println("----- MENU -----");
		System.out.println("1. Nhập danh sách Lái xe mới và in ra danh sách lái xe sau khi nhập.");
		System.out.println("2. Nhập danh sách Tuyến mới và in ra danh sách các tuyến sau khi nhập.");
		System.out.println("3. Nhập danh sách phân công cho mỗi lái xe và in danh sách ra màn hình.");
		System.out.println("4. Sắp xếp danh sách phân công.");
		System.out.println("5. Lập bảng kê tổng khoảng cách chạy xe trong ngày của mỗi lái xe.");
		System.out.println("6. Thoát.");
		System.out.print("Vui lòng chọn: ");
	}

	private static void nhapDanhSachLaiXe() {
		System.out.print("Nhập số lượng lái xe cần thêm: ");
		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			System.out.print("Nhập họ tên: ");
			String hoTen = sc.nextLine();
			System.out.print("Nhập địa chỉ: ");
			String diaChi = sc.nextLine();
			System.out.print("Nhập số điện thoại: ");
			String sdt = sc.nextLine();
			System.out.print("Nhập trình độ lái xe: ");
			String trinhDo = sc.nextLine();
			danhSachLaiXe.add(new LaiXe(hoTen, diaChi, sdt, trinhDo));
		}
	}

	private static void inDanhSachLaiXe() {
		System.out.println("Danh sách lái xe hiện có:");
		for (LaiXe laiXe : danhSachLaiXe) {
			System.out.println(laiXe);
		}
	}

	private static void nhapDanhSachTuyen() {
		System.out.print("Nhập số lượng tuyến cần thêm: ");
		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			System.out.print("Nhập khoảng cách (km): ");
			int khoangCach = sc.nextInt();
			sc.nextLine();
			System.out.print("Nhập số điểm dừng: ");
			int soDiemDung = sc.nextInt();
			sc.nextLine();
			danhSachTuyen.add(new Tuyen(khoangCach, soDiemDung));
		}
	}

	private static void inDanhSachTuyen() {
		System.out.println("Danh sách tuyến hiện có:");
		for (Tuyen tuyen : danhSachTuyen) {
			System.out.println(tuyen);
		}
	}

	private static void nhapDanhSachPhanCong() {
		System.out.print("Nhập mã lái xe: ");
		int maLX = sc.nextInt();
		sc.nextLine();
		LaiXe laiXe = danhSachLaiXe.stream()
				.filter(lx -> lx.getMaLX() == maLX)
				.findFirst()
				.orElse(null);

		if (laiXe == null) {
			System.out.println("Mã lái xe không tồn tại.");
			return;
		}

		System.out.print("Nhập mã tuyến: ");
		int maTuyen = sc.nextInt();
		sc.nextLine();
		Tuyen tuyen = danhSachTuyen.stream()
				.filter(t -> t.getMaTuyen() == maTuyen)
				.findFirst()
				.orElse(null);

		if (tuyen == null) {
			System.out.println("Mã tuyến không tồn tại.");
			return;
		}

		System.out.print("Nhập số lượt: ");
		int soLuot = sc.nextInt();
		sc.nextLine();
		if (soLuot > 15) {
			System.out.println("Số lượt lái không được vượt quá 15.");
			return;
		}

		int tongSoLuot = danhSachBangPhanCong.stream()
				.filter(pc -> pc.getLaiXe().getMaLX() == maLX)
				.mapToInt(BangPhanCong::getSoLuot)
				.sum();

		if (tongSoLuot + soLuot > 15) {
			System.out.println("Tổng số lượt phân công không được vượt quá 15.");
			return;
		}

		danhSachBangPhanCong.add(new BangPhanCong(laiXe, tuyen, soLuot));
	}

	private static void inDanhSachPhanCong() {
		System.out.println("Danh sách phân công hiện có:");
		for (BangPhanCong phanCong : danhSachBangPhanCong) {
			System.out.println(phanCong);
		}
	}
	private static void sapXepPhanCong() {
		danhSachBangPhanCong.sort((pc1, pc2) -> {
			int compareLaiXe = pc1.getLaiXe().getHoTen().compareTo(pc2.getLaiXe().getHoTen());
			if (compareLaiXe != 0) return compareLaiXe;
			return pc1.getTuyen().getMaTuyen() - pc2.getTuyen().getMaTuyen();
		});
		inDanhSachPhanCong();
	}

	private static void tinhTongKhoangCach() {
		System.out.println("Bảng kê tổng khoảng cách chạy xe trong ngày của mỗi lái xe:");
		danhSachLaiXe.forEach(laiXe -> {
			int tongKhoangCach = danhSachBangPhanCong.stream()
					.filter(pc -> pc.getLaiXe().getMaLX() == laiXe.getMaLX())
					.mapToInt(pc -> pc.getTuyen().getKhoangCach() * pc.getSoLuot())
					.sum();
			System.out.println(laiXe.getHoTen() + ": " + tongKhoangCach + " km");
		});
	}
}
