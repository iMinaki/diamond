package person.marlon.diamond.common.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import person.marlon.diamond.common.enums.SortOrderEnum;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.common.generic.Sort;

import java.util.Map;

public class GenericUtil {

	/**
	 * 将驼峰格式转换成数据库下划线形式：
	 *  TestData-->test_data
	 */
	private String convertCamelToUnderlinePattern(String field){
		return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
	}
	
	public static Page map2Page(Map<String,Object> paramMap,String defaultSortField){
		
		Page page;
		
		if(paramMap == null){
			page = new Page();
		}else{
			if(paramMap.get("pageNum") != null && paramMap.get("pageSize") != null){
				
				int pageNum = Integer.parseInt((String)paramMap.get("pageNum"));// 前端传过来是String
				int pageSize = Integer.parseInt((String)paramMap.get("pageSize"));
				page = new Page(pageNum,pageSize);
				
			}else if(paramMap.get("pageNum") != null /*&& paramMap.get("pageSize") == null */) {
				int pageNum = Integer.parseInt((String)paramMap.get("pageNum"));
				page = new Page(pageNum);
			}else{
				page = new Page();
			}
			
			String sortField = (String)paramMap.get("sortField");
			Sort sort = null;
			if(sortField != null){
				//sortType
				if(SortOrderEnum.ASC.getValue().equals(paramMap.get("sortType"))){
					sort = new Sort(sortField,true);
				}else{
					sort = new Sort(sortField);
				}
			}else{
				if(StringUtils.isNotEmpty(defaultSortField)){
					sort = new Sort(defaultSortField);
				}
				//else{
				//	sort = new Sort();
				//}
			}
			if(sort != null){
				page.setSort(sort);
			}
		}
		
		
		
		return page;
	}
}
