public class Adjust{
	public static void main(String...args) throws Exception{
		Member m = (Member)(new ImExStream()).importObject("stiffepiff.bin");
		m.modifyCount(65, 35);
		(new ImExStream<Member>()).exportObject("stiffepiff.bin", m);
	}
}
