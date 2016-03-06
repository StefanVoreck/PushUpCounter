import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Modifier{
	public static void main(String...args) throws IOException, Exception{
		// field
		Member m;
		
		// import
		try{
			FileInputStream fis = new FileInputStream("stiffepiff.bin");
			ObjectInputStream ois = new ObjectInputStream(fis);
			m = (Member) ois.readObject();
			fis.close();
			ois.close();
			System.out.println("Import success.");
		}catch(Exception e){
			throw new IOException("Import failed.");
		}
		
		// modify
		try{
			int[] arr = new int[366];
			arr[1] = 1810;
			m.setCountArray(arr);
		}catch(Exception e){
			throw new Exception("Modify failed.");
		}
		// export
		try{
			FileOutputStream fos = new FileOutputStream("stiffepiff.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(m);
			fos.close();
			oos.close();
			System.out.println("Export success.");
		}catch(Exception e){
			throw new IOException("Export failed.");
		}
	}
}
