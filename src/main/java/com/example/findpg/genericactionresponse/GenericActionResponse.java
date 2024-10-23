package com.example.findpg.genericactionresponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GenericActionResponse<T> {
    protected Boolean success;
    protected List<T> message;
    protected String errmsg;
    protected String successmsg;
    protected Boolean accessDenied;

    public GenericActionResponse(Boolean success) {
        super();
        this.success = success;
    }
}
