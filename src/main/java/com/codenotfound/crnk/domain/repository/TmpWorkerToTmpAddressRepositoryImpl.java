package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.TmpAddress;
import com.codenotfound.crnk.domain.model.TmpWorker;
import io.crnk.core.repository.RelationshipRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class TmpWorkerToTmpAddressRepositoryImpl
    extends RelationshipRepositoryBase<TmpWorker, Long, TmpAddress, Long> {

  public TmpWorkerToTmpAddressRepositoryImpl() {
    super(TmpWorker.class, TmpAddress.class);
  }
}
