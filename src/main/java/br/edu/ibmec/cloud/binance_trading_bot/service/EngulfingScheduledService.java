package br.edu.ibmec.cloud.binance_trading_bot.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EngulfingScheduledService {

    private final BinanceIntegration binanceIntegration;

    public EngulfingScheduledService(BinanceIntegration binanceIntegration) {
        this.binanceIntegration = binanceIntegration;
    }

    @Scheduled(fixedRate = 60000) // roda a cada 60 segundos
    public void checkForBullishEngulfing() {
        try {
            String symbol = "BTCUSDT";
            String interval = "5m";
            double quantidade = 0.001;

            JSONArray candles = binanceIntegration.getLastCandles(symbol, interval);
            boolean isEngulfing = binanceIntegration.isBullishEngulfing(candles);

            if (isEngulfing) {
                System.out.println("📈 Engolfo de Alta detectado! Comprando...");

                // 1. Obtemos o último preço de fechamento como referência
                double precoEntrada = Double.parseDouble(candles.getJSONArray(1).getString(4));

                // 2. Enviamos a ordem de compra
                String resultadoCompra = binanceIntegration.createMarketOrder(symbol, quantidade, "BUY");
                System.out.println("✅ Ordem de compra executada: " + resultadoCompra);

                // 3. Calculamos SL (-2%) e TP (+4%)
                double stopLoss = precoEntrada * 0.98;
                double takeProfit = precoEntrada * 1.04;

                // 4. Enviamos as ordens condicionais
                String slResult = binanceIntegration.createStopLossOrder(symbol, quantidade, "SELL", stopLoss);
                String tpResult = binanceIntegration.createTakeProfitOrder(symbol, quantidade, "SELL", takeProfit);

                System.out.println("📉 Stop Loss enviado: " + slResult);
                System.out.println("📈 Take Profit enviado: " + tpResult);
            } else {
                System.out.println("Nenhum Engolfo detectado.");
            }

        } catch (Exception e) {
            System.err.println("Erro ao verificar Engolfo ou enviar ordens: " + e.getMessage());
        }
    }

    @Scheduled(fixedRate = 60000)
    public void checkForSignals() {
        try {
            String symbol = "BTCUSDT";
            String interval = "1m";
            double quantidade = 0.001;

            JSONArray candles = binanceIntegration.getLastCandles(symbol, interval);

            boolean isEngulfing = binanceIntegration.isBullishEngulfing(candles);
            boolean isInsideBar = binanceIntegration.isInsideBar(candles);

            if (isEngulfing) {
                System.out.println("📈 Engolfo de Alta detectado! Comprando...");

                double precoEntrada = Double.parseDouble(candles.getJSONArray(1).getString(4));

                binanceIntegration.createMarketOrder(symbol, quantidade, "BUY");

                double stopLoss = precoEntrada * 0.98;
                double takeProfit = precoEntrada * 1.04;

                binanceIntegration.createStopLossOrder(symbol, quantidade, "SELL", stopLoss);
                binanceIntegration.createTakeProfitOrder(symbol, quantidade, "SELL", takeProfit);
            } else if (isInsideBar) {
                System.out.println("📊 Inside Bar detectado. Nenhuma ação automática definida.");
                // Aqui você pode futuramente colocar uma lógica de compra ou venda
            } else {
                System.out.println("❌ Nenhum padrão técnico detectado.");
            }

        } catch (Exception e) {
            System.err.println("Erro ao verificar sinais: " + e.getMessage());
        }
    }

}
