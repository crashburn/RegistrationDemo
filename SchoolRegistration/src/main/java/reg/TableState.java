package reg;

public class TableState {
	
	private int pageIndex;
	private int maxPageIndex;
	
	private String sortBy;
	private String defaultSortBy;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getMaxPageIndex() {
		return maxPageIndex;
	}
	public void setMaxPageIndex(int maxPageIndex) {
		this.maxPageIndex = maxPageIndex;
	}
	
	/**
	 * Page number makes more sense to humans...it adjusts the 
	 * index by 1 to account for the index being zero-based
	 */
	public int getPageNumber() {
		return pageIndex + 1;
	}
	/**
	 * Page number makes more sense to humans...it adjusts the 
	 * index by 1 to account for the index being zero-based
	 */
	public int getMaxPageNumber() {
		return maxPageIndex + 1;
	}
	
	public String getSortBy() {
		return (sortBy != null) ? sortBy : defaultSortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getDefaultSortBy() {
		return defaultSortBy;
	}
	public void setDefaultSortBy(String defaultSortBy) {
		this.defaultSortBy = defaultSortBy;
	}
	

}
