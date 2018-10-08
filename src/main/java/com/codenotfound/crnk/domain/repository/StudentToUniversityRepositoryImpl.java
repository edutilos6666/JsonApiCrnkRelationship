package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.Student;
import com.codenotfound.crnk.domain.model.University;
import io.crnk.core.repository.RelationshipRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class StudentToUniversityRepositoryImpl extends RelationshipRepositoryBase<Student, Long, University, Long> {
    protected StudentToUniversityRepositoryImpl(Class<Student> sourceResourceClass, Class<University> targetResourceClass) {
        super(sourceResourceClass, targetResourceClass);
    }

    public StudentToUniversityRepositoryImpl() {
        super(Student.class, University.class);
    }
}
