package main.liuzhen;

import java.math.BigDecimal;

/**
 * @author Liuzhen
 * @class: Money
 * @description: 本金利息bean
 * @create 2017-1-22 15:48
 */
public class Money {
	
	private BigDecimal capital;		// 本金
	private BigDecimal interest;	// 利息
	
	public Money() {
	}
	
	public Money(BigDecimal capital, BigDecimal interest) {
		this.capital = capital;
		this.interest = interest;
	}
	
	public BigDecimal getCapital() {
		return capital;
	}
	
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	
	public BigDecimal getInterest() {
		return interest;
	}
	
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
}
