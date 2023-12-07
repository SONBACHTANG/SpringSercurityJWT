package com.dto;

import java.util.ArrayList;
import java.util.List;

public class OutputSort {

	private int page;
	private int totalPage;
	private List<CustomDTO> listResult = new ArrayList<>();
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<CustomDTO> getListResult() {
		return listResult;
	}

	public void setListResult(List<CustomDTO> listResult) {
		this.listResult = listResult;
	}
}
