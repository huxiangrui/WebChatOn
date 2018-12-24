package reportwriter;

import java.io.File;
import java.io.IOException;

import path.Path;
import report.Report;

public abstract class ReportWriter {
	private static final String BASE_PATH=Path.filepath+"/WebRoot/report";
	
	//成员变量
	private String personPath;
	private String directoryPath;
	private String filePath;
	private File file;
	private String personType;
	
	public  ReportWriter(Report report,String personType){
		setPersonType(personType);
		
		createFolder(BASE_PATH);
		
		this.personPath=BASE_PATH+"/"+personType;
		createFolder(getPersonPath());
		
		this.directoryPath=getPersonPath()+"/"+report.getName();
		createFolder(getDirectoryPath());
		
		this.filePath=getDirectoryPath()+"/"+report.getName()+"-"+report.getReportDateString()+".xml";
		createFile(getFilePath());
		
		this.file=new File(getFilePath());
	}

	private void createFolder(String path){
		File newFolder=new File(path);
		if (!newFolder.exists()&& !newFolder.isDirectory()) {
			newFolder.mkdir();
		}
	}
	
	private void createFile(String path){
		File newFile=new File(path);
		if(!newFile.exists())    
		{    
		    try {    
		    	newFile.createNewFile();    
		    } catch (IOException e) {    
		        // TODO Auto-generated catch block    
		        e.printStackTrace();    
		    }    
		}    
	}
	
	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getPersonPath() {
		return personPath;
	}

	public void setPersonPath(String personPath) {
		this.personPath = personPath;
	}
}
