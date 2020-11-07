package ec.com.newsolutions.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.HashMap;
import java.util.Map;

public class EntityNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(Long id) {
        super(ErrorConstants.DEFAULT_TYPE, String.format(ErrorMessageConstants.MSG_ENTITY_NOT_FOUND,id),
            Status.BAD_REQUEST, null, null, null, getAlertParameters());
    }

    private static Map<String, Object> getAlertParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", ErrorConstants.ERR_ENTITY_NOT_FOUND);
        return parameters;
    }
}
