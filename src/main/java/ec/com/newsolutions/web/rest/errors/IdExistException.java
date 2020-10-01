package ec.com.newsolutions.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.HashMap;
import java.util.Map;

public class IdExistException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;
    private final String entityName;

    public IdExistException(String entityName) {
        super(ErrorConstants.DEFAULT_TYPE, String.format(ErrorMessageConstants.MSG_ID_EXISTS,entityName),
            Status.BAD_REQUEST, null, null, null, getAlertParameters(entityName));
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    private static Map<String, Object> getAlertParameters(String entityName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", ErrorConstants.ERR_ID_EXISTS);
        parameters.put("params", entityName);
        return parameters;
    }
}
