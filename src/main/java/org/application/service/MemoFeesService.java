package org.application.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.application.domain.MemoFeesEntity;

@Named
public class MemoFeesService extends BaseService<MemoFeesEntity> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public MemoFeesService(){
        super(MemoFeesEntity.class);
    }
    
    @Transactional
    public List<MemoFeesEntity> findAllMemoFeesEntities() {
        
        return entityManager.createQuery("SELECT o FROM MemoFees o ", MemoFeesEntity.class).getResultList();
    }
    
    @Override
    @Transactional
    public long countAllEntries() {
        return entityManager.createQuery("SELECT COUNT(o) FROM MemoFees o", Long.class).getSingleResult();
    }
    
    @Override
    protected void handleDependenciesBeforeDelete(MemoFeesEntity memoFees) {

        /* This is called before a MemoFees is deleted. Place here all the
           steps to cut dependencies to other entities */
        
    }

}
