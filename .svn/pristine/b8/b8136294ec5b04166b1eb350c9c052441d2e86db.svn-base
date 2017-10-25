package com.benqzl.unit;

public class UnitALG {
	
	/**
	 * #56/78机组综合常用电率算法
	 * a:fdlrr5(dlnl_dl)   b:fdlrr6(dlnl_dl)  c:fdlrr7(dlnl_dl)  d:fdlrr8(dlnl_dl) e:eswdl(dlnl_dl)  f:xwdlrr(dlnl_nh)
	 */
	public  float consumptionRate(float a,float b,float c,float d,float e,float f){
		float result  = a+b+c+d-e+f/a+b+c+d;
		return result;
	}
	/**
	 * #1/2  #3/4机组综合常用电率算法
	 * a:fdlrj1/fdlrj3(dlnl_dl)     b:fdlrj2/fdlrj4(dlnl_dl)    c:fswdl/fswdl34(dlnl_dl)  d:xwdl/xwdl34(dlnl_nh)
	 */
	public  float consumptionRate(float a,float b,float c,float d){
		float result  = a+b-c+d/a+b;
		return result;
	}
	
	/**
	 * #12/34发电气耗
	 *  a:fdlrj1/fdlrj3(dlnl_dl)     b:fdlrj2/fdlrj4(dlnl_dl)    c:fswdl/fswdl34(dlnl_dl)  
	 */
	
	public  float consumptionAir(float a,float b,float c){
		float result  = c/a+b;
		return result;
	}
	
	/**
	 * #5678发电气耗
	 *  a:fdlrr5(dlnl_dl)   b:fdlrr6(dlnl_dl)  c:fdlrr7(dlnl_dl)  d:fdlrr8(dlnl_dl) e:ehql24(dlnl_nh)  f:grlrr(dlnl_nh)  
	 */
	
	public  float consumptionAir(float a,float b,float c,float d,float e,float f){
		float result  = e-(f*93/10000)/a+b+c+d;
		return result;
	}
	
	/**
	 * 管损算法
	 * a:grlrr(dlnl_nl)   b:esrl(dlnl_nl) 
	 */
	public  float consumptionHeat(float a,float b){
		float result  = (a-b/a)/100;
		return result;
	}
	
}
