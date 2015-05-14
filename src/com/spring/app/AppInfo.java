package com.spring.app;

import android.content.Context;

public class AppInfo {
	
	private AppInfo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取应用程序的包名信息
	 * @param context
	 * @return
	 */
	public static String getAppPackageName(Context context) {
		// TODO Auto-generated method stub
		if(context!=null) {
			return context.getPackageName();
		} 
		return null;
	}
	
}
