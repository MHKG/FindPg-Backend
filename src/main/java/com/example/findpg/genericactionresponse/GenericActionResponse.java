package com.example.findpg.genericactionresponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
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

    @Override
    public String toString() {
        return "GenericActionResponse{"
                + Optional.ofNullable(success).map(mn -> "success=" + success).orElse("")
                + Optional.ofNullable(message).map(mn -> ", message=" + message).orElse("")
                + Optional.ofNullable(errmsg).map(mn -> ", errmsg=" + errmsg).orElse("")
                + '}';
    }
}
