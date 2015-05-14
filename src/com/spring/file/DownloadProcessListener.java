package com.spring.file;

/**
 * //自定义的回调函数，用到回调下载文件是否完成
 * @author wy
 */
public interface DownloadProcessListener {
	
	/**
	 * 准备下载
	 * @param fileSize
	 */
	void initDownload(int fileSize);
	
	/**
	 * 下载中
	 * @param uploadSize
	 */
	void onDownloadProcess(int fileSize, int uploadSize);
	
	/**
	 * 下载响应
	 * @param responseCode
	 * @param message
	 */
	void onDownloadDone(int responseCode, String message);
}
