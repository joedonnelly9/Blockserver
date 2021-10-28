package com.donnelly.takehome.blockserver.dto;

import com.donnelly.takehome.blockserver.model.Block;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BlockDto {
    private String id;
    private String parent;
    private Set<String> children = new HashSet<String>();
    private Map<String, String> properties = new HashMap<>();

    public BlockDto() {
    }

    public BlockDto(Block b) {
        if (b != null) {
            this.id = b.getId().toString();
            if (b.getParent() != null) {
                this.parent = b.getParent().getId().toString();
            }
            for (Block c : b.getChildren()) {
                this.children.add(c.getId().toString());
            }
            this.properties = b.getProperties();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Set<String> getChildren() {
        return children;
    }

    public void setChildren(Set<String> children) {
        this.children = children;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
