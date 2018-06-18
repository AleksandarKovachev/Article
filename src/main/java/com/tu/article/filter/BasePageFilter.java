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

	public int getLoopPagesSize() {
		if (getTotalPages() <= 5) {
			return getTotalPages() - 1;
		} else {
			return getLoopPagesStart() + 4;
		}
	}

	public int getLoopPagesStart() {
		if (getTotalPages() <= 5) {
			return 0;
		} else {
			if (getPageNumber() - 2 >= 0) {
				if (getPageNumber() + 2 <= getTotalPages() - 1) {
					// the selected page is on the 3rd position
					return getPageNumber() - 2;
				} else if (getPageNumber() + 1 <= getTotalPages() - 1) {
					// the selected page is on the 4th position
					return getPageNumber() - 3;
				} else {
					// the selected page is on the 5th position
					return getPageNumber() - 4;
				}
			} else if (getPageNumber() - 1 >= 0) {
				// the selected page is on the second position
				return getPageNumber() - 1;
			} else {
				// the selected page is on the first postion
				return getPageNumber();
			}
		}
	}

}
