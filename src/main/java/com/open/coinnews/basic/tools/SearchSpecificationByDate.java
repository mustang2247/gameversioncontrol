package com.open.coinnews.basic.tools;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * 根据时间查询
 */
public class SearchSpecificationByDate<T> implements Specification<T> {

    public static final String GRATE_EQUAL = "ge"; //大于等于
    public static final String GRATE_THEN = "gt"; //大于
    public static final String LESS_EQUAL = "le"; //小于等于
    public static final String LESS_THEN = "lt"; //小于
    public static final String LIKE_BEGIN = "likeb"; // like '%?'
    public static final String LIKE_END = "likee"; //like '?%'
    public static final String LIKE = "like"; //like '%?%'
    public static final String LIKE_BEGIN_END = "likebe"; //like '%?%'
    public static final String NOT_LIKE_BEGIN = "nlikeb"; //not like '%?'
    public static final String NOT_LIKE_END = "nlikee"; //not like '?%'
    public static final String NOT_LIKE = "nlike"; //not like '%?%'
    public static final String NOT_LIKE_BEGIN_END = "nlikebe"; // not like '%?%'
    public static final String EQUAL = "eq"; //equal =
    public static final String NOT_EQUAL = "neq"; // not equal   !=
    public static final String IS_NULL = "isnull"; //is null

    private SearchCriteria criteria;
    private Date startDate;
    private Date endDate;

    public SearchSpecificationByDate(SearchCriteria criteria,
                                     final Date startDate,
                                     final Date endDate) {
        this.criteria = criteria;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        Predicate a1 = null;
        if (criteria != null){
            String opt = criteria.getOperation();
            String key = criteria.getKey();
            String value = criteria.getValue().toString();
            if (opt.equalsIgnoreCase(GRATE_EQUAL)) { //大于等于
                a1 = builder.greaterThanOrEqualTo(
                        root.<String> get(key), value);
            } else if(opt.equalsIgnoreCase(GRATE_THEN)) { //大于
                a1 = builder.greaterThan(root.get(key), value);
            } else if(opt.equalsIgnoreCase(LESS_EQUAL)) { //小于等于
                a1 = builder.lessThanOrEqualTo(root.get(key), value);
            } else if(opt.equalsIgnoreCase(LESS_THEN)) { //小于
                a1 = builder.lessThan(root.get(key), value);
            } else if(opt.equalsIgnoreCase(LIKE_BEGIN)) { // like '%?'
                a1 = builder.like(root.get(key), "%"+value);
            } else if(opt.equalsIgnoreCase(LIKE_END)) { // like '?%'
                a1 = builder.like(root.get(key), value+"%");
            } else if(opt.equalsIgnoreCase(LIKE) || opt.equalsIgnoreCase(LIKE_BEGIN_END)) { //like '%?%'
                a1 = builder.like(root.get(key), "%"+value+"%");
            } else if(opt.equalsIgnoreCase(NOT_LIKE_BEGIN)) { // not like '%?'
                a1 = builder.notLike(root.get(key), "%"+value);
            } else if(opt.equalsIgnoreCase(NOT_LIKE_END)) { // not like '?%'
                a1 = builder.notLike(root.get(key), value + "%");
            } else if(opt.equalsIgnoreCase(NOT_LIKE) || opt.equalsIgnoreCase(NOT_LIKE_BEGIN_END)) { //not like '%?%'
                a1 = builder.notLike(root.get(key), "%"+value+"%");
            } else if(opt.equalsIgnoreCase(EQUAL)) { //equal
                a1 = builder.equal(root.get(key), value);
            } else if(opt.equalsIgnoreCase(NOT_EQUAL)) { //not equal
                a1 = builder.notEqual(root.get(key), value);
            } else if(opt.equalsIgnoreCase(IS_NULL)) { // is null
                a1 = builder.isNull(root.get(key));
            }
        }

        Predicate between = builder.between(root.get("createDate"), startDate, endDate);

        if(a1 != null){
            return builder.and(a1, between);
        }
        return between;
    }
}
