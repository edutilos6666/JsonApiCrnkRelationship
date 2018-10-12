package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.HibernateStudent;
import com.codenotfound.crnk.domain.model.HibernateUniversity;
import io.crnk.core.repository.RelationshipRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class HibernateUniversityToHibernateStudentRepositoryImpl extends RelationshipRepositoryBase<HibernateUniversity,Long, HibernateStudent, Long> {
    protected HibernateUniversityToHibernateStudentRepositoryImpl(Class<HibernateUniversity> sourceResourceClass, Class<HibernateStudent> targetResourceClass) {
        super(sourceResourceClass, targetResourceClass);
    }

    public HibernateUniversityToHibernateStudentRepositoryImpl() {
        super(HibernateUniversity.class, HibernateStudent.class);
    }
}
