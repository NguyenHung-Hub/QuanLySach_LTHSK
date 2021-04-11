package quanLiSach;

import java.io.Serializable;
import java.util.ArrayList;

public class ListBook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2721168871051173254L;
	private ArrayList<Book> listBook;

	public ListBook() {
		listBook = new ArrayList<Book>();
	}

	public boolean addBook(Book book) {
		if (listBook.contains(book)) 
			return false;
		listBook.add(book);
		return true;
	}

	public boolean xoaSach(Object object) {
		return listBook.remove(object);
	}
	
	/**
	 * cập thông tin cho sách, không được thay đổi mã sách
	 * @param sachMoi
	 * @param ma
	 * @return
	 */
	public boolean updateBook(Book newBook, String id) {
		Book oldBook = new Book(id);

		if (listBook.contains(oldBook)) {
			oldBook = listBook.get(listBook.indexOf(oldBook));
			oldBook.setName(newBook.getName());
			oldBook.setPublishYear(newBook.getPublishYear());
			oldBook.setPageNumber(newBook.getPageNumber());
			oldBook.setISBN(newBook.getISBN());
			oldBook.setAuthor(newBook.getAuthor());
			oldBook.setPublisher(newBook.getPublisher());
			oldBook.setPrice(newBook.getPrice());
			return true;

		}

		return false;
	}
	/**
	 * tìm kiếm sách theo mã sách
	 * @param ma
	 * @return sach nếu tìm thấy ngược lại return null
	 */
	public Book searchBook(String id) {
		for (Book book : listBook) 
			if (book.getId().equals(id)) 
				return book;
		return null;
	}

	public int getSize() {
		return listBook.size();
	}

	public Book getElement(int index) {
		if (index < 0 || index > listBook.size()) 
			return null;
		return listBook.get(index);
	}
}
