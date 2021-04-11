package quanLiSach;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3783924031459896025L;
	private String id; //mã
	private String name; //tựa sách
	private int publishYear; //năm xuất bản
	private int pageNumber; //số trang
	private String ISBN;
	private String author; //tác giả
	private String publisher; //nhà xuất bản
	private double price; // đơn giá
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String id) {
		setId(id);
	}
	
	public Book(String id, String name, int publishYear, int pageNumber, String iSBN, String author, String publisher,
			double price) {
		setId(id);
		setName(name);
		setPublishYear(publishYear);
		setPageNumber(pageNumber);
		setISBN(iSBN);
		setAuthor(author);
		setPublisher(publisher);
		setPrice(price);
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
