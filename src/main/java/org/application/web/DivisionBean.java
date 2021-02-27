package org.application.web;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.application.domain.DivisionEntity;
import org.application.service.DivisionService;
import org.application.service.security.SecurityWrapper;
import org.application.web.generic.GenericLazyDataModel;
import org.application.web.util.MessageFactory;

@Named("divisionBean")
@ViewScoped
public class DivisionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(DivisionBean.class.getName());
    
    private GenericLazyDataModel<DivisionEntity> lazyModel;
    
    private DivisionEntity division;
    
    @Inject
    private DivisionService divisionService;
    
    public void prepareNewDivision() {
        reset();
        this.division = new DivisionEntity();
        // set any default values now, if you need
        // Example: this.division.setAnything("test");
    }

    public GenericLazyDataModel<DivisionEntity> getLazyModel() {
        if (this.lazyModel == null) {
            this.lazyModel = new GenericLazyDataModel<>(divisionService);
        }
        return this.lazyModel;
    }
    
    public String persist() {

        if (division.getId() == null && !isPermitted("division:create")) {
            return "accessDenied";
        } else if (division.getId() != null && !isPermitted(division, "division:update")) {
            return "accessDenied";
        }
        
        String message;
        
        try {
            
            if (division.getId() != null) {
                division = divisionService.update(division);
                message = "message_successfully_updated";
            } else {
                division = divisionService.save(division);
                message = "message_successfully_created";
            }
        } catch (OptimisticLockException e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_optimistic_locking_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_save_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }
        
        FacesMessage facesMessage = MessageFactory.getMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        
        return null;
    }
    
    public String delete() {
        
        if (!isPermitted(division, "division:delete")) {
            return "accessDenied";
        }
        
        String message;
        
        try {
            divisionService.delete(division);
            message = "message_successfully_deleted";
            reset();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_delete_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }
        FacesContext.getCurrentInstance().addMessage(null, MessageFactory.getMessage(message));
        
        return null;
    }
    
    public void reset() {
        division = null;

    }

    public DivisionEntity getDivision() {
        // Need to check for null, because some strange behaviour of f:viewParam
                // Sometimes it is setting to null
        if (this.division == null) {
            prepareNewDivision();
        }
        return this.division;
    }
    
    public void setDivision(DivisionEntity division) {
            if (division != null) {
        this.division = division;
            }
    }
    
    public boolean isPermitted(String permission) {
        return SecurityWrapper.isPermitted(permission);
    }

    public boolean isPermitted(DivisionEntity division, String permission) {
        
        return SecurityWrapper.isPermitted(permission);
        
    }
    
}
