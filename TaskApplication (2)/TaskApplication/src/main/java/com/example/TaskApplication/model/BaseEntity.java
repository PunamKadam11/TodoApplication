package com.example.TaskApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Column(name = "created_on")
    @JsonIgnore
    private LocalDateTime createdOn;

    @JoinColumn(name = "created_by")
    @JsonIgnore
    private Integer createdBy;

    @Column(name = "modified_on")
    @JsonIgnore
    private LocalDateTime modifiedOn;

    @JoinColumn(name = "modified_by")
    @JsonIgnore
    private Integer modifiedBy;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    @JsonIgnore
    private boolean active = true;

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdOn = createdDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.modifiedOn = updateDate;
    }

    @PrePersist
    public void onPrePersist() {
        setCreatedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdateDate(LocalDateTime.now());
    }
}