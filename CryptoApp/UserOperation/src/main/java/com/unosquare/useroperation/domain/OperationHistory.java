package com.unosquare.useroperation.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "operation_history")
public class OperationHistory extends EntityBase<Integer> {

    @ManyToOne()
    @JoinColumn(name = "operation_type_id")
    @JsonManagedReference
    private OperationType operationType;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    private LocalDateTime timestamp;
}
