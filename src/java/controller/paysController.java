/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Pays;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import session.PaysFacadeLocal;

/**
 *
 * @author Vadrama
 */
@Named(value = "paysController")
@SessionScoped
public class paysController implements Serializable {

    /**
     * Creates a new instance of paysController
     */
    
    @EJB
    private PaysFacadeLocal payFacde;
    private Pays selectedPays;
    private List<Pays> listOfPays = new ArrayList<>();
    private Pays pays = new Pays();
    private String operation;
    String msg;
    String msg1;
    
    private String nom;
    private String iso2;
    private String iso3;
    private Integer devise;
    private Date created;
    private Date modified;
    private Integer deleted;
    
    public paysController() throws ParseException {
       
    }
    
    @PostConstruct
    public void init() {
        listOfPays.clear();
        listOfPays.addAll(payFacde.findAll());

    }

    public PaysFacadeLocal getPayFacde() {
        return payFacde;
    }

    public void setPayFacde(PaysFacadeLocal payFacde) {
        this.payFacde = payFacde;
    }

    public Pays getSelectedPays() {
        return selectedPays;
    }

    public void setSelectedPays(Pays selectedPays) {
        this.selectedPays = selectedPays;
    }

    public List<Pays> getListOfPays() {
        return listOfPays;
    }

    public void setListOfPays(List<Pays> listOfPays) {
        this.listOfPays = listOfPays;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Integer getDevise() {
        return devise;
    }

    public void setDevise(Integer devise) {
        this.devise = devise;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public java.util.Date getuDate() {
        return uDate;
    }

    public void setuDate(java.util.Date uDate) {
        this.uDate = uDate;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public DateFormat getDf() {
        return df;
    }

    public void setDf(DateFormat df) {
        this.df = df;
    }        
    
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    
    public void prepareCreate(ActionEvent e) {
        pays = new Pays();
        msg = "";
        action(e);
    }
    
    public void sendMessageInfo(String msg, String msg1) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext context =FacesContext.getCurrentInstance(); 
        context.addMessage(null, new FacesMessage(msg, msg1));
    }
    
    public void sendMessageError(String msg, String msg1) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext context =FacesContext.getCurrentInstance(); 
        context.addMessage(null, new FacesMessage(msg, msg1));
    }    
    
    public void prepareEdit() {
       setOperation("modify"); 
       System.out.println("operation Edit");
        msg = "";
        System.out.println(""+pays.toString());
    }
    
    public void prepareDelete() {
       setOperation("delete");  
       System.out.println("operation Delete");
        msg = "";
        System.out.println(""+pays.toString());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        msg = "";
    }

    /**
     *
     * @return 
     */
   

private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
	        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
	        return sDate;
	    }

 java.util.Date uDate = new java.util.Date();
 java.sql.Date sDate = convertUtilToSql(uDate);
 DateFormat df = new SimpleDateFormat("yyyy-MM-dd - hh:mm:ss");
    
    public void persist() {

        switch (operation) {

            case "add":
                savePays();
                break;
                
            case "modify":
                modifyPays();
                
                break; 
                
            case "delete":
                deletePays();

                break;
        }
       pays = new Pays();
    }
    
    public void savePays() {

        try {
            pays.setId(payFacde.nextId());
            pays.setCreated(sDate);
            pays.setDeleted(0);
            payFacde.create(pays);
            RequestContext.getCurrentInstance().execute("PF('e_dialog').hide()");
            
            msg = "Opération éffectuer avec succès";
            msg1 = "Nouveau Pays " + pays.getNom() + " Créé avec succès";
            sendMessageInfo(msg, msg1);
        } catch (Exception e) { 
           e.printStackTrace();
            msg = "Echec de l'opération ";
        } finally {
            init();            
        }
    }
    
    public void modifyPays() {
        try {
            pays.setModified(sDate);
            payFacde.edit(pays);
            RequestContext.getCurrentInstance().execute("PF('e_dialog').hide()");
            msg = "Opération éffectuer avec succès";
            msg1 = "Pays " + pays.getNom() + " Modifier avec succès";
            sendMessageInfo(msg, msg1);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération ";             
            msg1 = "Le Pays " + pays.getNom() + "n'a pas pu etre Créé";
            sendMessageInfo(msg, msg1);
        } finally {
            init();            
        }
    }
    
    public void deletePays() {
        try {
            System.out.println("Pays Delete");
            pays.setDeleted(1);
            payFacde.edit(pays);
            RequestContext.getCurrentInstance().execute("PF('e_dialog_del').hide()");
            msg = "Opération éffectuer avec succès";
            msg1 = "Pays " + pays.getNom() + " Modifier avec succès";
            sendMessageInfo(msg, msg1);
            init();            
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération ";
        } finally {
            init();            
        }
    }
    
    
}
