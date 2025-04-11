package br.edu.ibmec.cloud.binance_trading_bot.controller;

import br.edu.ibmec.cloud.binance_trading_bot.model.User;
import br.edu.ibmec.cloud.binance_trading_bot.model.UserConfiguration;
import br.edu.ibmec.cloud.binance_trading_bot.model.UserTrackingTicker;
import br.edu.ibmec.cloud.binance_trading_bot.repository.UserConfigurationRepository;
import br.edu.ibmec.cloud.binance_trading_bot.repository.UserRepository;
import br.edu.ibmec.cloud.binance_trading_bot.repository.UserTrackingTickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConfigurationRepository userConfigurationRepository;

    @Autowired
    private UserTrackingTickerRepository userTrackingTickerRepository;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        this.userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
        return this.userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/configuration")
    public ResponseEntity<User> associateConfiguration(@PathVariable("id") Integer id, @RequestBody UserConfiguration configuration) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        this.userConfigurationRepository.save(configuration);
        User user = optionalUser.get();
        user.getConfigurations().add(configuration);
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("{id}/tracking-ticker")
    public ResponseEntity<User> associateTicker(@PathVariable("id") Integer id, @RequestBody UserTrackingTicker ticker) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        this.userTrackingTickerRepository.save(ticker);
        User user = optionalUser.get();
        user.getTrackingTickers().add(ticker);
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        if (!userRepository.existsById(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}/configuration/{configId}")
    public ResponseEntity<User> deleteConfiguration(@PathVariable("id") Integer userId, @PathVariable("configId") Integer configId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<UserConfiguration> optionalConfig = userConfigurationRepository.findById(configId);

        if (optionalUser.isEmpty() || optionalConfig.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User user = optionalUser.get();
        UserConfiguration config = optionalConfig.get();

        user.getConfigurations().remove(config);
        userConfigurationRepository.delete(config);
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}/tracking-ticker/{tickerId}")
    public ResponseEntity<User> deleteTicker(@PathVariable("id") Integer userId, @PathVariable("tickerId") Integer tickerId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<UserTrackingTicker> optionalTicker = userTrackingTickerRepository.findById(tickerId);

        if (optionalUser.isEmpty() || optionalTicker.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User user = optionalUser.get();
        UserTrackingTicker ticker = optionalTicker.get();

        user.getTrackingTickers().remove(ticker);
        userTrackingTickerRepository.delete(ticker);
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
