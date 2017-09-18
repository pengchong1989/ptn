package com.nms.drive.service.bean;
/**
 * TMP EXP到PHB映射表(0AH)
 * @author 罗磊
 *
 */
public class EXPToPHBObject {
	private int expPHBID = 0;//EXP@PHB_ID  (1)-15 
	private int zero = 0;	//0    	(0)/1/2/3/4/5/6/7= (BE)/AF1/AF2/AF3/AF4/EF/CS6/CS7
	private int one = 0;	//1		0/(1)/2/3/4/5/6/7= BE/(AF1)/AF2/AF3/AF4/EF/CS6/CS7
	private int two = 0;	//2		0/1/(2)/3/4/5/6/7= BE/AF1/(AF2)/AF3/AF4/EF/CS6/CS7
	private int three = 0; 	//3		0/1/2/(3)/4/5/6/7= BE/AF1/AF2/(AF3)/AF4/EF/CS6/CS7
	private int four = 0;	//4		0/1/2/3/(4)/5/6/7= BE/AF1/AF2/AF3/(AF4)/EF/CS6/CS7
	private int five = 0;	//5		0/1/2/3/4/(5)/6/7= BE/AF1/AF2/AF3/AF4/(EF)/CS6/CS7
	private int six = 0;	//6		0/1/2/3/4/(5)/(6)/7= BE/AF1/AF2/AF3/AF4/EF/(CS6)/CS7
	private int seven =0;	//7		0/1/2/3/4/(5)/6/(7)= BE/AF1/AF2/AF3/AF4/EF/CS6/(CS7)
	
	public int getExpPHBID() {
		return expPHBID;
	}
	public void setExpPHBID(int expPHBID) {
		this.expPHBID = expPHBID;
	}
	public int getZero() {
		return zero;
	}
	public void setZero(int zero) {
		this.zero = zero;
	}
	public int getOne() {
		return one;
	}
	public void setOne(int one) {
		this.one = one;
	}
	public int getTwo() {
		return two;
	}
	public void setTwo(int two) {
		this.two = two;
	}
	public int getThree() {
		return three;
	}
	public void setThree(int three) {
		this.three = three;
	}
	public int getFour() {
		return four;
	}
	public void setFour(int four) {
		this.four = four;
	}
	public int getFive() {
		return five;
	}
	public void setFive(int five) {
		this.five = five;
	}
	public int getSix() {
		return six;
	}
	public void setSix(int six) {
		this.six = six;
	}
	public int getSeven() {
		return seven;
	}
	public void setSeven(int seven) {
		this.seven = seven;
	}
	
}
