package com.henghao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.henghao.entity.ExcelEntity;

/**
 * 读写文件工具
 * @author Administrator
 *
 */
public class FileUtils {
	/**
	 * 读文件
	 * @param fileName 文件名
	 * @return
	 */
	public static String readFile(String fileName){
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String line = null;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(isr != null){
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 写文件
	 * @param data 传入的数据
	 * @param path 写入文件的路径
	 */
	public static void writeFile(String data,String path){
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		PrintWriter pw = null;
		try {
			File file = new File(path);
			if(!file.exists()){
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos);
			pw = new PrintWriter(osw);
			pw.println(data);
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(osw != null){
				try {
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(pw != null){
				pw.close();
			}
		}
	}
	/**
	 * 下载excel文件
	 * HttpServletResponse response ,List<Object[]> contents 
	 * 内容类似二维数组,List<String> head 列内容,String title 标题头,String filename 文件名
	 */
	public static void downloadExcel(HttpServletResponse response,List<ExcelEntity> list) throws IOException {
		//创建HSSFWorkbook对象(excel的文档对象)  
		HSSFWorkbook wb = new HSSFWorkbook();  
		for (ExcelEntity excel : list) {			
			//建立新的sheet对象（excel的表单）  
			HSSFSheet sheet= wb.createSheet(excel.getTitle());  
			//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
			HSSFRow row1=sheet.createRow(0);  
			//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
			HSSFCell cell=row1.createCell(0);  
		     //设置单元格内容  
			cell.setCellValue(excel.getTitle());  
			//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));  
			//在sheet里创建第二行  
			HSSFRow row2=sheet.createRow(1);      
		     //创建单元格并设置单元格内容  
			for (int m=0;m<excel.getHead().length;m++) {
				row2.createCell(m).setCellValue(excel.getHead()[m]);
			}
		    //在sheet里创建第三以后行  
		    if(excel.getContents() !=null && !excel.getContents().isEmpty()){
		    	 for(int i=0; i<excel.getContents().size(); i++) {
		 	    	HSSFRow row3 =sheet.createRow(2+i); 
		 	    	int j=excel.getContents().get(i).length;
		 	    	for(int k=0;k<j;k++){
		 	    		row3.createCell(k).setCellValue(""+excel.getContents().get(i)[k]);
		 	    	}
		 	    }
		    }	   		     
			
		}
		//.....省略部分代码  	  
		//输出Excel文件  
	    OutputStream output=response.getOutputStream();  
	    response.reset();  
	    list.get(0).setFilename(list.get(0).getFilename()+DateUtils.getCurrentDate("yyyyMMddhhmmss")+".xls");
	    response.setHeader("Content-disposition", "attachment; filename="+list.get(0).getFilename());  
	    response.setContentType("application/msexcel");          
	    wb.write(output);  
	    output.close();  
	}
	
}
