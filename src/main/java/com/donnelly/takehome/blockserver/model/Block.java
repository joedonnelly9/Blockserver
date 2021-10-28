package com.donnelly.takehome.blockserver.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name="BLOCKS")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Block {

    @Id
    @Column(name="block_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_id")
    private Block parent;

    @OneToMany(mappedBy="parent")
    private Set<Block> children = new HashSet<Block>();

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, String> properties = new HashMap<>();

    public Block() {

    }

    public Block(Block parent) {
        if (parent != null) {
            this.parent = parent;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Block getParent() {
        return parent;
    }

    public void setParent(Block parent) {
        this.parent = parent;
    }

    public Set<Block> getChildren() {
        return children;
    }

    public void setChildren(Set<Block> children) {
        this.children = children;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
