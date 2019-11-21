import java.io.*;


public class FileWriterIO {
	
	PrintStream ps = null;
	
	public FileWriterIO(String filename) {
		try {
			ps = new PrintStream(filename);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void WriteTextLn(String s) {
		ps.println(s);
	}
	
	public void WriteText(String s) {
		ps.print(s);
	}
	
//	/**
//	 * 
//	 * @param args
//	 * @Title: main   
//	 * @Description: ≤‚ ‘Test
//	 */
//	public static void main(String[] args) {
//		FileWriterIO fwi = new FileWriterIO("Result.log");
//		fwi.WriteTextLn("ŒÃº—À≥ «÷Ì£°£°£°");
//	}
	
}
