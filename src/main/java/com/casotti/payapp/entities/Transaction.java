    package com.casotti.payapp.entities;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private BigDecimal  amount;
        private LocalDateTime timeStamp;
        @ManyToOne
        private Users payer;
        @ManyToOne
        private Users payee;

    }
