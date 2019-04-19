package com.mod.loan.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	/**
	 * 导出excel
	 * 
	 * @param response
	 * @param fileName
	 * @param workbook
	 * @return
	 */
	public static String excelExp(HttpServletResponse response, String fileName, HSSFWorkbook workbook) {
		OutputStream os = null;
		try {
			// 清空输出流
			response.reset();
			// 定义输出类型
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			// 设置响应头和下载保存的文件名
			response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes( "utf-8" ), "ISO8859-1") + ".xls");
			os = response.getOutputStream();
			workbook.write(os);
			os.flush();
			os.close();
			// 这一行非常关键，否则在实际中有可能出现莫名其妙的问题！！！强行将响应缓存中的内容发送到目的地
			//response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 给excel创建sheet
	 * 
	 * @param workbook
	 * @param fileNameBegin
	 * @param headers
	 * @param list
	 * @return
	 */
	public static HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName, String[] headers, List<Object[]> list) {
		HSSFSheet sheet = workbook.createSheet(sheetName);
		addHeraderCell(headers, sheet);
		addListCell(list, sheet);
		return sheet;
	}

	private static void addListCell(List<Object[]> list, HSSFSheet sheet) {
		int size = list.size();
		int rowStartNum = 1;// 从第2行开始写数据（前几行为表头）

		// 构造值
		HSSFRow row;
		HSSFCell cell;
		Object[] objs = null;
		Object value;
		for (int i = 0; i < size; i++) {
			objs = (Object[]) list.get(i);
			row = sheet.createRow(i + rowStartNum);
			for (int j = 0; j < objs.length; j++) {
				value = objs[j] == null ? "" : objs[j];
				cell = row.createCell(j);
				if (value instanceof Number) {
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					cell.setCellValue(Double.parseDouble(String.valueOf(value)));
				} else {
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(String.valueOf(value));
				}

			}
		}
	}

	private static void addHeraderCell(String[] headers, HSSFSheet sheet) {
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell;
		for (int i = 0; i < headers.length; i++) {
			cell = row.createCell(i);
			// cell.setCellStyle(cellStyle);
			cell.setCellValue(headers[i]);
		}
	}

	public static List<Object[]> mapToArray(List<Map<String, Object>> data, String[] columns) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (Map<String, Object> item : data) {
			Object[] array = new Object[columns.length];
			for (int i = 0; i < columns.length; i++) {
				array[i] = item.get(columns[i]) == null ? "" : item.get(columns[i]);
			}
			list.add(array);
		}
		return list;
	}
}
