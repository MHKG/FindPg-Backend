package com.example.findpg.genericactionresponse;

import java.util.List;
import java.util.Optional;

public class GenericActionResponse<T> {
    protected Boolean success;
    protected List<T> message;
    protected String errmsg;
    protected String successmsg;
    protected Boolean accessDenied;

    public GenericActionResponse() {}

    public GenericActionResponse(Boolean success) {
        super();
        this.success = success;
    }

    public GenericActionResponse(Boolean success, List<T> messages) {
        super();
        this.success = success;
        this.message = messages;
    }

    public GenericActionResponse(Boolean success, String errmsg) {
        super();
        this.success = success;
        this.errmsg = errmsg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getSuccessmsg() {
        return successmsg;
    }

    public void setSuccessmsg(String successmsg) {
        this.successmsg = successmsg;
    }

    public List<T> getMessage() {
        return message;
    }

    public void setMessage(List<T> messages) {
        this.message = messages;
    }

    public Boolean getAccessDenied() {
        return accessDenied;
    }

    public void setAccessDenied(Boolean accessDenied) {
        this.accessDenied = accessDenied;
    }

    @Override
    public String toString() {
        return "GenericActionResponse{"
                + Optional.ofNullable(success).map(mn -> "success=" + success).orElse("")
                + Optional.ofNullable(message).map(mn -> ", message=" + message).orElse("")
                + Optional.ofNullable(errmsg).map(mn -> ", errmsg=" + errmsg).orElse("")
                + '}';
    }
}
