package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.University;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UniversityRepositoryImpl extends ResourceRepositoryBase<University, Long> implements UniversityRepository {
    private Map<Long,University> container = new ConcurrentHashMap<>();

    protected UniversityRepositoryImpl(Class<University> resourceClass) {
        super(resourceClass);
    }

    public UniversityRepositoryImpl() {
        super(University.class);
    }

    @Override
    public <S extends University> S save(S resource) {
        container.put(resource.getId(), resource);
        return resource;
    }

    @Override
    public void delete(Long id) {
        container.remove(id);
    }

    @Override
    public ResourceList<University> findAll(QuerySpec querySpec) {
        return querySpec.apply(container.values());
    }
}
