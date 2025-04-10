package br.edu.ibmec.cloud.binance_trading_bot.controller;

<<<<<<< Updated upstream
=======
import br.edu.ibmec.cloud.binance_trading_bot.model.User;
import br.edu.ibmec.cloud.binance_trading_bot.model.UserOrderReport;
import br.edu.ibmec.cloud.binance_trading_bot.repository.UserOrderReportRepository;
import br.edu.ibmec.cloud.binance_trading_bot.repository.UserRepository;
import br.edu.ibmec.cloud.binance_trading_bot.request.OrderRequest;
import br.edu.ibmec.cloud.binance_trading_bot.response.OrderResponse;
import br.edu.ibmec.cloud.binance_trading_bot.service.BinanceIntegration;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@Data
@RequestMapping("{id}/order")
>>>>>>> Stashed changes
public class OrderController {
}
