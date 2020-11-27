package ec.com.newsolutions.repository.specification;

import ec.com.newsolutions.domain.Unit;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecificationsBuilder {
    private final List<SearchCriteria> params;

    public SpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public SpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public SpecificationsBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }
    public Specification build() {
        if (params.size() == 0) {
            return null;
        }
        List<Specification> specs = params.stream()
            .map(AbsctractSpecification::new)
            .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }

        return result;
    }

}
