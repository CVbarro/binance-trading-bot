package br.edu.ibmec.cloud.binance_trading_bot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderReportDTO {
    private String symbol;
    private double precoCompra;
    private double precoVenda;
    private double lucro;
    private String dataCompra;
    private String dataVenda;
}
