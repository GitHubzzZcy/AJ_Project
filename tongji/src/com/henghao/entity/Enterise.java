/**
 * 
 */
package com.henghao.entity;

/**2017-3-30,上午10:03:55Enterise.java
 * @author Nwl
 *
 */
public class Enterise {
	/**
	 * 分类类名
	 */
	private String typeName;
	/**
	 * 对应类别数量
	 */
	private int numbers;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	@Override
	public String toString() {
		return "Enterise [typeName=" + typeName + ", numbers=" + numbers + "]";
	}
	
}
