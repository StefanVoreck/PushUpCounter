import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("unchecked")
public class ImExStream<E>{
	public E importObject(String fileName) throws java.io.IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		E e = (E)ois.readObject();
		fis.close();
		ois.close();
		return e;
	}
	
	public void exportObject(String fileName, E e) throws java.io.IOException, ClassNotFoundException{
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(e);
		fos.close();
		oos.close();
	}
}
