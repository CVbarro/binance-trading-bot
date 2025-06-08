Binance Trading Bot
Desenvolvedores:
Vitor Farani, João Pedro Bezamat, César Viana

Objetivo do Projeto
Este projeto tem como objetivo criar um Trading Bot que interage com a API da Binance para realizar operações automáticas de compra e venda de criptomoedas, além de gerar relatórios detalhados sobre ganhos e perdas, a fim de monitorar o desempenho das transações realizadas. O bot será implementado e hospedado na plataforma Azure, garantindo escalabilidade e robustez.

Tecnologias Utilizadas
API da Binance: Para realizar as operações de compra e venda de criptomoedas.

Azure SQL Database: Para armazenamento de transações e relatórios de desempenho.

Azure App Service: Para hospedar o bot na nuvem e garantir escalabilidade.

Spring Boot: Framework utilizado para construir a aplicação de backend.

Binance Connector Java: Biblioteca para integração com a API da Binance.

JPA (Java Persistence API): Para a persistência de dados no banco de dados SQL.

Jackson: Para serialização e desserialização de objetos JSON.

Funcionalidades Principais
Consulta de Preços de Moedas: O bot consulta preços em tempo real das criptomoedas na Binance para realizar decisões de compra ou venda.

Execução de Ordens de Compra e Venda: O bot é capaz de realizar ordens de compra e venda (market orders) de criptomoedas com base em configurações predefinidas.

Gerenciamento de Relatórios de Ganhos e Perdas: O sistema armazena e gera relatórios detalhados de cada transação realizada, incluindo lucro ou perda.

Armazenamento de Transações e Relatórios: O bot registra todas as transações feitas, incluindo detalhes como símbolo da moeda, quantidade, preço de compra e venda, e lucro ou perda.

Banco de Dados
Armazenamento de Transações: O sistema armazena cada transação realizada no banco de dados SQL, garantindo que os dados de cada operação (como moeda, quantidade, preço de compra, preço de venda) sejam persistidos.

Armazenamento de Relatórios: Relatórios detalhados sobre o desempenho do bot são armazenados para facilitar a análise de ganhos e perdas ao longo do tempo.

Hospedagem e Escalabilidade
Azure App Service: O bot será hospedado na nuvem utilizando o Azure App Service, o que garante alta disponibilidade e escalabilidade, permitindo que o bot opere sem interrupções.

Escalabilidade: O bot foi projetado para ser escalável, utilizando os serviços do Azure para garantir que ele funcione de forma contínua, mesmo com o aumento de demanda.

Regras do Projeto
Execução de Operações: As operações de compra e venda são feitas automaticamente com base em sinais predefinidos. O bot não realiza operações sem um sinal válido.

Relatórios de Ganhos e Perdas: Cada transação realizada é registrada, e relatórios periódicos de ganhos e perdas são gerados para monitoramento da performance.

Implementação na Nuvem (Azure): O código do bot será hospedado no Azure App Service, o que garante que o bot esteja em execução contínua, conectado ao banco de dados SQL para o armazenamento das transações e relatórios.

Etapas para Desenvolvimento
Configuração do Ambiente de Desenvolvimento:

Criar uma conta no Azure e configurar o Azure App Service.

Criar um banco de dados SQL no Azure.

Configurar a conexão com a API da Binance para consulta de preços e execução de ordens.

Desenvolvimento do Bot:

Implementar a lógica de execução de ordens de compra e venda utilizando os padrões de trading.

Implementar a persistência de dados (transações e relatórios) no banco de dados SQL.

Teste e Validação:

Testar o bot em ambiente local e na nuvem para garantir que as operações de compra e venda funcionem corretamente.

Validar os cálculos de ganhos e perdas nas transações realizadas.

Deploy para o Azure:

Realizar o deploy do código no Azure App Service.

Configurar o Azure Monitor para rastrear erros e métricas de desempenho.

Documentação e Apresentação:

Documentar todas as funcionalidades implementadas e o processo de configuração do bot.

Apresentar o funcionamento do bot e os relatórios gerados para a equipe e stakeholders.

Como Usar
1. Criar um Usuário
Para criar um usuário, envie uma requisição POST para o endpoint /users com os dados do usuário (login, senha, chave de API da Binance, etc.).

2. Adicionar Configurações e Tickers
Após criar o usuário, você pode associar configurações de trading e tickers (moedas a serem monitoradas) aos usuários:

POST /users/{id}/configuration: Adiciona configurações de trading.

POST /users/{id}/tracking-ticker: Adiciona moedas para serem monitoradas.

3. Consultar Preços
Para consultar os preços das moedas que o usuário está monitorando, faça uma requisição GET para /users/{id}/tickers.

4. Enviar Ordens de Compra/Venda
Para enviar ordens de compra ou venda de criptomoedas, faça uma requisição POST para /users/{id}/order com os detalhes da ordem (símbolo, quantidade, tipo de ordem).

5. Visualizar Relatórios
Após a execução das ordens, os relatórios de transações são gerados automaticamente e armazenados. Esses relatórios podem ser acessados para monitoramento do desempenho do bot.

Executando o Projeto Localmente
Clone o repositório:
git clone <URL_DO_REPOSITORIO>

Navegue até a pasta do projeto e abra no seu IDE (Exemplo: IntelliJ, Eclipse).

Configure o arquivo application.properties com as credenciais da Binance e a conexão com o banco de dados.

Execute a aplicação utilizando a classe principal BinanceTradingBotApplication.java:
mvn spring-boot:run

A aplicação estará disponível na sua máquina local.
