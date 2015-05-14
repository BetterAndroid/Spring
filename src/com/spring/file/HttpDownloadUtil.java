package com.spring.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.spring.utils.FileUtils;

public class HttpDownloadUtil {
    
	/**************************文件下载*******************************/
	/**
     * 根据URL下载文件，前提是这个文件当中的内容是文本，函数返回值是文件当中的文本内容
     * @param urlstr
     * @return
*/
    public String downloadFile(String urlstr){
        StringBuffer sb=new StringBuffer();
        BufferedReader buffer=null;
        URL url=null;
        String line=null;
        try {
            //创建一个URL对象
            url=new URL(urlstr);
            //根据URL对象创建一个Http连接
            HttpURLConnection urlConn=(HttpURLConnection) url.openConnection();
            //使用IO读取下载的文件数据
            buffer=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while((line=buffer.readLine())!=null){
                sb.append(line);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    /**
     * 该函数返回整形    -1：代表下载文件错误0 ：下载文件成功1：文件已经存在 
     * @param urlstr
     * @param path
     * @param fileName
     * @return
*/
    public int downloadFile(String urlstr,String path,String fileName, final DownloadProcessListener listener){
        InputStream inputStream=null;
        FileUtils fileUtils=new FileUtils(path);
        
        try {
			if(fileUtils.isFileExist(fileName)){
				listener.onDownloadDone(1, "");
			}else{
			    inputStream=getInputStreamFormUrl(urlstr);
			    if(inputStream!=null) {
			    	listener.initDownload(inputStream.available());
			    	File resultFile=fileUtils.writeToSDfromInput(path, fileName, inputStream);
			    	if(resultFile==null){
			    		listener.onDownloadDone(-1, "");
			    		return -1;
			    	}
			    } else {
			    	listener.onDownloadDone(-1, "");
			    	return -1;
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        listener.onDownloadDone(0, "");
        return 0;
    }
    
    
    
    /**
     * 根据URL得到输入流
     * @param urlstr
     * @return
*/
    public InputStream getInputStreamFormUrl(String urlstr){
        InputStream inputStream=null;
        try {
            URL url=new URL(urlstr);
            HttpURLConnection urlConn=(HttpURLConnection) url.openConnection();
            int downSize = urlConn.getContentLength();//根据响应获取文件大小
    	    if (downSize <= 0) throw new RuntimeException("无法获知文件大小");
    	    inputStream=urlConn.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
