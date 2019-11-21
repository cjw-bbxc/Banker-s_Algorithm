import java.util.Scanner;

public class TheBanker {
	int m;  //���̸���
	int n; //ÿ�����̵���Դ����
	int[][] max;  //����������
	int[][] allocation;  // �Է������Դ����ռ�е���Դ��
	int[][] need;   // �������Դ 
	int[] available;   // �����õ���Դ
	int[] p;   // ��¼��ȫ����
	boolean[] finish; // ��־һ�������Ƿ���ɣ�true ��ʾ��� false ��ʾδ���
	Scanner input = new Scanner(System.in);
	
	FileReaderIO fri = null;
	FileWriterIO fwi = null;
	
	
	public TheBanker() {
		fri = new FileReaderIO("Info.txt");
		fri.InitInfo();
		
		this.m = fri.m;
		this.n = fri.n;
		this.need = fri.need;
		this.max = fri.max;
		this.allocation = fri.allocation;
		this.available = fri.available;
		
		fwi = new FileWriterIO("Result.log");
		print();
	}
	
	//��ʾ�б�
     public void print() {
    	 fwi.WriteTextLn("------------------------------------------");
    	 fwi.WriteTextLn("ϵͳ�еġ���������:	"+m);
    	 fwi.WriteTextLn("���̵ġ���Դ��������:	"+n);
    	 fwi.WriteTextLn("------------------------------------------");
	     fwi.WriteTextLn("\tMax\tAllocation\tNeed\tAvalable");
	     fwi.WriteTextLn("\tA B C\tA B C\t\tA B C\tA B C");
	     for (int i=0;i<m;i++) {
		     fwi.WriteText("P("+i+"): ");
		     fwi.WriteText(" ");
			     for (int j=0;j<n;j++) {
			         fwi.WriteText(max[i][j]+" ");    
			     }
			     fwi.WriteText("\t");
			     for (int j=0;j<n;j++) {
			           fwi.WriteText(allocation[i][j]+" ");
			     }
			     fwi.WriteText("\t\t");
			     for (int j=0;j<n;j++) {
			           fwi.WriteText(need[i][j]+" ");
			     }
			     fwi.WriteText("\t");
			     if (i==0) {
			        for (int j=0;j<n;j++) {
			          fwi.WriteText(available[j]+" ");
			        }
			     }
			     fwi.WriteTextLn("");
	     }      
	     fwi.WriteTextLn("------------------------------------------");
     }
	
	//-------------------����ϵͳ�Ƿ��ڰ�ȫ��״̬--------------------------
	public boolean Security_check() {
		int[] work = new int[n];
		for (int i=0;i<n;i++) {
			work[i] = available[i];// ��available��ֵ����work
		}
		finish = new boolean[m];
		for (int i = 0; i < m; i++) {// ��ʼ�ѽ���ȫ����δ����״̬ ��Ϊfalse��
			finish[i] = false;
		}

		int num = 0;// ��ÿ�����̶�Ҫ��������Դ�����бȽ�
		int num1 = 0;
		int count = 0;// ��¼���Է��������
		int count1 = 0;// ��¼���������Ƿ����
		p = new int[m];// �ҵ���ȫ����
		
		while (num1<m) {
			for (int i=0;i<m;i++) {
				if (finish[i] == false) {// �ж�finish��״̬�����Ϊtrue˵���ղ��Ѿ��ҵ�������Ҫ�ظ���
					for (int j=0;j<n;j++) {
						if (need[i][j] <= work[j]) {// �Ƚ�һ�����̵ĸ�����Դ�Ƿ���������
							num++;
						}
					}
					if (num == n) {// ���һ������������Դ����������need<work,���ҵ���һ����������
						for (int k=0;k<n;k++) {
							work[k] = work[k] + allocation[i][k];
						}
						finish[i] = true;// �ҵ�һ����������
						p[count++] = i;// ��¼�ҵ����ǵڼ�������
					}
				}
				num = 0;// ����������㣬���������¸���Դ�����ÿ���Ƿ���������
			}
			num1++;
		}

		// ��¼�ж��ٸ����У�
		for (int i=0;i<m;i++) {
			if (finish[i] == true) {
				count1++;// ����Ƿ����еĽ��������true,
			}
		}
		if (count1 == m) {// ��������������������ܹ��ж��ٳ��򣬾��ҵ��˰�ȫ�����С������������֮û���ҵ�
			fwi.WriteTextLn("����һ����ȫ���У���ȫ����Ϊ:");
			for (int i=0;i<m;i++) {
				if (i != m-1) {
					fwi.WriteText("P"+p[i]+"-->");
				} else {
					fwi.WriteTextLn("P"+p[i]);
				}
			}
			fwi.WriteTextLn("----------------------------------------------------");
			return true;
		} else {
			fwi.WriteTextLn( "û���ҵ�һ����ȫ���У�ϵͳ���ڲ���ȫ״̬��");
			return false;
		}
	}

	public void checkRequest(int x,int[] y) {
		int process = 0;// ��¼������ǵڼ�������
		int count2 = 0;// ��¼�Է�����������������ĸ���
		boolean flag = true;// ��Ҫ��ֹ����������Ѿ������˱���process��������Ҫ����������
		fwi.WriteTextLn("������Ҫ����ĵڼ������̣�ע�����p�±��Ǵ�0��ʼ��");
		while (flag) {
			fwi.WriteTextLn("ϵͳ�Զ�����Ϊ��" + x);
			process = x;
			if (process > m) {
				flag = true;
				fwi.WriteTextLn("���볬���˱������̵ķ�Χ�����������룡");
			} else {
				flag = false;

			}
		}
		fwi.WriteTextLn("��"+process+"�������������");;
		int[] request = new int[n];
		fwi.WriteTextLn("����Ҫ�������ԴRequest:");
		for (int i=0;i<n;i++) {
			fwi.WriteTextLn("ϵͳ�Զ�����Ϊ��" + y[i]);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
			request[i] = y[i];
		}
		// �ж��Ƿ���Է���
		for (int i=0;i<n;i++) {
			if (request[i] <= need[process-1][i] && request[i] <= available[i]) {
				count2++;// �ж��Ƿ�ÿ�����̵�������Դ�������Է����Ҫ�󣬲���¼��
			}
		}

		if (count2 == n) {// ���ÿһ����Դ������Ҫ������Խ��������Է���
			for (int j=0;j<n;j++) {
				allocation[process-1][j] += request[j];  //ע�������±��Ǵ�0��ʼ��
				need[process-1][j] -= request[j];
				available[j] -= request[j];
			}
			fwi.WriteTextLn("�Է�������-------->");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
			print();// ��ӡ�Է���Ľ��
			fwi.WriteTextLn("���а�ȫ���ж�");
			flag = Security_check();// �ж��Ƿ�Ϊ��ȫ����
			if (flag==false) {  //����Ƿ�������ҵ�һ����ȫ���У��򷵻أ������з���
				for (int j=0;j<n;j++) {
					allocation[process-1][j] -= request[j];  //ע�������±��Ǵ�0��ʼ��
					need[process-1][j] += request[j];
					available[j] += request[j];
				}
			}
		} else {
			fwi.WriteTextLn("���ܽ����Է���,Ҳ���Ҳ�����ȫ����");
		}
	}
}