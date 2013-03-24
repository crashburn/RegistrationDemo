/* 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
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
	public void setMaxPageIndex(int totalRecordCount, int pageSize) {
		int max = totalRecordCount / pageSize;
		int mod = totalRecordCount % pageSize;
		if( (max > 0) && (mod == 0) ) {
			max--;
		}
		this.maxPageIndex = max;
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
