
public class TestBanker {

	public static void main(String[] args) {
		System.out.println("-----����ϵͳ���м��㷨-------");
		TheBanker tb = new TheBanker();
		String url = "demo.txt";
		FileReaderIO frt = new FileReaderIO(url);
		String[] s = frt.ReadText();
		String[] subchoose = s[0].split(" ");
		int filePointer = 0;
		boolean flag = true;
		while (flag) {
			System.out.println("1.�������⣬�����Ƿ�ȫ");
			System.out.println("2.�������");
			System.out.println("3.�˳���");
			System.out.println("----------------------------------------------------------");
			System.out.println("��ѡ��");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
			int num = Integer.parseInt(subchoose[filePointer++]);
			System.out.println("ϵͳѡ��Ϊ��" + num);
			switch (num) {
			case 1:
				tb.Security_check();
				flag = true;
				break;
			case 2:
				int x = Integer.parseInt(subchoose[filePointer++]);
				int a = Integer.parseInt(subchoose[filePointer++]);
				int b = Integer.parseInt(subchoose[filePointer++]);
				int c = Integer.parseInt(subchoose[filePointer++]);
				int requests[] = {a,b,c};
				tb.checkRequest(x,requests);//�������
				flag = true;
				break;
			case 3:
				System.out.println("ллʹ��,�ټ�!!!");
				flag = false;
				break;
			}
	  	}
	}
}