package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.Student;
import com.codenotfound.crnk.domain.model.University;
import io.crnk.core.repository.RelationshipRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class UniversityToStudentRepositoryImpl extends RelationshipRepositoryBase<University,Long, Student, Long> {
    protected UniversityToStudentRepositoryImpl(Class<University> sourceResourceClass, Class<Student> targetResourceClass) {
        super(sourceResourceClass, targetResourceClass);
    }

    public UniversityToStudentRepositoryImpl() {
        super(University.class, Student.class);
    }
}
