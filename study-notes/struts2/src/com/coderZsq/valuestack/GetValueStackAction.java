package com.coderZsq.valuestack;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;

// 获取ValueStack对象
public class GetValueStackAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        // 方式1
        ValueStack valueStack1 = (ValueStack) ServletActionContext.getRequest().getAttribute("struts.valueStack");
        // 方式2
        ValueStack valueStack2 = (ValueStack) ServletActionContext.getRequest().getAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY);
        // 方式3
        ValueStack valueStack = ActionContext.getContext().getValueStack();

        return NONE;
    }
}
