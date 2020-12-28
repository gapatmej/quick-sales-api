package ec.com.newsolutions.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String ERR_CONSTRAINT_VIOLATION = "error.constraintViolation";
    public static final String ERR_ID_EXISTS = "error.idexists";
    public static final String ERR_INVALID_ID = "error.invalidId";
    public static final String ERR_ENTITY_NOT_FOUND = "error.entityNotFound";
    public static final String ERR_WORKSPACE_NOT_FOUND = "error.workspaceNotFound";
    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");
    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");


    private ErrorConstants() {
    }
}
