/* 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

function tableSort(sortBy) {
	window.location.search = "sortBy=" + sortBy + "&pageIndex=" + getTablePageIndex();
}
function tablePage(increment) {
	window.location.search = "sortBy=" + getCurrentPageSort() + "&pageIndex=" + getTablePageIndex(increment);
}
function getTablePageIndex(increment) {
	var index;
	var current = parseInt(document.getElementById("pagination").getAttribute("data-current-page"),10);
	var max = parseInt(document.getElementById("pagination").getAttribute("data-max-page"),10);
	if(increment) {
		index = current + increment;
		index = Math.max(index, 0);
		index = Math.min(index, max);
	}
	else {
		index = current;
	}
	return index;
}
function getCurrentPageSort() {
	return document.getElementById("pagination").getAttribute("data-current-sort");
}
function listenForTableClicks() {
	// Sorting
	var cols = document.getElementById("headerRow").getElementsByTagName("th");
	for(var i=0, size=cols.length; i < size; i++) {
		if(cols[i].id) {
			cols[i].addEventListener("click", function() { tableSort(this.id); }, false);
		}
	}
	// Pagination
	var prevPage = document.getElementById("prevPage");
	prevPage.addEventListener("click", function() { tablePage(-1); }, false);
	var nextPage = document.getElementById("nextPage");
	nextPage.addEventListener("click", function() { tablePage(1); }, false);
}

listenForTableClicks();
