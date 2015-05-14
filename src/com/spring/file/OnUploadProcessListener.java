package com.spring.file;

/**
 * //自定义的回调函数，用到回调上传文件是否完成
 * @author wy
 */
public interface OnUploadProcessListener {
	
	/**
	 * 准备上传
	 * @param fileSize
	 */
	void initUpload(int fileSize);
	
	/**
	 * 上传中
	 * @param uploadSize
	 */
	void onUploadProcess(int fileSize, int uploadSize);
	
	/**
	 * 上传响应
	 * @param responseCode
	 * @param message
	 */
	void onUploadDone(int responseCode, String message);
}
