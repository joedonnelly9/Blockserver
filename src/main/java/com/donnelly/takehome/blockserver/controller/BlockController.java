package com.donnelly.takehome.blockserver.controller;

import com.donnelly.takehome.blockserver.dto.BlockDto;
import com.donnelly.takehome.blockserver.model.Block;
import com.donnelly.takehome.blockserver.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BlockController {

    @Autowired
    private BlockRepository blockRepository;

    @GetMapping("/block")
    public Collection<BlockDto> getTopLevelBlocks() {
        Collection<BlockDto> responseObjectList = new HashSet<>();
        blockRepository.findTopLevelBlocks().forEach(b -> responseObjectList.add(new BlockDto(b)));
        return responseObjectList;
    }

    @GetMapping("/block/{id}")
    public BlockDto getBlock(@PathVariable String id) {
        Optional<Block> b = blockRepository.findById(Long.parseLong(id));
        return new BlockDto(b.orElse(null));
    }

    @PutMapping("/block")
    public BlockDto createParentBlock() {
        return new BlockDto(blockRepository.save(new Block()));
    }

    @PutMapping("/block/{id}")
    public BlockDto createChildBlock(@PathVariable String id) {
        Optional<Block> b = blockRepository.findById(Long.parseLong(id));
        if (!b.isPresent()) {
            return new BlockDto();
        }
        return new BlockDto(blockRepository.save(new Block(b.get())));
    }

    @PostMapping("/block/{id}")
    public HttpStatus updateBlock(@PathVariable String id, @RequestBody Map<String,String> propertyRequest) {
        Optional<Block> b = blockRepository.findById(Long.parseLong(id));
        if (b.isPresent()) {
            b.get().setProperties(propertyRequest);
            blockRepository.save(b.get());
            return HttpStatus.NO_CONTENT;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
