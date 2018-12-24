package reportreader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import object.Person;
import path.Path;
import report.Report;

public abstract class ReportReader {
	private static final String BASE_PATH=Path.filepath+"/WebRoot/report";
	private static final String DATE_FORMAT = "MM-dd-yyyy";
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	//成员变量
	private String personPath;
	private String directoryPath;
	private String filePath;
	private File file;
	private String personType;
	
	public ReportReader(Report report,String personType){
		setPersonType(personType);
		this.setPersonPath(BASE_PATH+"/"+personType);
		this.directoryPath=getPersonPath()+"/"+report.getName();
		this.filePath=getDirectoryPath()+"/"+report.getName()+"-"+report.getReportDateString()+".xml";
		setFile(new File(getFilePath()));
	}
	
	public ReportReader(Person person,Date reportDate,String personType){
		setPersonType(personType);
		dateFormatter.applyPattern(DATE_FORMAT);
		String reportDateString = dateFormatter.format(reportDate);
		this.setPersonPath(BASE_PATH+"/"+personType);
		this.directoryPath=getPersonPath()+"/"+person.getName();
		this.filePath=getDirectoryPath()+"/"+person.getName()+"-"+reportDateString+".xml";
		setFile(new File(getFilePath()));
	}
	
	public ReportReader(String filePath){
		setFilePath(filePath);
		setFile(new File(getFilePath()));
		
		this.directoryPath=getFile().getParent();
		
		File directoryFolder=new File(getDirectoryPath());
		this.personPath=directoryFolder.getParent();
	}

	public ReportReader(File file){
		setFile(file);
		this.filePath=file.getPath();
		this.directoryPath=file.getParent();
		
		File directoryFolder=new File(getDirectoryPath());
		this.personPath=directoryFolder.getParent();
	}
	
	public File[] getAllFileByPerson(){	
		File folder=new File(directoryPath);
		File[] fileList=folder.listFiles();
		return fileList;
		
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
		if(!file.exists())    
		{    
		    try {    
		    	file.createNewFile();    
		    } catch (IOException e) {    
		        // TODO Auto-generated catch block    
		        e.printStackTrace();    
		    }    
		}    
		this.file = file;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getPersonPath() {
		return personPath;
	}

	public void setPersonPath(String personPath) {
		this.personPath = personPath;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}
	
}
