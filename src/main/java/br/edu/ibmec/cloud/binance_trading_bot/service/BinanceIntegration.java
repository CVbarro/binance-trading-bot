package br.edu.ibmec.cloud.binance_trading_bot.service;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Data
public class BinanceIntegration {
    private String BASE_URL = "https://testnet.binance.vision";
    private String API_KEY;
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
        parametro.put("timeInForce", "GTC"); // Good 'Til Cancelled
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
}
