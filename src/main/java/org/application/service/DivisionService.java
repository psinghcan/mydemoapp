package org.application.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.application.domain.DivisionEntity;
import org.primefaces.model.SortOrder;

@Named
public class DivisionService extends BaseService<DivisionEntity> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public DivisionService(){
        super(DivisionEntity.class);
    }
    
    @Transactional
    public List<DivisionEntity> findAllDivisionEntities() {
        
        return entityManager.createQuery("SELECT o FROM Division o ", DivisionEntity.class).getResultList();
    }
    
    @Override
    @Transactional
    public long countAllEntries() {
        return entityManager.createQuery("SELECT COUNT(o) FROM Division o", Long.class).getSingleResult();
    }
    
    @Override
    protected void handleDependenciesBeforeDelete(DivisionEntity division) {

        /* This is called before a Division is deleted. Place here all the
           steps to cut dependencies to other entities */
        
    }

    // This is the central method called by the DataTable
    @Override
    @Transactional
    public List<DivisionEntity> findEntriesPagedAndFilteredAndSorted(int firstResult, int maxResults, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        
        StringBuilder query = new StringBuilder();

        query.append("SELECT o FROM Division o");
        
        String nextConnective = " WHERE";
        
        Map<String, Object> queryParameters = new HashMap<>();
        
        if (filters != null && !filters.isEmpty()) {
            
            nextConnective += " ( ";
            
            for(String filterProperty : filters.keySet()) {
                
                if (filters.get(filterProperty) == null) {
                    continue;
                }
                
                switch (filterProperty) {
                
                case "division":
                    query.append(nextConnective).append(" o.division LIKE :division");
                    queryParameters.put("division", "%" + filters.get(filterProperty) + "%");
                    break;

                case "divisionAbbrev":
                    query.append(nextConnective).append(" o.divisionAbbrev LIKE :divisionAbbrev");
                    queryParameters.put("divisionAbbrev", "%" + filters.get(filterProperty) + "%");
                    break;

                case "presidentId":
                    query.append(nextConnective).append(" o.presidentId LIKE :presidentId");
                    queryParameters.put("presidentId", "%" + filters.get(filterProperty) + "%");
                    break;

                case "administratorId":
                    query.append(nextConnective).append(" o.administratorId LIKE :administratorId");
                    queryParameters.put("administratorId", "%" + filters.get(filterProperty) + "%");
                    break;

                case "startDate":
                    query.append(nextConnective).append(" o.startDate = :startDate");
                    queryParameters.put("startDate", filters.get(filterProperty));
                    break;

                case "endDate":
                    query.append(nextConnective).append(" o.endDate = :endDate");
                    queryParameters.put("endDate", filters.get(filterProperty));
                    break;

                case "companyName":
                    query.append(nextConnective).append(" o.companyName LIKE :companyName");
                    queryParameters.put("companyName", "%" + filters.get(filterProperty) + "%");
                    break;

                }
                
                nextConnective = " AND";
            }
            
            query.append(" ) ");
            nextConnective = " AND";
        }
        
        if (sortField != null && !sortField.isEmpty()) {
            query.append(" ORDER BY o.").append(sortField);
            query.append(SortOrder.DESCENDING.equals(sortOrder) ? " DESC" : " ASC");
        }
        
        TypedQuery<DivisionEntity> q = this.entityManager.createQuery(query.toString(), this.getType());
        
        for(String queryParameter : queryParameters.keySet()) {
            q.setParameter(queryParameter, queryParameters.get(queryParameter));
        }

        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
