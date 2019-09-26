package com.hoyatod.util;

import java.util.List;

@SuppressWarnings("rawtypes")
public class PageResult {

	private Page page;// ��װ�õķ�ҳ��Ϣ
	private List list;// ��¼��Ϣ

	public PageResult(Page page, List list) {
		super();
		this.page = page;
		this.list = list;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
