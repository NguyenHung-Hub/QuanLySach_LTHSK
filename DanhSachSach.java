package quanLiSach;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachSach implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2721168871051173254L;
	private ArrayList<Sach> dsSach;

	public DanhSachSach() {
		dsSach = new ArrayList<Sach>();
	}

	public boolean themSach(Sach sach) {
		if (dsSach.contains(sach)) {
			return false;
		}
		dsSach.add(sach);
		return true;
	}

	public boolean xoaSach(Object object) {
		return dsSach.remove(object);
	}
	
	/**
	 * cập thông tin cho sách, không được thay đổi mã sách
	 * @param sachMoi
	 * @param ma
	 * @return
	 */
	public boolean capNhatSach(Sach sachMoi, String ma) {
		Sach sachCu = new Sach(ma);

		if (dsSach.contains(sachCu)) {
			sachCu = dsSach.get(dsSach.indexOf(sachCu));
			sachCu.setName(sachMoi.getName());
			sachCu.setPublishYear(sachMoi.getPublishYear());
			sachCu.setPageNumber(sachMoi.getPageNumber());
			sachCu.setISBN(sachMoi.getISBN());
			sachCu.setAuthor(sachMoi.getAuthor());
			sachCu.setPublisher(sachMoi.getPublisher());
			sachCu.setPrice(sachMoi.getPrice());
			return true;

		}

		return false;
	}
	/**
	 * tìm kiếm sách theo mã sách
	 * @param ma
	 * @return sach nếu tìm thấy ngược lại return null
	 */
	public Sach timKiemSach(String ma) {
		for (Sach sach : dsSach) {
			if (sach.getId().equals(ma)) {
				return sach;
			}
		}
		return null;
	}

	public int getSize() {
		return dsSach.size();
	}

	public Sach getElement(int viTri) {
		if (viTri < 0 || viTri > dsSach.size()) {
			return null;
		}
		return dsSach.get(viTri);
	}
}
