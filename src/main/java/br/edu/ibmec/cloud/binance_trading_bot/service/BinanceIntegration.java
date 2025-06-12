package br.edu.ibmec.cloud.binance_trading_bot.service;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Data
public class BinanceIntegration {
    private String BASE_URL = "https://testnet.binance.vision";

    @Value("${binance.api.key}")
    private String API_KEY;

    @Value("${binance.api.key}")
    private String SECRET_KEY;

    public String getTickers(ArrayList<String> simbolo) {
        SpotClient client = new SpotClientImpl(this.API_KEY, this.SECRET_KEY, this.BASE_URL);
        Map<String, Object> parametro = new LinkedHashMap<>();
        parametro.put("symbol", simbolo);
        return client.createMarket().ticker(parametro);
    }

    public String createMarketOrder(String simbolo, double quantidade, String lado) {
        SpotClient client = new SpotClientImpl(this.API_KEY, this.SECRET_KEY, this.BASE_URL);
        Map<String, Object> parametro = new LinkedHashMap<>();
        parametro.put("symbol", simbolo);
        parametro.put("side", lado);
        parametro.put("type", "MARKET");
        parametro.put("quantity", quantidade);
        return client.createTrade().newOrder(parametro);
    }

    public String createLimitOrder(String simbolo, double quantidade, String lado, double preco) {
        SpotClient client = new SpotClientImpl(this.API_KEY, this.SECRET_KEY, this.BASE_URL);
        Map<String, Object> parametro = new LinkedHashMap<>();
        parametro.put("symbol", simbolo);
        parametro.put("side", lado);
        parametro.put("type", "LIMIT");
        parametro.put("quantity", quantidade);
        parametro.put("price", preco);
        parametro.put("timeInForce", "GTC");
        return client.createTrade().newOrder(parametro);
    }

    public String createStopLossOrder(String simbolo, double quantidade, String lado, double stopPrice) {
        SpotClient client = new SpotClientImpl(this.API_KEY, this.SECRET_KEY, this.BASE_URL);
        Map<String, Object> parametro = new LinkedHashMap<>();
        parametro.put("symbol", simbolo);
        parametro.put("side", lado);
        parametro.put("type", "STOP_MARKET");
        parametro.put("quantity", quantidade);
        parametro.put("stopPrice", stopPrice);
        return client.createTrade().newOrder(parametro);
    }

    public String createTakeProfitOrder(String simbolo, double quantidade, String lado, double stopPrice) {
        SpotClient client = new SpotClientImpl(this.API_KEY, this.SECRET_KEY, this.BASE_URL);
        Map<String, Object> parametro = new LinkedHashMap<>();
        parametro.put("symbol", simbolo);
        parametro.put("side", lado);
        parametro.put("type", "TAKE_PROFIT_MARKET");
        parametro.put("quantity", quantidade);
        parametro.put("stopPrice", stopPrice);
        return client.createTrade().newOrder(parametro);
    }

    // 🔍 Buscar as últimas 2 velas
    public JSONArray getLastCandles(String symbol, String interval) throws IOException, InterruptedException {
        String url = String.format("https://api.binance.com/api/v3/klines?symbol=%s&interval=%s&limit=2", symbol, interval);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONArray(response.body());
    }

    // 📈 Verificar padrão Engolfo de Alta (Bullish Engulfing)
    public boolean isBullishEngulfing(JSONArray candles) {
        JSONArray prev = candles.getJSONArray(0);
        JSONArray curr = candles.getJSONArray(1);

        double prevOpen = Double.parseDouble(prev.getString(1));
        double prevHigh = Double.parseDouble(prev.getString(2));
        double prevLow = Double.parseDouble(prev.getString(3));
        double prevClose = Double.parseDouble(prev.getString(4));

        double currOpen = Double.parseDouble(curr.getString(1));
        double currHigh = Double.parseDouble(curr.getString(2));
        double currLow = Double.parseDouble(curr.getString(3));
        double currClose = Double.parseDouble(curr.getString(4));

        // Exibe os candles
        System.out.printf("🕯️ Candle Anterior → [Open: %.2f | High: %.2f | Low: %.2f | Close: %.2f]%n",
                prevOpen, prevHigh, prevLow, prevClose);

        System.out.printf("🕯️ Candle Atual    → [Open: %.2f | High: %.2f | Low: %.2f | Close: %.2f]%n",
                currOpen, currHigh, currLow, currClose);

        boolean isEngulfing = (prevOpen > prevClose) && (currOpen < currClose) && (currClose > prevClose);

        if (isEngulfing) {
            System.out.println("✅ Padrão Engolfo detectado!");
        }

        return isEngulfing;
    }




    public String checkInsideBar(JSONArray candles, String symbol, double quantidade) {
        JSONArray prev = candles.getJSONArray(0);
        JSONArray curr = candles.getJSONArray(1);

        double prevHigh = Double.parseDouble(prev.getString(2));
        double prevLow = Double.parseDouble(prev.getString(3));
        double currHigh = Double.parseDouble(curr.getString(2));
        double currLow = Double.parseDouble(curr.getString(3));
        double currClose = Double.parseDouble(curr.getString(4));
        double prevOpen = Double.parseDouble(curr.getString(1));
        double prevClose = Double.parseDouble(prev.getString(4));
        double currOpen = Double.parseDouble(curr.getString(1));

        // Log visual
        System.out.printf("🕯️ Candle Anterior → [Open: %.2f | High: %.2f | Low: %.2f | Close: %.2f]%n",
                prevOpen, prevHigh, prevLow, prevClose);

        System.out.printf("🕯️ Candle Atual    → [Open: %.2f | High: %.2f | Low: %.2f | Close: %.2f]%n",
                currOpen, currHigh, currLow, currClose);

        boolean isInsideBar = (currHigh <= prevHigh) && (currLow >= prevLow);

        if (!isInsideBar) return null;

        System.out.println("📊 Padrão Inside Bar detectado!");

        // Estratégia: rompimento
        if (currClose > prevHigh) {
            System.out.println("📈 Rompimento para cima! Comprando...");
            createMarketOrder(symbol, quantidade, "BUY");
            return "BUY";
        } else if (currClose < prevLow) {
            System.out.println("📉 Rompimento para baixo! Vendendo...");
            createMarketOrder(symbol, quantidade, "SELL");
            return "SELL";
        } else {
            System.out.println("⏸️ Ainda dentro da faixa, aguardando rompimento.");
            return "NEUTRO";
        }
    }


}
