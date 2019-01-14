/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Pays;
import entities.Region;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import session.PaysFacadeLocal;
import session.RegionFacadeLocal;

/**
 *
 * @author Vadrama
 */
@Named(value = "regionsController")
@SessionScoped
public class regionsController implements Serializable {

    @EJB
    private RegionFacadeLocal rigionFacde;
    private Region selectedRegion;
    private List<Region> listOfRegion = new ArrayList<>();
    private Region region = new Region();
    private String operation;
    private String nom;
    String msg;
    String msg1;
    
    @EJB    
    private PaysFacadeLocal payFacde;
    private List<Pays> listOfPays = new ArrayList<>();
    private Pays pays = new Pays();
    
    /**
     * Creates a new instance of regionsController
     */
    public regionsController() {
    }
    
    @PostConstruct
    public void init() {
        listOfPays.clear();
        listOfPays.addAll(payFacde.findAll());

        listOfRegion.clear();
        listOfRegion.addAll(rigionFacde.findAll());
    }

    public RegionFacadeLocal getRigionFacde() {
        return rigionFacde;
    }

    public void setRigionFacde(RegionFacadeLocal rigionFacde) {
        this.rigionFacde = rigionFacde;
    }

    public Region getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(Region selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

    public List<Region> getListOfRegion() {
        return listOfRegion;
    }

    public void setListOfRegion(List<Region> listOfRegion) {
        this.listOfRegion = listOfRegion;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public PaysFacadeLocal getPayFacde() {
        return payFacde;
    }

    public void setPayFacde(PaysFacadeLocal payFacde) {
        this.payFacde = payFacde;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getuDate() {
        return uDate;
    }

    public void setuDate(Date uDate) {
        this.uDate = uDate;
    }

    public java.sql.Date getsDate() {
        return sDate;
    }

    public void setsDate(java.sql.Date sDate) {
        this.sDate = sDate;
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
        
    public void prepareCreate(ActionEvent e) {
        System.out.println("Prep");
        region = new Region();
        msg = "";
        action(e);       
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
        System.out.println(""+region.toString());
    } 
    
     public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        msg = "";
    } 
    
      private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

     
      
    java.util.Date uDate = new java.util.Date();
    java.sql.Date sDate = convertUtilToSql(uDate);
//    DateFormat df = new SimpleDateFormat("yyyy-MM-dd - hh:mm:ss");

    public void persist() {

        switch (operation) {

            case "add":
                saveRegions();
                break;
                
            case "modify":
                modifyRegion();
                
                break; 
                
            case "delete":
                deleteRegion();

                break;
        }
       region = new Region();
    }
     
    public void saveRegions() {            
        try {
            region.setId(rigionFacde.nextId());
            region.setCreated(sDate);
            System.out.println("Pays is " + region.getPays());
            region.setDeleted(0);
            rigionFacde.create(region);            
            msg = "Opération éffectuer avec succès";
            msg1 = "Nouvelle Région " + region.getNom() + " Créée avec succès";
            sendMessageInfo(msg, msg1);
        } catch (Exception e) { 
           e.printStackTrace();
            msg = "Echec de l'opération ";
            msg1 = "Nouvelle Region " + region.getNom() + " N'a pas pu etre crere!";
            sendMessageError(msg, msg1);
        } finally {             
             RequestContext.getCurrentInstance().execute("PF('e_dialog').hide()");
             init();            
        }
    }
    
     public void modifyRegion() {
        try {
            region.setModified(sDate);
            rigionFacde.edit(region);
            RequestContext.getCurrentInstance().execute("PF('e_dialog').hide()");
            msg = "Opération éffectuer avec succes";
            msg1 = "Région " + region.getNom() + " Modifiée avec succès";
            sendMessageInfo(msg, msg1);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération ";
            msg1 = "Ŀa région " + region.getNom() + " N'a pas pu etre modifiée!";
            sendMessageError(msg, msg1);
        } finally {
            init();            
        }
    }

        public void deleteRegion() {
        try {
            System.out.println("Region Delete");
            region.setDeleted(1);
            rigionFacde.edit(region);
            RequestContext.getCurrentInstance().execute("PF('e_dialog_del').hide()");
            msg = "Opération éffectuer avec succès";
            msg1 = "Ville " + region.getNom() + " Supprimer avec succès";
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
