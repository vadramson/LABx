/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Region;
import entities.Ville;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
import session.RegionFacadeLocal;
import session.VilleFacadeLocal;

/**
 *
 * @author Vadrama
 */
@Named(value = "villeController")
@SessionScoped
public class villeController implements Serializable {
    
    @EJB
    private VilleFacadeLocal villeFacde;
    private Ville ville = new Ville();
    private List<Ville> listOfVille = new ArrayList<>();
    private Ville selectedVille;
    private String operation;
    private String nom;
    String msg;
    String msg1;
    
    @EJB    
    private RegionFacadeLocal regionFacde;
    private List<Region> listOfRegions = new ArrayList<>();
    private Region region = new Region();
    /**
     * Creates a new instance of villeController
     */
    public villeController() {
    }
    
    @PostConstruct
    public void init() {
        listOfRegions.clear();
        listOfRegions.addAll(regionFacde.findAll());

        listOfVille.clear();
        listOfVille.addAll(villeFacde.findAll());
    }
    
     public VilleFacadeLocal getVilleFacde() {
        return villeFacde;
    }

    public void setVilleFacde(VilleFacadeLocal villeFacde) {
        this.villeFacde = villeFacde;
    }

    public Ville getSelectedVille() {
        return selectedVille;
    }

    public void setSelectedVille(Ville selectedVille) {
        this.selectedVille = selectedVille;
    }

    public List<Ville> getListOfVille() {
        return listOfVille;
    }

    public void setListOfVille(List<Ville> listOfVille) {
        this.listOfVille = listOfVille;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public RegionFacadeLocal getRegionFacde() {
        return regionFacde;
    }

    public void setRegionFacde(RegionFacadeLocal regionFacde) {
        this.regionFacde = regionFacde;
    }

    public List<Region> getListOfRegions() {
        return listOfRegions;
    }

    public void setListOfRegions(List<Region> listOfRegions) {
        this.listOfRegions = listOfRegions;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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
        
    public void prepareCreate() {
        setOperation("add"); 
        System.out.println("Prepare Create New Ville");
        ville = new Ville();
        msg = "";
//        action(e);       
    }
    
    public void prepareEdit() {
       setOperation("modify"); 
       System.out.println("operation Edit");
        msg = "";
        System.out.println(""+region.toString());
    }
    
    public void prepareDelete() {
       setOperation("delete");  
       System.out.println("operation Delete");
        msg = "";
        System.out.println(""+ville.toString());
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
     
    java.util.Date uDate = new java.util.Date();
    java.sql.Date sDate = convertUtilToSql(uDate);  
      
     public void persist() {

        switch (operation) {

            case "add":
                saveVille();
                break;
                
            case "modify":
                modifyVille();
                
                break; 
                
            case "delete":
                deleteVille();

                break;
        }
       region = new Region();
    }
     
    public void saveVille() {       
        System.out.println("Région is ");
        System.out.println("Région is " + ville.getRegion());
        try {
            ville.setId(villeFacde.next_Id());
            ville.setCreated(sDate);
            System.out.println("Region is " + ville.getRegion());
            ville.setDeleted(0);
            villeFacde.create(ville);            
            msg = "Opération éffectuer avec succès";
            msg1 = "Nouvelle Ville " + ville.getNom() + " Créée avec succès";
            sendMessageInfo(msg, msg1);
        } catch (Exception e) { 
           e.printStackTrace();
            msg = "Echec de l'opération ";
            msg1 = "La Ville " + ville.getNom() + " N'a pas pu etre Créée!";
            sendMessageError(msg, msg1);
        } finally {             
             RequestContext.getCurrentInstance().execute("PF('e_dialog').hide()");
             init();            
        }
    }
    
     public void modifyVille() {
        try {
            ville.setMidified(sDate);
            villeFacde.edit(ville);
            RequestContext.getCurrentInstance().execute("PF('e_dialog').hide()");
            msg = "Opération éffectuer avec succes";
            msg1 = "Ville " + ville.getNom() + " Modifiée avec succès";
            sendMessageInfo(msg, msg1);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Echec de l'opération ";
            msg1 = "Ŀa ville " + ville.getNom() + " N'a pas pu etre modifiée!";
            sendMessageError(msg, msg1);
        } finally {
            init();            
        }
    }
     
     
   public void deleteVille() {
        try {
            System.out.println("Ville Delete");
            ville.setDeleted(1);
            villeFacde.edit(ville);
            RequestContext.getCurrentInstance().execute("PF('e_dialog_del').hide()");
            msg = "Opération éffectuer avec succès";
            msg1 = "Ville " + ville.getNom() + " Supprimer avec succès";
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
