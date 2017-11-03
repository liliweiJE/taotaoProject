package com.taotao.common.utils.fastdfs;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.taotao.common.pojo.FastDFSFile;

public class FastDfsUtisl {
	
	private static final String FAST_CONF_URL="E:\\taotao\\taotao-common\\src\\main\\resources\\property\\fdfs_client.conf";
	
	private static TrackerClient trackerClient;
	
	private static TrackerServer trackerServer;
	
	private static StorageClient storageClient;
	
	private static StorageServer storageServer;
	
	static{
		//初始化fastdfs配置文件
		try {
			ClientGlobal.init(FAST_CONF_URL);
			trackerClient=new TrackerClient();
			trackerServer=trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, storageServer);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String uploadFile(FastDFSFile file,NameValuePair[] meta_list) throws IOException, MyException{
	      long startTime = System.currentTimeMillis();
	    String[] uploadResults = null;
	    try {
	      uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), null);
	    } catch (Exception e) {
	    }
	    
	    if (uploadResults == null) {
	    	//失败待优化
	      return null;
	    }
	    
	    String groupName 		= uploadResults[0];
	    String remoteFileName   = uploadResults[1];
	    
	   /* String fileAbsolutePath = PROTOCOL + trackerServer.getInetSocketAddress().getHostName() 
	        + SEPARATOR
	        + groupName 
	        + SEPARATOR 
	        + remoteFileName;*/
	    
	    
	    return null;
	}
	
	public static FileInfo getFile(String groupName, String remoteFileName) {
	    try {
	      return storageClient.get_file_info(groupName, remoteFileName);
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
	    return null;
	  }
	  
	  public static void deleteFile(String groupName, String remoteFileName) throws Exception {
	    storageClient.delete_file(groupName, remoteFileName);
	  }
	  
	  public static StorageServer[] getStoreStorages(String groupName) throws IOException {
	    return trackerClient.getStoreStorages(trackerServer, groupName);
	  }
	  
	  public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
	    return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
	  }
	
	/*public static void main(String[] args) throws Exception {
		 
		 File content = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\yy.jpg");
		    
		    FileInputStream fis = new FileInputStream(content);
		      byte[] file_buff = null;
		      if (fis != null) {
		      	int len = fis.available();
		      	file_buff = new byte[len];
		      	fis.read(file_buff);
		      }
		    
		    FastDFSFile file = new FastDFSFile("520", file_buff, "jpg");
		    
		    String fileAbsolutePath = FastDfsUtisl.uploadFile(file);
		    System.out.println(fileAbsolutePath);
		    fis.close();
	}*/

}
