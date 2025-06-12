package br.edu.ibmec.cloud.binance_trading_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling

public class BinanceTradingBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinanceTradingBotApplication.class, args);
	}

}
