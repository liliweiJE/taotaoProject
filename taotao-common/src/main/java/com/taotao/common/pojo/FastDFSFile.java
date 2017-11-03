package com.taotao.common.pojo;

public class FastDFSFile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  private String name;
	  
	  private byte[] content;
	  
	  private String ext;
	  
	  
	  
	  
	  public FastDFSFile(String name, byte[] content, String ext, String height,
		      String width, String author) {
		    super();
		    this.name = name;
		    this.content = content;
		    this.ext = ext;
		  }
		  
		  public FastDFSFile(String name, byte[] content, String ext) {
		    super();
		    this.name = name;
		    this.content = content;
		    this.ext = ext;
		  }

		  public byte[] getContent() {
		    return content;
		  }

		  public void setContent(byte[] content) {
		    this.content = content;
		  }

		  public String getExt() {
		    return ext;
		  }

		  public void setExt(String ext) {
		    this.ext = ext;
		  }

		  public String getName() {
		    return name;
		  }

		  public void setName(String name) {
		    this.name = name;
		  }

}
