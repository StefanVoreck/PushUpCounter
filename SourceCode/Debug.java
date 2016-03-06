import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Debug{
	public static void main(String...args) throws IOException{
		Member m;
		try{
			FileInputStream fis = new FileInputStream("stiffepiff.bin");
			ObjectInputStream ois = new ObjectInputStream(fis);
			m = (Member) ois.readObject();
			fis.close();
			ois.close();
		}catch(Exception e){
			throw new IOException("Import failed.");
		}
		
		System.out.println(m.getQuota());
	}
}
