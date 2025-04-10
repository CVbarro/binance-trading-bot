package br.edu.ibmec.cloud.binance_trading_bot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserOrderReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String symbol;

    @Column
    private double quantity;

    @Column
    private double buyPrice;

    @Column
    private double sellPrice;

    @Column
    private LocalDateTime dtOperation;

    @ManyToOne
    @JoinColumn(name = "id_usuario") // Essa é a chave estrangeira
    private User user;
}

