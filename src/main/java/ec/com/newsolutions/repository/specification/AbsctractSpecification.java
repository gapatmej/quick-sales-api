package ec.com.newsolutions.repository.specification;

import ec.com.newsolutions.domain.enumeration.TaxTypeEnum;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AbsctractSpecification<T> implements Specification<T> {

    private SearchCriteria criteria;

    public AbsctractSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            if (criteria.getOperation().equalsIgnoreCase(">")) {
                return criteriaBuilder.greaterThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase(">=")) {
                return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase("<")) {
                return criteriaBuilder.lessThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase("<=")) {
                return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase("=")) {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
            else if (criteria.getOperation().equalsIgnoreCase(":")) {
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
