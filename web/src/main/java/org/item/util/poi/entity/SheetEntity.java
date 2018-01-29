package org.item.util.poi.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SheetEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 文件sheet名称
	 */
	private String sheetName;
	/**
	 * 表格标题，写的时候有用
	 */
	private List<String> titleList;
	/**
	 * 表格内容
	 */
	private List<List<Object>> contentList;
	
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public List<String> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}
	public List<List<Object>> getContentList() {
		return contentList;
	}
	public void setContentList(List<List<Object>> contentList) {
		this.contentList = contentList;
	}
	/**
	 * 将contentList分为两份，一个title，一个content
	 * 自动从contentList取第一行为表头，同时将contentList第一行删除
	 * @return
	 */
	public List<String> hasTitle(){
		if(null!=this.contentList && this.contentList.size()>0){
			if(this.titleList == null){
				this.titleList = new LinkedList<String>();
			}
			List<Object> list = this.contentList.get(0);
			for(Object obj : list){
				this.titleList.add((String)obj);
			}
			this.contentList.remove(0);
		}
		return titleList;
	}
}
