package com.nms.drive.service.bean;


/**
 * 时钟配置块(01H)
 * 
 * @author hulei
 * 
 */
public class ClockObject {

	private int clockWorkMode = 0; // 时钟工作模式" value="0" mapping="(00)/01/02=(AUTO)/HOLD/FREE"
	private int qlEnable = 0;// QL使能选择式" value="1" mapping="00/(01)=QL使能/(QL不使能)"
	
	private	String clockPRIS = "";// 时钟优先级排列  value="255" mapping="00/01/…/0EH/FFH==外时钟/WAN1/WAN2/WAN3/WAN4/LAN1/LAN2/…/LAN6/SLOT02参考源1/SLOT02参考源2/SLOT03参考源1/SLOT03参考源2/空,若未填满16项, 补填FFH,缺省列表[FFH,00,01,02……]"
	
	private int outerClockInSelect = 0;// 外时钟输入选择" value="1" mapping="00/(01)=HDB3/(Hz)"
	private int outerClockOutSelect1 = 0;// 外时钟输出使能选择1" value="0" mapping="01/(00)=开/(关)"
	private int outerClockOutSelect2 = 0;// 外时钟输出使能选择2" value="1" mapping="00/(01)=HDB3/(Hz)"
	
	private String outSelectS = "";// 输出时钟选择 value="0" mapping="00/01/02/03/04/05/06/07/08/09/0A/0B/0C/0D/0EH/0FH/(0FFH)=通PLL锁相环/外时钟/GE1/GE2/GE3/GE4/FE1/FE2/FE3/FE4/FE5/FE6/FE7/FE8/FE卡/E1/(空)"
	
	private int smOuter = 0;// SM门限外时钟" value="11" mapping="00/02/04/08/(0B)/0F=质量未知/G811时钟/G812转送时钟/G812本地时钟/(G813时钟)/不用于同步"
	private int smSystem = 0;// SM门限系统时钟" value="11" mapping="00/02/04/08/(0B)/0F=质量未知/G811时钟/G812转送时钟/G812本地时钟/(G813时钟)/不用于同步"
	private int qlIn = 0;// QL使用SA选择输入源SA选择" value="0" mapping="(00)/01/02/03/04=(SA4)/SA5/SA6/SA7/SA8"
	private int qlOut = 0;// QL使用SA选择输出源SA选择" value="0" mapping="(00)/01/02/03/04=(SA4)/SA5/SA6/SA7/SA8"
	
	private String clockInQLValueS = ""; //输入源QL值GE1  value="11"   mapping="00/02/04/08/(0B)/0F/1F=质量未知/G811时钟/G812转送时钟/G812本地时钟/(G813时钟)/不用于同步/自动提取S1"
	
	private String ClockOutQLValueS = "";// 输出源的QL值GE1" value="11" mapping="00/02/04/08/(0B)/0F/1F=质量未知/G811时钟/G812转送时钟/G812本地时钟/(G813时钟)/不用于同步/自动提取S1"
	
	private int clockInResumeTime = 0;// 时钟输入源等待恢复时间" value="5" mapping="5"
	
	// 等待恢复时间开关" value="0" mapping="(00)"
	private String r10 = "0"; 	//（字节 1）BIT0=外时钟=0/1=OFF/ON  
	private String r11 = "0";	//    BIT1= GE1.1=0/1=OFF/ON
	private String r12 = "0";	//    BIT2= GE1.2=0/1=OFF/ON
	private String r13 = "0";	//    BIT3= GE1.3=0/1=OFF/ON
	private String r14 = "0";	//    BIT4= GE1.4=0/1=OFF/ON
	private String r15 = "0";	//	  BIT5= GE1.5=0/1=OFF/ON
	private String r16 = "0";	//    BIT6= GE1.6=0/1=OFF/ON
	private String r17 = "0";   //    BIT7= GE1.7=0/1=OFF/ON
	private String r18 = "0";	//（字节 2） BIT0= GE1.8=0/1=OFF/ON
	private String r19 = "0";	//    BIT1= GE1.9=0/1=OFF/ON
	private String r20 = "0";	//    BIT2= GE1.10=0/1=OFF/ON
	private String r21 = "0";	// 	  BIT3= GE2.1=0/1=OFF/ON
	private String r22 = "0";	//    BIT4= GE2.2=0/1=OFF/ON
	private String r23 = "0";	//    BIT5= GE2.3=0/1=OFF/ON
	private String r24 = "0";	//	  BIT6= GE2.4=0/1=OFF/ON
	private String r25 = "0";	//    BIT7= GE2.5=0/1=OFF/ON
	private String r26 = "0";	//（字节 3） BIT0= GE2.6=0/1=OFF/ON
	private String r27 = "0";   //    BIT1= GE2.7=0/1=OFF/ON
	private String r28 = "0";	//    BIT2= GE2.8=0/1=OFF/ON
	private String r31 = "0";	// 	  BIT3= GE3.1=0/1=OFF/ON
	private String r32 = "0";	//    BIT4= GE3.2=0/1=OFF/ON
	private String r33 = "0";	//    BIT5= GE3.3=0/1=OFF/ON
	private String r34 = "0";	//    BIT6= GE3.4=0/1=OFF/ON
	private String r35 = "0";	//    BIT7= GE3.5=0/1=OFF/ON
	private String r36 = "0";	//(字节4) BIT0= GE3.6=0/1=OFF/ON
	private String r37 = "0";	//    BIT1= GE3.7=0/1=OFF/ON
	private String r38 = "0";	//    BIT2= GE3.8=0/1=OFF/ON
	private int clockRorate;
	
