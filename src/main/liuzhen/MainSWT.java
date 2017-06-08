package main.liuzhen;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @class: MainSWT
 * @description: 
 * @author Liuzhen
 */
public class MainSWT {
	
	private static Display display;		// 显示器
	private static Shell shell;			// 壳(程序的窗口)
	private static Text contractText;	// 合同额显示文字
	private static Text monthRateText;	// 月利率显示文字
	private static Text periodsText;	// 期数显示文字
	private static Text resultText;		// 结果文字
	private static Text contractAmt;	// 合同额
	private static Text monthRate;		// 月利率
	private static Text periods;		// 期数
	private static Button calcBtn;		// 计算按钮
	
	public MainSWT() {
		display = new Display();
		// 基本对话框
		shell = new Shell(display, SWT.DIALOG_TRIM);
		shell.setText("Note pad");		// 标题
		shell.setSize(600,400);			// 尺寸
		
		contractText = new Text(shell, SWT.READ_ONLY);
		contractText.setText("合同额:");
		contractText.setLocation(10, 10);
		contractText.setSize(60, 20);
		contractText.setEditable(false);
		
		monthRateText = new Text(shell, SWT.READ_ONLY);
		monthRateText.setText("月利率:");
		monthRateText.setLocation(10, 40);
		monthRateText.setSize(60, 20);
		monthRateText.setEditable(false);
		
		periodsText = new Text(shell, SWT.READ_ONLY);
		periodsText.setText("期   数:");
		periodsText.setLocation(10, 70);
		periodsText.setSize(60, 20);
		periodsText.setEditable(false);
		
		resultText = new Text(shell, SWT.READ_ONLY|SWT.MULTI|SWT.V_SCROLL);
		resultText.setText("");
		resultText.setSize(560, 200);
		resultText.setLocation(10, 130);
		resultText.setEditable(false);
		
		contractAmt = new Text(shell,SWT.BORDER);
		contractAmt.setText("70000");
		contractAmt.setLocation(100,10);
		contractAmt.setSize(100, 20);
		
		monthRate = new Text(shell,SWT.BORDER);
		monthRate.setText("0.0214");
		monthRate.setLocation(100,40);
		monthRate.setSize(100, 20);
		
		periods = new Text(shell,SWT.BORDER);
		periods.setText("12");
		periods.setLocation(100,70);
		periods.setSize(100, 20);
		
		calcBtn = new Button(shell,SWT.TOGGLE);
		calcBtn.setLocation(10,100);
		calcBtn.setSize(50,20);
		calcBtn.setText("计算");
		calcBtn.addSelectionListener( new SelectionAdapter() {
			public void widgetSelected (SelectionEvent event) {
				String cStr = contractAmt.getText();
				BigDecimal contractAmtB = null;
				if (isNum(cStr)) {
					contractAmtB = new BigDecimal(cStr);
				} else {
					alertMsg(shell, "别闹！合同额居然不是数字！");
					return ;
				}
				
				String mStr = monthRate.getText();
				BigDecimal monthRateB = null;
				if (isNum(mStr)) {
					monthRateB = new BigDecimal(mStr);
				} else {
					alertMsg(shell, "别闹！月利率居然不是数字！");
					return ;
				}
				
				String pStr = periods.getText();
				int periodsI = 0;
				if (isInt(pStr)) {
					periodsI = Integer.parseInt(pStr);
				} else {
					alertMsg(shell, "别闹！期数居然不是整数！");
					return ;
				}
				
				// 输入获取完成，下面开始计算
				String out = "单期总额" + EqualCapitalAndInterest.getMonthTotal(contractAmtB, monthRateB, periodsI) + "\n";
				List<Money> list = EqualCapitalAndInterest.getList(contractAmtB, monthRateB, periodsI);
				for (int i = 0; i < list.size(); i++) {
					out += "第" + (i< 9 ? "0": "") +(i + 1) + "期——> " +  "本金:" + list.get(i).getCapital() + "	利息:" + list.get(i).getInterest() + "\n";
				}
				resultText.setText(out);
			}
		});
		
		
		// 开闭
		shell.open();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
		System.out.println("closed");
		display.dispose();
	}
	
	private void alertMsg(Shell shell,String msg) {
		MessageBox alertBox = new MessageBox(shell);
		alertBox.setMessage(msg);
		alertBox.setText("信息");
		alertBox.open();
	}
	
	/**
	 * @title:  isNum
	 * @description: 判断是否是数字
	 * @param s
	 * @return 
	 * @author: Liuzhen
	 * @date:   2017-3-29 11:58
	 */
	private boolean isNum(String s) {
		try {
			new BigDecimal(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @title:  isInt
	 * @description: 判断是否是int
	 * @param s
	 * @return
	 * @author: Liuzhen
	 * @date:   2017-3-29 11:59
	 */
	private boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		new MainSWT();
		System.exit(0);
	}
}
