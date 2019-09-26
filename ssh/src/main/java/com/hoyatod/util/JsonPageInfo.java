/**
 * 返回给EasyUI Datagrid的数据 
 */
package com.hoyatod.util;

import java.util.List;

public class JsonPageInfo {
	
    private long total;//数据总数
    
    private List<?> rows;//表格内数据  
  
    public long getTotal() {  
        return total;  
    }  
  
    public void setTotal(long total) {  
        this.total = total;  
    }  
  
    public List<?> getRows() {  
        return rows;  
    }  
  
    public void setRows(List<?> rows) {  
        this.rows = rows;  
    }  


}
