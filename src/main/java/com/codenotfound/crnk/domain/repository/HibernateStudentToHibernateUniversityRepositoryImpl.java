package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.HibernateStudent;
import com.codenotfound.crnk.domain.model.HibernateUniversity;
import io.crnk.core.repository.RelationshipRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class HibernateStudentToHibernateUniversityRepositoryImpl extends RelationshipRepositoryBase<HibernateStudent,Long,HibernateUniversity, Long> {
    protected HibernateStudentToHibernateUniversityRepositoryImpl(Class<HibernateStudent> sourceResourceClass, Class<HibernateUniversity> targetResourceClass) {
        super(sourceResourceClass, targetResourceClass);
    }

    public HibernateStudentToHibernateUniversityRepositoryImpl() {
        super(HibernateStudent.class, HibernateUniversity.class);
    }
}
