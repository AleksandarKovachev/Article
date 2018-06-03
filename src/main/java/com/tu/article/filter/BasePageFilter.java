package com.tu.article.filter;

/**
 * Base filter with defined methods for working with the pagination
 * 
 * @author aleksandar.kovachev
 *
 */
public class BasePageFilter {

	protected int pageNumber;
	protected int pageSize;
	protected int totalCount;

	public BasePageFilter(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNumber(int pgnum) {
		// prevent moving before first page
		if (pgnum < 0) {
			pgnum = 0;
		}

		// prevent moving after the last page
		int lastPageNumber = totalCount / pageSize;

		if ((totalCount > 0) && (totalCount % pageSize == 0)) {
			lastPageNumber--;
		}

		if (pgnum > lastPageNumber) {
			pgnum = lastPageNumber;
		}

		pageNumber = pgnum;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageSize(int pgsize) {
		pageSize = pgsize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotalCount(int total) {
		totalCount = total;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPages() {
		return ((getTotalCount() % getPageSize()) == 0 && getTotalCount() != 0) ? getTotalCount() / getPageSize()
				: (getTotalCount() / getPageSize()) + 1;
	}

	public boolean getHasNextPage() {
		return (getPageLastRow() < totalCount);
	}

	public boolean getHasPrevPage() {
		return (getPageFirstRow() > 1);
	}

	public int getPageLastRow() {
		return (pageNumber + 1) * pageSize;
	}

	public int getPageFirstRow() {
		return pageNumber * pageSize + 1;
	}

}
