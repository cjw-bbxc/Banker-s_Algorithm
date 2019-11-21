import java.util.Scanner;

public class TheBanker {
	int m;  //进程个数
	int n; //每个进程的资源个数
	int[][] max;  //最大需求矩阵
	int[][] allocation;  // 以分配的资源（已占有的资源）
	int[][] need;   // 需求的资源 
	int[] available;   // 可利用的资源
	int[] p;   // 记录安全序列
	boolean[] finish; // 标志一个进程是否完成？true 表示完成 false 表示未完成
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
	
	//显示列表
     public void print() {
    	 fwi.WriteTextLn("------------------------------------------");
    	 fwi.WriteTextLn("系统中的【进程数】:	"+m);
    	 fwi.WriteTextLn("进程的【资源类型数】:	"+n);
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
	
	//-------------------检验系统是否处于安全性状态--------------------------
	public boolean Security_check() {
		int[] work = new int[n];
		for (int i=0;i<n;i++) {
			work[i] = available[i];// 把available的值赋给work
		}
		finish = new boolean[m];
		for (int i = 0; i < m; i++) {// 开始把进程全部置未分配状态 都为false；
			finish[i] = false;
		}

		int num = 0;// 对每个进程都要把所有资源都进行比较
		int num1 = 0;
		int count = 0;// 记录可以分配的序列
		int count1 = 0;// 记录所有序列是否分配
		p = new int[m];// 找到安全序列
		
		while (num1<m) {
			for (int i=0;i<m;i++) {
				if (finish[i] == false) {// 判断finish的状态，如果为true说明刚才已经找到，不需要重复。
					for (int j=0;j<n;j++) {
						if (need[i][j] <= work[j]) {// 比较一个进程的各种资源是否满足条件
							num++;
						}
					}
					if (num == n) {// 如果一个进程所有资源都满足条件need<work,则找到了一个进程满足
						for (int k=0;k<n;k++) {
							work[k] = work[k] + allocation[i][k];
						}
						finish[i] = true;// 找到一个进程满足
						p[count++] = i;// 记录找到的是第几个进程
					}
				}
				num = 0;// 必须把它清零，重新来找下个资源种类的每种是否都满足条件
			}
			num1++;
		}

		// 记录有多少个序列；
		for (int i=0;i<m;i++) {
			if (finish[i] == true) {
				count1++;// 检测是否所有的进程最后都是true,
			}
		}
		if (count1 == m) {// 如果序列里面总数等于总共有多少程序，就找到了安全的序列。并且输出。反之没有找到
			fwi.WriteTextLn("存在一个安全序列，安全序列为:");
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
			fwi.WriteTextLn( "没有找到一个安全序列，系统处于不安全状态！");
			return false;
		}
	}

	public void checkRequest(int x,int[] y) {
		int process = 0;// 记录输入的是第几个进程
		int count2 = 0;// 记录试分配过程中满足条件的个数
		boolean flag = true;// 主要防止输入的数字已经超出了本来process数量，则要求重新输入
		fwi.WriteTextLn("请输入要申请的第几个进程，注意进程p下标是从0开始的");
		while (flag) {
			fwi.WriteTextLn("系统自动输入为：" + x);
			process = x;
			if (process > m) {
				flag = true;
				fwi.WriteTextLn("输入超出了本来进程的范围，请重新输入！");
			} else {
				flag = false;

			}
		}
		fwi.WriteTextLn("第"+process+"个进程提出请求");;
		int[] request = new int[n];
		fwi.WriteTextLn("输入要请求的资源Request:");
		for (int i=0;i<n;i++) {
			fwi.WriteTextLn("系统自动输入为：" + y[i]);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
			request[i] = y[i];
		}
		// 判断是否可以分配
		for (int i=0;i<n;i++) {
			if (request[i] <= need[process-1][i] && request[i] <= available[i]) {
				count2++;// 判断是否每个进程的所有资源都满足试分配的要求，并记录。
			}
		}

		if (count2 == n) {// 如果每一种资源都满足要求，则可以进程请求试分配
			for (int j=0;j<n;j++) {
				allocation[process-1][j] += request[j];  //注意数组下标是从0开始的
				need[process-1][j] -= request[j];
				available[j] -= request[j];
			}
			fwi.WriteTextLn("试分配如下-------->");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
			print();// 打印试分配的结果
			fwi.WriteTextLn("进行安全性判断");
			flag = Security_check();// 判断是否为安全序列
			if (flag==false) {  //如果是分配后不能找到一个安全序列，则返回，不进行分配
				for (int j=0;j<n;j++) {
					allocation[process-1][j] -= request[j];  //注意数组下标是从0开始的
					need[process-1][j] += request[j];
					available[j] += request[j];
				}
			}
		} else {
			fwi.WriteTextLn("不能进行试分配,也就找不到安全序列");
		}
	}
}