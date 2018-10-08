package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.Student;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StudentRepositoryImpl extends ResourceRepositoryBase<Student, Long> implements  StudentRepository{
    private Map<Long, Student> container = new ConcurrentHashMap<>();
    protected StudentRepositoryImpl(Class<Student> resourceClass) {
        super(resourceClass);
    }

    public StudentRepositoryImpl() {
        super(Student.class);
    }

    @Override
    public <S extends Student> S save(S resource) {
        container.put(resource.getId(), resource);
        return resource;
    }

    @Override
    public void delete(Long id) {
        container.remove(id);
    }

    @Override
    public ResourceList<Student> findAll(QuerySpec querySpec) {
        return querySpec.apply(container.values());
    }
}
