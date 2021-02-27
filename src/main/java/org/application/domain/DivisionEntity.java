package org.application.domain;

import org.application.domain.security.BaseEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity(name="Division")
@Table(name="DIVISION")
public class DivisionEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @Column(length = 50, name="division")
    private String division;

    @Size(max = 50)
    @Column(length = 50, name="divisionAbbrev")
    private String divisionAbbrev;

    @Size(max = 50)
    @Column(length = 50, name="presidentId")
    private String presidentId;

    @Size(max = 50)
    @Column(length = 50, name="administratorId")
    private String administratorId;

    @Column(name="startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name="endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Size(max = 100)
    @Column(length = 100, name="companyName")
    private String companyName;

    public String getDivision() {
        return this.division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDivisionAbbrev() {
        return this.divisionAbbrev;
    }

    public void setDivisionAbbrev(String divisionAbbrev) {
        this.divisionAbbrev = divisionAbbrev;
    }

    public String getPresidentId() {
        return this.presidentId;
    }

    public void setPresidentId(String presidentId) {
        this.presidentId = presidentId;
    }

    public String getAdministratorId() {
        return this.administratorId;
    }

    public void setAdministratorId(String administratorId) {
        this.administratorId = administratorId;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
