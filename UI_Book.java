package quanLiSach;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UI_Book extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtPublishYear;
	private JTextField txtPageNumber;
	private JTextField txtISBN;
	private JTextField txtAuthor;
	private JTextField txtPublisher;
	private JTextField txtPrice;
	private JButton btnAdd;
	private JButton btnClear;
	private JButton btnEdit;
	private JButton btnRemove;

	JPanel buttonJPanel;
	private String[] titleString = { "Mã sách", "Tựa sách", "Năm xuất bản", "Số trang", "ISBN", "Tác giả",
			"Nhà xuất bản", "Đơn giá" };
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> searchComboBox;

	private ListBook listBook;
	private static final String FILENAME = "data/listBook.txt";
	private boolean storageStatus = true;

	public UI_Book() {
		setTitle("Quản lí sách");
		setSize(1000, 650);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				if (storageStatus == false) {
					int x = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu trước khi thoát?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if (x == JOptionPane.YES_OPTION) {
						LuuVaoFile();
						System.exit(0);
					} else {
						System.exit(0);
					}
				} else {
					System.exit(0);
				}
			}
		});
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		listBook = new ListBook();

		tableModel = new DefaultTableModel(titleString, 0);
		table = new JTable(tableModel);
		table.setRowHeight(40);
		table.addMouseListener(this);

		/* Begin: inputPanel */
		JPanel inputJPanel = new JPanel();
		inputJPanel.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		inputJPanel.setPreferredSize(new Dimension(getWidth(), 180));

		// left
		JPanel leftInputJPanel = new JPanel();
		leftInputJPanel.setPreferredSize(new Dimension(460, 150));

		Box leftBox = Box.createVerticalBox();

		// Mã sách
		Box bookIDBox = Box.createHorizontalBox();
		bookIDBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 100));
		JLabel iDJLabel = new JLabel("Mã sách:");
		iDJLabel.setPreferredSize(new Dimension(85, bookIDBox.getHeight()));
		bookIDBox.add(iDJLabel);
		txtID = new JTextField(20);
		bookIDBox.add(txtID);
		leftBox.add(bookIDBox);

		Box nameBox = Box.createHorizontalBox();
		nameBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		JLabel nameLabel = new JLabel("Tựa sách:");
		nameLabel.setPreferredSize(new Dimension(85, bookIDBox.getHeight()));
		nameBox.add(nameLabel);
		txtName = new JTextField(20);
		nameBox.add(txtName);
		leftBox.add(nameBox);

		Box publishYearBox = Box.createHorizontalBox();
		publishYearBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		JLabel publishYearLabel = new JLabel("Năm xuất bản:");
		publishYearLabel.setPreferredSize(new Dimension(85, bookIDBox.getHeight()));
		publishYearBox.add(publishYearLabel);
		txtPublishYear = new JTextField(20);
		publishYearBox.add(txtPublishYear);
		leftBox.add(publishYearBox);

		Box pageNumberBox = Box.createHorizontalBox();
		pageNumberBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		JLabel pageNumberLabel = new JLabel("Số trang:");
		pageNumberLabel.setPreferredSize(new Dimension(85, bookIDBox.getHeight()));
		pageNumberBox.add(pageNumberLabel);
		txtPageNumber = new JTextField(20);
		pageNumberBox.add(txtPageNumber);
		leftBox.add(pageNumberBox);

		Box ISBNBox = Box.createHorizontalBox();
		ISBNBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		ISBNBox.add(new JLabel("International Standard Book Number (ISBN): "));
		txtISBN = new JTextField(20);
		ISBNBox.add(txtISBN);
		leftBox.add(ISBNBox);

		leftInputJPanel.add(leftBox);
		inputJPanel.add(leftInputJPanel);

		// right
		JPanel rightInputJPanel = new JPanel();
		rightInputJPanel.setPreferredSize(new Dimension(460, 150));

		Box rightBox = Box.createVerticalBox();

		Box authorBox = Box.createHorizontalBox();
		authorBox.setBorder(BorderFactory.createEmptyBorder(25, 0, 5, 0));
		JLabel authorJLabel = new JLabel("Tác giả:");
		authorJLabel.setPreferredSize(new Dimension(85, bookIDBox.getHeight()));
		authorBox.add(authorJLabel);
		txtAuthor = new JTextField(20);
		authorBox.add(txtAuthor);
		rightBox.add(authorBox);

		Box publisherBox = Box.createHorizontalBox();
		publisherBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		JLabel publisherJLabel = new JLabel("Nhà xuất bản:");
		publisherJLabel.setPreferredSize(new Dimension(85, bookIDBox.getHeight()));
		publisherBox.add(publisherJLabel);
		txtPublisher = new JTextField(20);
		publisherBox.add(txtPublisher);
		rightBox.add(publisherBox);

		Box priceBox = Box.createHorizontalBox();
		priceBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		JLabel priceJLabel = new JLabel("Đơn giá:");
		priceJLabel.setPreferredSize(new Dimension(85, bookIDBox.getHeight()));
		priceBox.add(priceJLabel);
		txtPrice = new JTextField(30);
		txtPrice.addActionListener(this);
		priceBox.add(txtPrice);
		rightBox.add(priceBox);

		rightInputJPanel.add(rightBox);
		inputJPanel.add(rightInputJPanel);

		this.add(inputJPanel, BorderLayout.NORTH);
		/* End: inputPanel */

		JPanel centerJPanel = new JPanel(new BorderLayout());

		/* Begin: buttonPanel */
		buttonJPanel = new JPanel();
		buttonJPanel.setPreferredSize(new Dimension(getWidth(), 50));

		btnAdd = new JButton("Thêm");
		btnClear = new JButton("Xóa rỗng");
		btnEdit = new JButton("Sửa");
		btnEdit.setEnabled(false);
		btnRemove = new JButton("Xóa");
		btnRemove.setEnabled(false);

		buttonJPanel.add(btnAdd);
		buttonJPanel.add(btnClear);
		buttonJPanel.add(btnEdit);
		buttonJPanel.add(btnRemove);

		btnAdd.addActionListener(this);
		btnClear.addActionListener(this);
		btnEdit.addActionListener(this);
		btnRemove.addActionListener(this);

		JLabel timKiemJLabel = new JLabel("Tìm theo mã sách:");
		timKiemJLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		buttonJPanel.add(timKiemJLabel);

		searchComboBox = new JComboBox<String>();
		searchComboBox.addItem("");
		searchComboBox.addActionListener(this);
		searchComboBox.setEditable(true);

		buttonJPanel.add(searchComboBox);

		centerJPanel.add(buttonJPanel, BorderLayout.NORTH);
		/* End: buttonPanel */

		/* Begin: viewPanel */
		JPanel viewJPanel = new JPanel(new BorderLayout());
		viewJPanel.setBorder(BorderFactory.createTitledBorder("Danh sách cuốn sách"));

		viewJPanel.add(new JScrollPane(table), BorderLayout.CENTER);

		centerJPanel.add(viewJPanel, BorderLayout.CENTER);
		/* End: viewPanel */
		this.add(centerJPanel, BorderLayout.CENTER);

		Storage storage = new Storage();

		try {
			listBook = (ListBook) storage.ReadFile(FILENAME);
			for (int i = 0; i < listBook.getSize(); i++) {
				Book book = listBook.getElement(i);
				
				DecimalFormat df=new DecimalFormat("#,##0");
				tableModel.addRow(new Object[] { book.getId(), book.getName(), book.getPublishYear(), book.getPageNumber(),
						book.getISBN(), book.getAuthor(), book.getPublisher(), df.format(book.getPrice()) });
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < listBook.getSize(); i++) {
			Book s = listBook.getElement(i);
			searchComboBox.addItem(s.getId());
		}

	}

	public static void main(String[] args) {
		new UI_Book().setVisible(true);
	}

	/*
	 * Kiểm tra xem các textField đã được nhập đầy đủ hay chưa
	 */
	public boolean isEmptyTextField() {

		if (txtID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã.");
			txtID.requestFocus();
			return true;
		}
		if (txtName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tựa sách.");
			txtName.requestFocus();
			return true;
		}
		if (txtPublishYear.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập năm xuất bản.");
			txtPublishYear.requestFocus();
			return true;
		}
		if (txtPageNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập số trang.");
			txtPageNumber.requestFocus();
			return true;
		}
		if (txtISBN.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập ISBN.");
			txtISBN.requestFocus();
			return true;
		}
		if (txtAuthor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên tác giả.");
			txtAuthor.requestFocus();
			return true;
		}
		if (txtPublisher.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập nhà xuất bản.");
			txtPublisher.requestFocus();
			return true;
		}
		if (txtPrice.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập đơn giá.");
			txtPrice.requestFocus();
			return true;
		}

		return false;
	}

	public boolean checkNumber() {

		try {
			int year = Integer.parseInt(txtPublishYear.getText());
			if (year < 1900 || year > 2100) {
				JOptionPane.showMessageDialog(this, "Nhập năm xuất bản không đúng.");
				txtPublishYear.selectAll();
				txtPublishYear.requestFocus();
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Nhập năm xuất bản không đúng.");
			txtPublishYear.selectAll();
			txtPublishYear.requestFocus();
			return false;
		}

		try {
			int pageNumber = Integer.parseInt(txtPageNumber.getText());
			if (pageNumber < 1) {
				JOptionPane.showMessageDialog(this, "Số trang phải lớn hơn 0.");
				txtPageNumber.selectAll();
				txtPageNumber.requestFocus();
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Nhập số trang không đúng.");
			txtPageNumber.selectAll();
			txtPageNumber.requestFocus();
			return false;
		}

		try {
			double price = Double.parseDouble(txtPrice.getText());
			if (price < 1) {
				JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0.");
				txtPrice.selectAll();
				txtPrice.requestFocus();
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Nhập đơn giá không đúng.");
			txtPrice.selectAll();
			txtPrice.requestFocus();
			return false;
		}

		return true;
	}

	public void LuuVaoFile() {
		Storage luuTru = new Storage();

		try {
			luuTru.SaveFile(listBook, FILENAME);
			JOptionPane.showMessageDialog(this, "Lưu thành công.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Lưu không thành công.");
			e.printStackTrace();
		}
	}

	public void Focus() {
		txtID.selectAll();
		txtName.selectAll();
		txtPublishYear.selectAll();
		txtPageNumber.selectAll();
		txtISBN.selectAll();
		txtAuthor.selectAll();
		txtPublisher.selectAll();
		txtPrice.selectAll();
		txtID.requestFocus();
	}
	
	public void ClearTextField() {
		txtID.setText("");
		txtName.setText("");
		txtPublishYear.setText("");
		txtPageNumber.setText("");
		txtISBN.setText("");
		txtAuthor.setText("");
		txtPublisher.setText("");
		txtPrice.setText("");
		
		txtID.setEditable(true);
		txtID.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();

		// thêm
		if (object.equals(btnAdd) || object.equals(txtPrice)) {
			if (isEmptyTextField() == false) {
				if (checkNumber() == false) {
					return;
				}
			} else
				return;

			int publishYear = Integer.parseInt(txtPublishYear.getText().trim());
			int pageNumber = Integer.parseInt(txtPageNumber.getText().trim());
			double price = Double.parseDouble(txtPrice.getText().trim());

			Book book = new Book(txtID.getText().trim(), txtName.getText().trim(), publishYear, pageNumber,
					txtISBN.getText().trim(), txtAuthor.getText().trim(), txtPublisher.getText().trim(), price);

			if (!listBook.addBook(book)) {
				JOptionPane.showMessageDialog(this, "Thêm thất bại. Có thể trùng mã sách.");
				txtID.selectAll();
				txtID.requestFocus();
				return;
			}
			
			tableModel.addRow(new Object[] { txtID.getText(), txtName.getText(), txtPublishYear.getText(),
					txtPageNumber.getText(), txtISBN.getText(), txtAuthor.getText(), txtPublisher.getText(),
					txtPrice.getText()});

			Focus();
			storageStatus = false;
		}

		// xóa rỗng
		if (object.equals(btnClear)) {
			ClearTextField();
		}

		// sửa
		if (object.equals(btnEdit)) {
			int publishYear = Integer.parseInt(txtPublishYear.getText().trim());
			int pageNumber = Integer.parseInt(txtPageNumber.getText().trim());
			double price = Double.parseDouble(txtPrice.getText().trim());
			
			Book sach = new Book(txtID.getText().trim(), txtName.getText().trim(), publishYear, pageNumber,
					txtISBN.getText().trim(), txtAuthor.getText().trim(), txtPublisher.getText().trim(), price);
			boolean x = listBook.updateBook(sach, (String) table.getValueAt(table.getSelectedRow(), 0));
			if (x == false) {
				JOptionPane.showMessageDialog(this, "Không sửa được.");
				return;
			}
			table.setValueAt(txtName.getText(), table.getSelectedRow(), 1);
			table.setValueAt(txtPublishYear.getText(), table.getSelectedRow(), 2);
			table.setValueAt(txtPageNumber.getText(), table.getSelectedRow(), 3);
			table.setValueAt(txtISBN.getText(), table.getSelectedRow(), 4);
			table.setValueAt(txtAuthor.getText(), table.getSelectedRow(), 5);
			table.setValueAt(txtPublisher.getText(), table.getSelectedRow(), 6);
			table.setValueAt(txtPrice.getText(), table.getSelectedRow(), 7);

			Focus();
			storageStatus = false;
		}

		// xóa
		if (object.equals(btnRemove)) {
			int x = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);

			if (x == JOptionPane.YES_OPTION) {
				Book book = listBook.getElement(table.getSelectedRow());
				if (listBook.xoaSach(book)) {
					searchComboBox.removeItem(book.getId()); //xóa mã trong ô tìm kiếm
					
					tableModel.removeRow(table.getSelectedRow()); //xóa dòng đang chọn
					storageStatus = false;
					ClearTextField();
					
				} else {
					JOptionPane.showMessageDialog(this, "Xóa lỗi.");
				}
			}
		}

		// tìm kiếm
		String idSearch = (String) searchComboBox.getSelectedItem();

		if (!idSearch.equals("")) {
			Book b = listBook.searchBook(idSearch);

			if (b == null) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã sách này.");
				return;
			}

			DecimalFormat df = new DecimalFormat("#,##0");
			String string = String.format(
					" Mã: %s\n Tựa sách: %s\n Năm xuất bản: %d\n Số trang: %d\n ISBN: %s\n Tác giả: %s\n Nhà xuất bản: %s\n Đơn giá: %s",
					b.getId(), b.getName(), b.getPublishYear(), b.getPageNumber(), b.getISBN(), b.getAuthor(),
					b.getPublisher(), df.format(b.getPrice()));

			JOptionPane.showMessageDialog(this, string);
			searchComboBox.setSelectedIndex(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		btnEdit.setEnabled(true);
		btnRemove.setEnabled(true);

		int row = table.getSelectedRow();

		txtID.setText(table.getValueAt(row, 0).toString());
		txtID.setEditable(false);
		txtName.setText(table.getValueAt(row, 1).toString());
		txtPublishYear.setText(table.getValueAt(row, 2).toString());
		txtPageNumber.setText(table.getValueAt(row, 3).toString());
		txtISBN.setText(table.getValueAt(row, 4).toString());
		txtAuthor.setText(table.getValueAt(row, 5).toString());
		txtPublisher.setText(table.getValueAt(row, 6).toString());
		txtPrice.setText(table.getValueAt(row, 7).toString());
		txtName.requestFocus();
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}

}
