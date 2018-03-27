package com.henghao.entity;

import java.util.Arrays;
import java.util.List;
/**
 * @类功能说明：  生成excel表格文件所需参数实体类
 * @公司名称：恒昊  
 * @作者： 宁万龙
 * @创建时间：2017-6-7 下午3:02:51  
 * @版本：V1.0
 */
public class ExcelEntity {
	/**
	 * contents 打印的正文表格内容
	 */
	private List<Object[]> contents;
	/**
	 * head 列标题
	 */
	private String[] head;
	/**
	 * title 表格标题题目
	 */
	private String title;
	/**
	 * filename 文件名，不包含 '.xls'
	 */
	private String filename;
	
	public List<Object[]> getContents() {
		return contents;
	}
	public void setContents(List<Object[]> contents) {
		this.contents = contents;
	}
	public String[] getHead() {
		return head;
	}
	public void setHead(String[] head) {
		this.head = head;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public ExcelEntity() {
		super();
	}
	public ExcelEntity(List<Object[]> contents, String[] head, String title,
			String filename) {
		super();
		this.contents = contents;
		this.head = head;
		this.title = title;
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "ExcelEntity [contents=" + contents + ", head="
				+ Arrays.toString(head) + ", title=" + title + ", filename="
				+ filename + "]";
	}
	
}
