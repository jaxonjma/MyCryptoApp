package com.unosquare.useroperation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "operation_types")
public class OperationType extends EntityBase<Integer>{

    @Column(name="operation_name",unique = true)
    private String operationName;

    @OneToMany(mappedBy = "operationType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<OperationHistory> operationHistories;
}
