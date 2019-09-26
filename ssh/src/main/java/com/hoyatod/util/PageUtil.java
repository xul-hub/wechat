package com.hoyatod.util;

public class PageUtil {

	public static Page createPage(int everyPage, int totalCount, int currentPage) {
		everyPage = getEveryPage(everyPage);
		currentPage = getCurrenPage(currentPage);
		int beginIndex = getBeginIndex(everyPage, currentPage);
		int totalPage = getTotalPage(everyPage, totalCount);
		boolean hasPrePage = getHasPrePage(currentPage);
		boolean hasNextPage = getHasNextPage(totalPage, currentPage);
		return new Page(everyPage, totalCount, totalPage, currentPage, beginIndex, hasPrePage, hasNextPage);
	}

	public static int getEveryPage(int everyPage) {// ���ÿҳ����ʾ��¼�� Ĭ��Ϊ10
		return everyPage == 0 ? 10 : everyPage;
	}

	public static int getCurrenPage(int currenPage) {// ��ȡ��ǰҳ Ĭ��Ϊ1
		return currenPage == 0 ? 1 : currenPage;
	}

	public static int getTotalPage(int everyPage, int totalCount) {
		int totalPage = 0;
		if (totalCount != 0 && totalCount % everyPage == 0) {
			totalPage = totalCount / everyPage;
		} else {
			totalPage = totalCount / everyPage + 1;// ҳ��+1
		}
		return totalPage;
	}

	/**
	 * ��ȡ�÷�ҳ�ĵ�һ������
	 * 
	 * @param everyPage
	 * @param currentPage
	 * @return
	 */
	public static int getBeginIndex(int everyPage, int currentPage) {
		return (currentPage - 1) * everyPage;
	}

	public static boolean getHasPrePage(int currentPage) {
		return currentPage == 1 ? false : true;// �����ǰҳΪ1�Ļ�����û��ǰһҳ
	}

	public static boolean getHasNextPage(int totalPage, int currentPage) {
		return currentPage == totalPage || totalPage == 0 ? false : true;
	}
}
