package com.donnelly.takehome.blockserver.repository;

import com.donnelly.takehome.blockserver.model.Block;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface BlockRepository extends CrudRepository<Block, Long> {

    @Query(value="SELECT * FROM BLOCKS b WHERE b.parent_id IS NULL", nativeQuery = true)
    Collection<Block> findTopLevelBlocks();

}
