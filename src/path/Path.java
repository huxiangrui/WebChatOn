package path;

import java.io.File;

public class Path {
	public static String filepath=getWebPath();
	
	private static String getPath(){
		String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();		
		String parentPath= getParentPath( getParentPath(getParentPath( getParentPath(path))));
		return parentPath+"/WebChatOn";
	}
	
	private static String getWebPath(){
		String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();		
		String parentPath= getParentPath( getParentPath(getParentPath( getParentPath(getParentPath( getParentPath(path))))));
		return parentPath+"/WebChatOn";
	}
	
	private static String getParentPath(String path){
		File file=new File(path);
		return file.getParent();
	}
}
