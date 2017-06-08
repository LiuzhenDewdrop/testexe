package main.liuzhen;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liuzhen
 * @class: EqualCapitalAndInterest
 * @description: 等额本息
 * @create 2017-1-22 15:47
 */
public class EqualCapitalAndInterest {
	
	public static List<Money> getList(BigDecimal contractAmt, BigDecimal monthRate, int periods) {
		List<Money> resultList = new ArrayList<Money>(periods);
		// 月还款额
		BigDecimal monthTotal = getMonthTotal(contractAmt, monthRate, periods);
		for (int i = 0; i < periods - 1; i++) {
			BigDecimal interest = contractAmt.multiply(monthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal capital = monthTotal.subtract(interest);
			resultList.add(new Money(capital, interest));
			contractAmt = contractAmt.subtract(capital);
		}
		resultList.add(new Money(contractAmt, monthTotal.subtract(contractAmt)));
		return resultList;
	}
	
	public static Money getFirst(BigDecimal contractAmt, BigDecimal monthRate, int periods) {
		BigDecimal total = getMonthTotal(contractAmt, monthRate, periods);
		Money money = new Money();
		BigDecimal interest = contractAmt.multiply(monthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
		money.setInterest(interest);
		money.setCapital(total.subtract(interest));
		return money;
	}
	
	
	public static BigDecimal getMonthTotal(BigDecimal contractAmt, BigDecimal monthRate, int periods) {
		BigDecimal temp = new BigDecimal(1);
		for (int i = 0; i < periods; i++) {
			temp = temp.multiply(monthRate.add(new BigDecimal(1)));
		}
		return contractAmt.multiply(monthRate).multiply(temp).divide(temp.subtract(new BigDecimal(1)), 2, BigDecimal.ROUND_HALF_UP);
	}
	
//	public static void main(String[] args) {
//		BigDecimal contractAmt = new BigDecimal(70000);
//		BigDecimal monthRate = new BigDecimal(0.0214);
//		int periods = 12;
//		System.out.println("单期总额" + getMonthTotal(contractAmt, monthRate, periods));
////		Money m = getFirst(contractAmt, monthRate, periods);
////		System.out.println(m.getCapital());
////		System.out.println(m.getInterest());
//		List<Money> list = getList(contractAmt, monthRate, periods);
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("第" + (i + 1) + "期——> " + (i< 9 ? " ": "") + "本金:" + list.get(i).getCapital() + "	利息:" + list.get(i).getInterest());
//		}
//	}
}
