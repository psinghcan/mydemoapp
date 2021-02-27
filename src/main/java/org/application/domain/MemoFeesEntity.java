package org.application.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Entity(name="MemoFees")
@Table(name="\"MEMOFEES\"")
public class MemoFeesEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="\"invoiceYear\"")
    @Digits(integer = 4, fraction = 0)
    private Integer invoiceYear;

    @Column(name="\"invoiceMonth\"")
    @Digits(integer = 4, fraction = 0)
    private Integer invoiceMonth;

    @Column(name="\"creationDate\"")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name="\"invoiceDate\"")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @Size(max = 50)
    @Column(length = 50, name="\"franchiseNumber\"")
    private String franchiseNumber;

    public Integer getInvoiceYear() {
        return this.invoiceYear;
    }

    public void setInvoiceYear(Integer invoiceYear) {
        this.invoiceYear = invoiceYear;
    }

    public Integer getInvoiceMonth() {
        return this.invoiceMonth;
    }

    public void setInvoiceMonth(Integer invoiceMonth) {
        this.invoiceMonth = invoiceMonth;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getInvoiceDate() {
        return this.invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getFranchiseNumber() {
        return this.franchiseNumber;
    }

    public void setFranchiseNumber(String franchiseNumber) {
        this.franchiseNumber = franchiseNumber;
    }

}