	public String getR28() {
		return r28;
	}
	public void setR28(String r28) {
		this.r28 = r28;
	}
	public int getClockWorkMode() {
		return clockWorkMode;
	}
	public void setClockWorkMode(int clockWorkMode) {
		this.clockWorkMode = clockWorkMode;
	}
	public int getQlEnable() {
		return qlEnable;
	}
	public void setQlEnable(int qlEnable) {
		this.qlEnable = qlEnable;
	}
	public int getOuterClockInSelect() {
		return outerClockInSelect;
	}
	public void setOuterClockInSelect(int outerClockInSelect) {
		this.outerClockInSelect = outerClockInSelect;
	}
	public int getOuterClockOutSelect1() {
		return outerClockOutSelect1;
	}
	public void setOuterClockOutSelect1(int outerClockOutSelect1) {
		this.outerClockOutSelect1 = outerClockOutSelect1;
	}
	public int getOuterClockOutSelect2() {
		return outerClockOutSelect2;
	}
	public void setOuterClockOutSelect2(int outerClockOutSelect2) {
		this.outerClockOutSelect2 = outerClockOutSelect2;
	}
	public int getSmOuter() {
		return smOuter;
	}
	public void setSmOuter(int smOuter) {
		this.smOuter = smOuter;
	}
	public int getSmSystem() {
		return smSystem;
	}
	public void setSmSystem(int smSystem) {
		this.smSystem = smSystem;
	}
	public int getQlIn() {
		return qlIn;
	}
	public void setQlIn(int qlIn) {
		this.qlIn = qlIn;
	}
	public int getQlOut() {
		return qlOut;
	}
	public void setQlOut(int qlOut) {
		this.qlOut = qlOut;
	}
	public int getClockInResumeTime() {
		return clockInResumeTime;
	}
	public void setClockInResumeTime(int clockInResumeTime) {
		this.clockInResumeTime = clockInResumeTime;
	}
	public String getR10() {
		return r10;
	}
	public void setR10(String r10) {
		this.r10 = r10;
	}
	public String getR11() {
		return r11;
	}
	public void setR11(String r11) {
		this.r11 = r11;
	}
	public String getR12() {
		return r12;
	}
	public void setR12(String r12) {
		this.r12 = r12;
	}
	public String getR13() {
		return r13;
	}
	public void setR13(String r13) {
		this.r13 = r13;
	}
	public String getR14() {
		return r14;
	}
	public void setR14(String r14) {
		this.r14 = r14;
	}
	public String getR20() {
		return r20;
	}
	public void setR20(String r20) {
		this.r20 = r20;
	}
	public String getR21() {
		return r21;
	}
	public void setR21(String r21) {
		this.r21 = r21;
	}
	public String getR22() {
		return r22;
	}
	public void setR22(String r22) {
		this.r22 = r22;
	}
	public String getR23() {
		return r23;
	}
	public void setR23(String r23) {
		this.r23 = r23;
	}
	public String getR24() {
		return r24;
	}
	public void setR24(String r24) {
		this.r24 = r24;
	}
	
	public String getR15() {
		return r15;
	}
	public void setR15(String r15) {
		this.r15 = r15;
	}
	public String getR16() {
		return r16;
	}
	public void setR16(String r16) {
		this.r16 = r16;
	}
	public String getR17() {
		return r17;
	}
	public void setR17(String r17) {
		this.r17 = r17;
	}
	public String getR18() {
		return r18;
	}
	public void setR18(String r18) {
		this.r18 = r18;
	}
	public String getR19() {
		return r19;
	}
	public void setR19(String r19) {
		this.r19 = r19;
	}
	public String getClockPRIS() {
		return clockPRIS;
	}
	public void setClockPRIS(String clockPRIS) {
		this.clockPRIS = clockPRIS;
	}
	public String getOutSelectS() {
		return outSelectS;
	}
	public void setOutSelectS(String outSelectS) {
		this.outSelectS = outSelectS;
	}
	public String getClockInQLValueS() {
		return clockInQLValueS;
	}
	public void setClockInQLValueS(String clockInQLValueS) {
		this.clockInQLValueS = clockInQLValueS;
	}
	public String getClockOutQLValueS() {
		return ClockOutQLValueS;
	}
	public void setClockOutQLValueS(String clockOutQLValueS) {
		ClockOutQLValueS = clockOutQLValueS;
	}
	public String getR31() {
		return r31;
	}
	public void setR31(String r31) {
		this.r31 = r31;
	}
	public String getR32() {
		return r32;
	}
	public void setR32(String r32) {
		this.r32 = r32;
	}
	public String getR33() {
		return r33;
	}
	public void setR33(String r33) {
		this.r33 = r33;
	}
	public String getR34() {
		return r34;
	}
	public void setR34(String r34) {
		this.r34 = r34;
	}
	public String getR35() {
		return r35;
	}
	public void setR35(String r35) {
		this.r35 = r35;
	}
	public String getR36() {
		return r36;
	}
	public void setR36(String r36) {
		this.r36 = r36;
	}
	public String getR37() {
		return r37;
	}
	public void setR37(String r37) {
		this.r37 = r37;
	}
	public String getR38() {
		return r38;
	}
	public void setR38(String r38) {
		this.r38 = r38;
	}
	public int getClockRorate() {
		return clockRorate;
	}
	public void setClockRorate(int clockRorate) {
		this.clockRorate = clockRorate;
	}
	public String getR25() {
		return r25;
	}
	public void setR25(String r25) {
		this.r25 = r25;
	}
	public String getR26() {
		return r26;
	}
	public void setR26(String r26) {
		this.r26 = r26;
	}
	public String getR27() {
		return r27;
	}
	public void setR27(String r27) {
		this.r27 = r27;
	}
	
}
