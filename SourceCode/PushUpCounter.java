import javax.swing.SwingUtilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

// Opens a thread to login frame.
public class PushUpCounter{
	public static void main(String...args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new LoginFrame();
			}
		});
	}
}

// Class for importing och exporting members.
class IOStream{
	public static Member importMember(String name) throws IOException{
		try{
			FileInputStream fis = new FileInputStream(name + ".bin");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Member member = (Member) ois.readObject();
			fis.close();
			ois.close();
			System.out.printf("Successfully imported member \"%s\" from \"%s.bin\".\n", name, name);
			return member;
		}catch(Exception e){
			throw new IOException("Error while importing " + name + ".");
		}
	}
	
	public static void exportMember(Member member) throws IOException{
		try{	
			// Exports member object
			FileOutputStream fos = new FileOutputStream(member.toString() + ".bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(member);
			
			// Exports count array, for debug and backup
			fos = new FileOutputStream(member.toString() + "array" + ".array");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(member.getCountArray());
			
			fos.close();
			oos.close();
			System.out.printf("Successfully exported member \"%s\" to \"%s.bin\".\n", member.toString(), member.toString());
		}catch(Exception e){
			throw new IOException("Error while exporting " + member.toString() + ".");
		}
	}
}


