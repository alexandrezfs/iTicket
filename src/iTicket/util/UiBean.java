package iTicket.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class UiBean implements Serializable {

    private String contextPath;

    public UiBean() {

    }

    public String getContextPath() {

        ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();

        this.contextPath = eC.getRequestContextPath();

        return contextPath;
    }

    public void setContextPath(String contextPath) {

        this.contextPath = contextPath;
    }

}
