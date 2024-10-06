package common.module.webmvc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResp extends RuntimeException {
    private String msg;
    private String code;

    public ErrorResp(Throwable e) {
        super(e);
        this.msg = e.getMessage();
        this.code = "UNKNOWN_ERROR";
    }
}
