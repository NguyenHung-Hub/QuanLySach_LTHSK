package quanLiSach;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//public class LuuTru {
//	public boolean LuuFile(Object object, String filePath) throws IOException {
//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
//		objectOutputStream.writeObject(object);
//		objectOutputStream.flush();
//		objectOutputStream.close();
//		return true;
//	}
//	
//	public Object DocFile(String filePath) throws Exception {
//		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
//		Object object = objectInputStream.readObject();
//		objectInputStream.close();
//		return object;
//	}
//}
public class LuuTru{
	public boolean LuuFIle(Object object, String filePath) throws IOException {
		ObjectOutputStream objectOutputStream=null;
		objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		objectOutputStream.close();
		return true;
	}
	
	public Object DocFile(String filePath) throws ClassNotFoundException, IOException {
		ObjectInputStream objectInputStream =new ObjectInputStream(new FileInputStream(filePath));
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		return object;
	}
}
