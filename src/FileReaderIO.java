import java.io.*;


public class FileReaderIO {
	
	BufferedReader bfread = null;
	
	int m;  //���̸���
	int n; //ÿ�����̵���Դ����
	int[][] max;  //����������
	int[][] allocation;  // �Է������Դ����ռ�е���Դ��
	int[][] need;   // �������Դ 
	int[] available;   // �����õ���Դ

	
	
	public FileReaderIO(String filename) {
		try {
			bfread = new BufferedReader(new FileReader(filename));
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public String[] ReadText() {
		String[] s = new String[100];
		int line = 1;
		try {
			while((s[line-1] = bfread.readLine())!=null) {
				line++;
			}
			bfread.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return s;
	}
	
	public void WriteText() {
		
	}
	
	
	public void InitInfo() {
		
		String[] s = ReadText();
		
		String[] ss = s[0].split(" ");
		
		/** ϵͳ�еġ���������*/
		this.m = Integer.parseInt(ss[0]);
		/** ���̵ġ���Դ��������*/
		this.n = Integer.parseInt(ss[1]);
		
		
		
		
		max = new int[m][n];  //����������
		allocation = new int[m][n];  // �Է������Դ����ռ�е���Դ��
		need = new int[m][n];   // �������Դ 
		available = new int[n];   // �����õ���Դ
		
		
		ss = s[1].split(" ");		
		this.max = Max(max,ss);
		ss = s[2].split(" ");
		this.allocation = Allocation(allocation,ss);
		
		for (int i=0;i<need.length;i++) {  //��������������������Դ��
		     for(int j=0;j<need[i].length;j++){
		        need[i][j] = max[i][j] - allocation[i][j];
		     }
	     }
		
		ss = s[3].split(" ");
		this.available = Available(available,ss);
	    System.out.println("��ʼ�����Ϊ�±�");
	    
	    /*stem.out.println("------------------------------------------");
	    System.out.println("\tMax\tAllocation\tNeed\tAvalable");
	    System.out.println("\tA B C\tA B C\t\tA B C\tA B C");
	    for (int i=0;i<m;i++) {
		    System.out.print("P("+i+"): ");
		    System.out.print(" ");
			    for (int j=0;j<n;j++) {
			        System.out.print(max[i][j]+" ");
			    }
			    System.out.print("\t");
			    for (int j=0;j<n;j++) {
			    	System.out.print(allocation[i][j]+" ");
			    }
			    System.out.print("\t\t");
			    for (int j=0;j<n;j++) {
			    	System.out.print(need[i][j]+" ");
			    }
			     System.out.print("\t");
			     if (i==0) {
			        for (int j=0;j<n;j++) {
			          System.out.print(available[j]+" ");
			        }
			     }
			     System.out.println();
	     }  
		*/
	}
	
	
	
	

	int[] Available(int[] available, String[] ss) {
		
		for (int i=0;i<available.length;i++) {
		     available[i] = Integer.parseInt(ss[i]);
	     }
		return available;
	}

	int[][] Allocation(int[][] allocation,String[] ss) {
		int k = 0;
		for (int i=0;i<allocation.length;i++) {  //����������̵ĸ���ռ����Դ��
//			System.out.println("�������p("+(i+1)+")�����е�Alloction��");
			for (int j=0;j<allocation[i].length;j++) {
				allocation[i][j] = Integer.parseInt(ss[k++]);
			}
		}
		return allocation;
	}

	int[][] Max(int[][] max,String[] ss) {
		int k = 0;
		for (int i=0;i<max.length;i++) {  //����������̵ĸ��������Դ��
//			System.out.println("�������p("+(i+1)+")���̵�Max��");
			for(int j=0;j<max[i].length;j++){
				max[i][j] = Integer.parseInt(ss[k++]);
			}
		}
		return max;
	}
	
	
	/**
	 * 
	 * @param args
	 * @Title: main   
	 * @Description: ���Ժ���
	 */
//	public static void main(String[] args) {
//		FileReaderText frt = new FileReaderText("Info.txt");
//		String[] s = frt.ReadText();
//		String[] ss = s[0].split(" ");
//		for (int i = 0; i < ss.length; i++) {
//			System.out.println(ss[i]+" ");
//		}
//	}
}
