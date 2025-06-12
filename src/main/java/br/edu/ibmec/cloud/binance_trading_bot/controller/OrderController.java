package br.edu.ibmec.cloud.binance_trading_bot.controller;

import br.edu.ibmec.cloud.binance_trading_bot.dtos.OrderReportDTO;
import br.edu.ibmec.cloud.binance_trading_bot.model.User;
import br.edu.ibmec.cloud.binance_trading_bot.model.UserOrderReport;
import br.edu.ibmec.cloud.binance_trading_bot.repository.UserOrderReportRepository;
import br.edu.ibmec.cloud.binance_trading_bot.repository.UserRepository;
import br.edu.ibmec.cloud.binance_trading_bot.request.OrderRequest;
import br.edu.ibmec.cloud.binance_trading_bot.response.OrderResponse;
import br.edu.ibmec.cloud.binance_trading_bot.service.BinanceIntegration;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("{id}/order")
public class OrderController {

    @Autowired
    private UserRepository usuarioRepositorio;

    @Autowired
    private UserOrderReportRepository relatorioOrdemRepositorio;

    @Autowired
    private BinanceIntegration binanceServico;

    @PostMapping
    public ResponseEntity<OrderResponse> enviarOrdem(@PathVariable("id") int id, @RequestBody OrderRequest pedido) {
        Optional<User> usuarioOptional = this.usuarioRepositorio.findById(id);
        if (usuarioOptional.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User usuario = usuarioOptional.get();
        this.binanceServico.setAPI_KEY(usuario.getBinanceApiKey());
        this.binanceServico.setSECRET_KEY(usuario.getBinanceSecretKey());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            String resultado;

            if (pedido.getPreco() > 0) {
                resultado = this.binanceServico.createLimitOrder(pedido.getSimbolo(), pedido.getQuantidade(), pedido.getLado(), pedido.getPreco());
            } else if (pedido.getStopPrice() != null && "SELL".equalsIgnoreCase(pedido.getLado())) {
                resultado = this.binanceServico.createStopLossOrder(pedido.getSimbolo(), pedido.getQuantidade(), pedido.getLado(), pedido.getStopPrice());
            } else if (pedido.getStopPrice() != null && "BUY".equalsIgnoreCase(pedido.getLado())) {
                resultado = this.binanceServico.createTakeProfitOrder(pedido.getSimbolo(), pedido.getQuantidade(), pedido.getLado(), pedido.getStopPrice());
            } else {
                resultado = this.binanceServico.createMarketOrder(pedido.getSimbolo(), pedido.getQuantidade(), pedido.getLado());
            }

            OrderResponse resposta = mapper.readValue(resultado, OrderResponse.class);

            if ("BUY".equalsIgnoreCase(pedido.getLado())) {
                UserOrderReport relatorio = new UserOrderReport();
                relatorio.setSimbolo(pedido.getSimbolo());
                relatorio.setQuantidade(pedido.getQuantidade());
                relatorio.setPrecoCompra(resposta.getPreenchimentos().get(0).getPreco());
                relatorio.setDataOperacao(LocalDateTime.now());

                this.relatorioOrdemRepositorio.save(relatorio);
                usuario.getRelatoriosDeOrdens().add(relatorio);
                this.usuarioRepositorio.save(usuario);
            }

            if ("SELL".equalsIgnoreCase(pedido.getLado())) {
                UserOrderReport ordem = usuario.getRelatoriosDeOrdens().stream()
                        .filter(item -> item.getSimbolo().equals(pedido.getSimbolo()) && item.getPrecoVenda() == 0)
                        .findFirst().orElse(null);

                if (ordem != null) {
                    ordem.setPrecoVenda(resposta.getPreenchimentos().get(0).getPreco());
                    this.relatorioOrdemRepositorio.save(ordem);
                }
            }

            return new ResponseEntity<>(resposta, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/relatorio")
    public List<OrderReportDTO> gerarRelatorio() {
        List<UserOrderReport> ordens = relatorioOrdemRepositorio.findAll();
        List<OrderReportDTO> relatorio = new ArrayList<>();

        for (UserOrderReport ordem : ordens) {
            if (ordem.getPrecoCompra() != null && ordem.getPrecoVenda() != null) {
                double lucro = (ordem.getPrecoVenda() - ordem.getPrecoCompra()) * ordem.getQuantidade();
                relatorio.add(new OrderReportDTO(
                        ordem.getSimbolo(),
                        ordem.getPrecoCompra(),
                        ordem.getPrecoVenda(),
                        lucro,
                        ordem.getDataOperacao().toString(),
                        ordem.getDataOperacao().toString() // você pode ajustar se tiver campos separados para venda
                ));
            }
        }

        double totalLucro = relatorio.stream().mapToDouble(OrderReportDTO::getLucro).sum();
        System.out.println("📊 Lucro total: " + totalLucro);

        return relatorio;
    }
}
