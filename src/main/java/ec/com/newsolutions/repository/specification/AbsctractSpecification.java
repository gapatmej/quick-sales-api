package ec.com.newsolutions.repository.specification;

import ec.com.newsolutions.domain.enumeration.TaxTypeEnum;
import ec.com.newsolutions.repository.enumeration.QueryOperationEnum;
import ec.com.newsolutions.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AbsctractSpecification<T> implements Specification<T> {

    private SearchCriteria criteria;

    public AbsctractSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            if (criteria.getOperation().equalsIgnoreCase(QueryOperationEnum.GREATER_THAN.value())) {
                return criteriaBuilder.greaterThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase(QueryOperationEnum.GREATER_OR_EQUAL.value())) {
                return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase(QueryOperationEnum.LESS_THAN.value())) {
                return criteriaBuilder.lessThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase(QueryOperationEnum.LESS_OR_EQUAL.value())) {
                return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase(QueryOperationEnum.EQUAL.value())) {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
            else if (criteria.getOperation().equalsIgnoreCase(QueryOperationEnum.NOT_EQUAL.value())) {
                return criteriaBuilder.notEqual(root.get(criteria.getKey()),criteria.getValue());
            }
            else if (criteria.getOperation().equalsIgnoreCase(QueryOperationEnum.IN.value())) {
                return root.get(criteria.getKey()).in(Utils.stringToList(criteria.getValue().toString(),QueryOperationEnum.IN_SEPARATOR.value()));
            }
            else if (criteria.getOperation().equalsIgnoreCase(QueryOperationEnum.NOT_IN.value())) {
                return root.get(criteria.getKey()).in(Utils.stringToList(criteria.getValue().toString(),QueryOperationEnum.IN_SEPARATOR.value())).not();
            }
            else if (criteria.getOperation().equalsIgnoreCase(QueryOperationEnum.LIKE.value())) {
                if (root.get(criteria.getKey()).getJavaType() == TaxTypeEnum.class) {
                    return criteriaBuilder.equal(root.get(criteria.getKey()), TaxTypeEnum.valueOf(criteria.getValue().toString()));
                }
                else if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return criteriaBuilder.like(criteriaBuilder.lower(
                        root.<String>get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
                } else {
                    return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            }
            return null;
    }
}
