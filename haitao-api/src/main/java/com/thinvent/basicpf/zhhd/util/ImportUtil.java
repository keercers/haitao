package com.thinvent.basicpf.zhhd.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinvent.basicpf.sys.adapt.impl.UserAdaptImpl;
import com.thinvent.library.exception.ThinventBaseException;
import com.thinvent.library.vo.UserVO;
public class ImportUtil {
	
	
	UserAdaptImpl userAdaptImpl = new UserAdaptImpl();
	
	public Boolean checkUserScopeList(Row row, int index) throws ThinventBaseException{
		Cell cell = row.getCell(index);
		@SuppressWarnings("unchecked")
		Map<String, JSONArray> userMap = userAdaptImpl.listAllUser();
		String listValue = null;
		listValue = userMap.get("userList").toJSONString();
		  List<UserVO> urlist = JSONObject.parseArray(listValue, UserVO.class);
		for(int i= 0; i < urlist .size(); i++){
			if(urlist .get(i).getUserName().equals(cell.getStringCellValue())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isRowEmpty(Row row) {
		   for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
		       Cell cell = row.getCell(c);
		       if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
		           return false;
		   }
		   return true;
		}
	public String parseExcel(Cell cell) {  
        String result = null;  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型  
        	result = findResult(cell);
            break;  
        case HSSFCell.CELL_TYPE_STRING:// String类型  
            result = cell.getRichStringCellValue().toString();  
            break;  
        case HSSFCell.CELL_TYPE_BLANK:  
            result = "";
            break;
        default:  
            result = "";  
            break;  
        }  
        return result;  
    }
	private String findResult(Cell cell) {
		if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
            SimpleDateFormat sdf = null;  
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat  
                    .getBuiltinFormat("h:mm")) {  
                sdf = new SimpleDateFormat("HH:mm");  
            } else {// 日期  
                sdf = new SimpleDateFormat("yyyy-MM-dd");  
            }  
            Date date = cell.getDateCellValue();
            return sdf.format(date);
        } else if (cell.getCellStyle().getDataFormat() == 58) {  
            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            double value = cell.getNumericCellValue();  
            Date date = org.apache.poi.ss.usermodel.DateUtil  
                    .getJavaDate(value); 
            return sdf.format(date);
        } else {  
            double value = cell.getNumericCellValue();  
            CellStyle style = cell.getCellStyle();  
            DecimalFormat format = new DecimalFormat();  
            String temp = style.getDataFormatString();  
            // 单元格设置成常规  
            if (("General").equals(temp)) {  
                format.applyPattern("#");  
            }
            return format.format(value);
        }
	}  
}
