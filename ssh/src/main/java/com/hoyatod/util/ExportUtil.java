package com.hoyatod.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportUtil {
	/**
     * 创建excel文档，
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 12);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 12);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_NONE);
        cs.setBorderRight(CellStyle.BORDER_NONE);
        cs.setBorderTop(CellStyle.BORDER_NONE);
        cs.setBorderBottom(CellStyle.BORDER_NONE);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_NONE);
        cs2.setBorderRight(CellStyle.BORDER_NONE);
        cs2.setBorderTop(CellStyle.BORDER_NONE);
        cs2.setBorderBottom(CellStyle.BORDER_NONE);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        cs2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        
        if(list==null||list.size()==0){
        	// 创建第一个sheet（页），并命名
            Sheet sheet = wb.createSheet();
            // 创建第一行
            Row row = sheet.createRow(0);
            
            //设置列名
            for(int i=0;i<columnNames.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(columnNames[i]);
                cell.setCellStyle(cs);
            } 
        	return wb;
        }
        if(keys==null||keys.length==0){
        	// 创建第一个sheet（页），并命名
            Sheet sheet = wb.createSheet();
            // 创建第一行
            Row row = sheet.createRow(0);
            
            //设置列名
            for(int i=0;i<columnNames.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(columnNames[i]);
                cell.setCellStyle(cs);
            } 
        	return wb;
        }
        if(columnNames==null||columnNames.length==0){
        	// 创建第一个sheet（页），并命名
            Sheet sheet = wb.createSheet();
            // 创建第一行
            Row row = sheet.createRow(0);
            //设置列名
            for(int i=0;i<columnNames.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(columnNames[i]);
                cell.setCellStyle(cs);
            } 
        	return wb;
        }
        
        int total = list.size()/65535;
        int modNum = list.size()%65535;
        
        if(total>0){
        	for(int t=0;t<total;t++){
        		// 创建第一个sheet（页），并命名
                Sheet sheet = wb.createSheet();
                // 创建第一行
                Row row = sheet.createRow(0);
                
                //设置列名
                for(int i=0;i<columnNames.length;i++){
                    Cell cell = row.createCell(i);
                    cell.setCellValue(columnNames[i]);
                    cell.setCellStyle(cs);
                } 
                //设置每行每列的值
                for (int i = 0; i <65535; i++) {
                    // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                    // 创建一行，在页sheet上
                    Row row1 = sheet.createRow(i+1);
                    // 在row行上创建一个方格
                    for(int j=0;j<keys.length;j++){
                        Cell cell = row1.createCell(j);
                        cell.setCellValue(list.get(t*65535+i).get(keys[j]) == null?" ": list.get(t*65535+i).get(keys[j]).toString());
                        cell.setCellStyle(cs2);
                    }
                }
                // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数.
                for(int i=0;i<keys.length;i++){
                    sheet.autoSizeColumn(i , true);
                }
                for(int i=0;i<keys.length;i++){
                	int curColWidth = sheet.getColumnWidth(i);  
                	if(curColWidth<35 * 150){
                		sheet.setColumnWidth(i, (35 * 150));
                	}
                }
        	}
        	if(modNum>0){
        		// 创建第一个sheet（页），并命名
                Sheet sheet = wb.createSheet();
                // 创建第一行
                Row row = sheet.createRow(0);
                
                //设置列名
                for(int i=0;i<columnNames.length;i++){
                    Cell cell = row.createCell(i);
                    cell.setCellValue(columnNames[i]);
                    cell.setCellStyle(cs);
                } 
                //设置每行每列的值
                for (int i = 0; i < modNum; i++) {
                    // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                    // 创建一行，在页sheet上
                    Row row1 = sheet.createRow(i+1);
                    // 在row行上创建一个方格
                    for(int j=0;j<keys.length;j++){
                        Cell cell = row1.createCell(j);
                        cell.setCellValue(list.get(total*65535+i).get(keys[j]) == null?" ": list.get(total*65535+i).get(keys[j]).toString());
                        cell.setCellStyle(cs2);
                    }
                }
                // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
                for(int i=0;i<keys.length;i++){
                    sheet.autoSizeColumn(i , true);
                }
                for(int i=0;i<keys.length;i++){
                	int curColWidth = sheet.getColumnWidth(i);  
                	if(curColWidth<35 * 150){
                		sheet.setColumnWidth(i, (35 * 150));
                	}
                }
        	}
        }else{
        	// 创建第一个sheet（页），并命名
            Sheet sheet = wb.createSheet();
            // 创建第一行
            Row row = sheet.createRow(0);
            
            //设置列名
            for(int i=0;i<columnNames.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(columnNames[i]);
                cell.setCellStyle(cs);
            } 
            //设置每行每列的值
            for (int i = 0; i < modNum; i++) {
                // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                // 创建一行，在页sheet上
                Row row1 = sheet.createRow(i+1);
                // 在row行上创建一个方格
                for(int j=0;j<keys.length;j++){
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                    cell.setCellStyle(cs2);
                }
            }
            // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
            for(int i=0;i<keys.length;i++){
                sheet.autoSizeColumn(i , true);
            }
            for(int i=0;i<keys.length;i++){
            	int curColWidth = sheet.getColumnWidth(i);  
            	if(curColWidth<35 * 150){
            		sheet.setColumnWidth(i, (35 * 150));
            	}
            }
        }
        return wb;
    }
    
    /**
     * 导出Excel
     * @param list
     * @param sheetName
     * @param columsName 
     */
    public static <T> Workbook createWorkBook(List<T> list,String sheetName,String[] columsName){
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        if(list==null||list.size()==0){
        	return wb;
        }
        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_LEFT);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_LEFT);
        
        int total = list.size()/65535;
        int modNum = list.size()%65535;
        Field[] fields = list.get(0).getClass().getDeclaredFields();//获得属性  
        if(total>0){
        	for(int t=0;t<total;t++){
        		 // 创建第一个sheet（页），并命名
                Sheet sheet = wb.createSheet(sheetName);
                // 创建第一行
                Row row = sheet.createRow(0);
                if(columsName==null||columsName.length<1){
                 	for(int k=0;k<fields.length;k++){
                 		//设置列名
                 		Cell cell = row.createCell(k);
                 		cell.setCellValue(fields[k].getName());
                 		cell.setCellStyle(cs);
                 	}
                 }else{
                     //设置列名
                     for(int i=0;i<columsName.length;i++){
                         Cell cell = row.createCell(i);
                         cell.setCellValue(columsName[i]);
                         cell.setCellStyle(cs);
                     }
                 }
                //设置每行每列的值
                for (int i = 0; i<65535; i++) {
                	//创建一行
                	Row row_c = sheet.createRow(i+1);
                    //获取表格数据
                	for(int j=0;j<fields.length;j++){
                		 PropertyDescriptor pd;
        				try {
        					pd = new PropertyDescriptor(fields[j].getName(),list.get(t*65535+i).getClass());
        				 	Method getMethod = pd.getReadMethod();//获得get方法  
                         	Object o;
                         	o = getMethod.invoke(list.get(t*65535+i));
                         	//执行get方法返回一个Object
                            //创建一个单元格
                            Cell cell = row_c.createCell(j);
                            cell.setCellValue((o==null?" ":o.toString()));
                            cell.setCellStyle(cs2);
        				} catch (IntrospectionException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        					continue;
        				} catch (IllegalAccessException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        					continue;
        				} catch (IllegalArgumentException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        					continue;
        				} catch (InvocationTargetException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        					continue;
        				}
                	}
                	// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
                    for(int w=0;w<columsName.length;w++){
                        sheet.autoSizeColumn(w , true);
                    }
                    for(int w=0;w<columsName.length;w++){
                    	int curColWidth = sheet.getColumnWidth(w);  
                    	if(curColWidth<35 * 150){
                    		sheet.setColumnWidth(w, (35 * 150));
                    	}
                    }
                }
        	}
        	if(modNum>0){
        		 // 创建第一个sheet（页），并命名
                Sheet sheet = wb.createSheet(sheetName);
                // 创建第一行
                Row row = sheet.createRow(0);
                if(columsName==null||columsName.length<1){
                 	for(int k=0;k<fields.length;k++){
                 		//设置列名
                 		Cell cell = row.createCell(k);
                 		cell.setCellValue(fields[k].getName());
                 		cell.setCellStyle(cs);
                 	}
                 }else{
                     //设置列名
                     for(int i=0;i<columsName.length;i++){
                         Cell cell = row.createCell(i);
                         cell.setCellValue(columsName[i]);
                         cell.setCellStyle(cs);
                     }
                 }
                //设置每行每列的值
                for (int i = 0; i <modNum; i++) {
                	//创建一行
                	Row row_c = sheet.createRow(i+1);
                    //获取表格数据
                	for(int j=0;j<fields.length;j++){
                		 PropertyDescriptor pd;
        				try {
        					pd = new PropertyDescriptor(fields[j].getName(),list.get(i).getClass());
        				 	Method getMethod = pd.getReadMethod();//获得get方法  
                         	Object o;
                         	o = getMethod.invoke(list.get(i));
                         	//执行get方法返回一个Object
                            //创建一个单元格
                            Cell cell = row_c.createCell(j);
                            cell.setCellValue((o==null?" ":o.toString()));
                            cell.setCellStyle(cs2);
        				} catch (IntrospectionException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        					continue;
        				} catch (IllegalAccessException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        					continue;
        				} catch (IllegalArgumentException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        					continue;
        				} catch (InvocationTargetException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        					continue;
        				}
                	}
                }
                // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
                for(int w=0;w<columsName.length;w++){
                    sheet.autoSizeColumn(w , true);
                }
                for(int w=0;w<columsName.length;w++){
                	int curColWidth = sheet.getColumnWidth(w);  
                	if(curColWidth<35 * 150){
                		sheet.setColumnWidth(w, (35 * 150));
                	}
                }
        	}
        }else{
			// 创建第一个sheet（页），并命名
			Sheet sheet = wb.createSheet(sheetName);
			// 创建第一行
			Row row = sheet.createRow(0);
			if (columsName == null || columsName.length < 1) {
				for (int k = 0; k < fields.length; k++) {
					// 设置列名
					Cell cell = row.createCell(k);
					cell.setCellValue(fields[k].getName());
					cell.setCellStyle(cs);
				}
			} else {
				// 设置列名
				for (int i = 0; i < columsName.length; i++) {
					Cell cell = row.createCell(i);
					cell.setCellValue(columsName[i]);
					cell.setCellStyle(cs);
				}
			}
			// 设置每行每列的值
			for (int i = 1; i <= modNum; i++) {
				// 创建一行
				Row row_c = sheet.createRow(i);
				// 获取表格数据
				for (int j = 0; j < fields.length; j++) {
					PropertyDescriptor pd;
					try {
						pd = new PropertyDescriptor(fields[j].getName(), list
								.get(i).getClass());
						Method getMethod = pd.getReadMethod();// 获得get方法
						Object o;
						o = getMethod.invoke(list.get(i));
						// 执行get方法返回一个Object
						// 创建一个单元格
						Cell cell = row_c.createCell(j);
						cell.setCellValue((o == null ? " " : o.toString()));
						cell.setCellStyle(cs2);
					} catch (IntrospectionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					}
				}
			}
			// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
			for (int w = 0; w < columsName.length; w++) {
				sheet.autoSizeColumn(w, true);
			}
			for (int w = 0; w < columsName.length; w++) {
				int curColWidth = sheet.getColumnWidth(w);
				if (curColWidth < 35 * 150) {
					sheet.setColumnWidth(w, (35 * 150));
				}
			}
        }
        return wb;
    }
}
