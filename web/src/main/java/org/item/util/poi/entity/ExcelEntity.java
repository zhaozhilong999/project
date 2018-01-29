package org.item.util.poi.entity;

import java.io.Serializable;
import java.util.List;

public class ExcelEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 文件路径
	 */
	private String filePath = "";
	/**
	 * 文件Sheet
	 */
	private List<SheetEntity> sheets;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List<SheetEntity> getSheets() {
		return sheets;
	}
	public void setSheets(List<SheetEntity> sheets) {
		this.sheets = sheets;
	}
	
	
}
