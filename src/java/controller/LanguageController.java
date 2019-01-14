/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.xml.rpc.encoding.Serializer;

/**
 *
 * @author Vadrama
 */
public class LanguageController implements Serializable{
    private String language = "en";

    /**
     * Creates a new instance of LanguageController
     */
    public LanguageController() {
    }
    
    public String english(){
        language = "en";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }
    
     public String french(){
        language = "fr";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
}
