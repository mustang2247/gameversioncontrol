package com.open.coinnews.basic.tools;

import org.springframework.data.jpa.domain.Specifications;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ParamFilterTools<T> {

    //参数名前缀
    private static final String PARAM_PRE = "filter_";
    private static final String PARAM_SPE = "-";

    public Specifications<T> buildSpecification(Model model, HttpServletRequest request, Specifications result) {
        Map<String, Object> args = new HashMap<>();
//        Specifications<T> result = null;
        Map<String, String[]> paramMap = request.getParameterMap();
        for(String key : paramMap.keySet()) {
            //这个参数是需要进行过虑的
            if(key.startsWith(PARAM_PRE)) {
                try {
                    String field = key.replaceFirst(PARAM_PRE, ""); //获取出字段名称
                    String parVal = paramMap.get(key)[0];
                    String [] val_array = parVal.split(PARAM_SPE);
                    String operate = val_array[0]; //比较符号
                    String fieldVal = val_array[1]; //对应值
                    BaseSpecification<T> spec = new BaseSpecification<>(new SearchCriteria(field, operate, fieldVal));
                    if(result==null) {
                        result = Specifications.where(spec);
                    } else {
                        result = result.and(spec);
                    }
                    args.put(key, fieldVal);
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }
//            System.out.println(key+"========"+paramMap.get(key).length+"=========="+paramMap.get(key)[0].toString());
        }
        model.addAttribute("args", args);
        return result;
    }

    public Specifications<T> buildSpecification(Model model, HttpServletRequest request) {
        return buildSpecification(model, request, null);
    }
}
