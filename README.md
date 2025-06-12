<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Binance Trading Bot</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
        }
        h1, h2, h3 {
            color: #333;
        }
        ul {
            list-style-type: disc;
            margin-left: 20px;
        }
        code {
            background-color: #f4f4f4;
            padding: 5px;
            border-radius: 4px;
        }
    </style>
</head>
<body>

    <h1>Binance Trading Bot</h1>
    <h3>Desenvolvedores:</h3>
    <p>Vitor Farani, João Pedro Bezamat, César Viana</p>

    <h2>Objetivo do Projeto</h2>
    <p>Este projeto tem como objetivo criar um Trading Bot que interage com a API da Binance para realizar operações automáticas de compra e venda de criptomoedas, além de gerar relatórios detalhados sobre ganhos e perdas, a fim de monitorar o desempenho das transações realizadas. O bot será implementado e hospedado na plataforma Azure, garantindo escalabilidade e robustez.</p>

    <h2>Tecnologias Utilizadas</h2>
    <ul>
        <li><strong>API da Binance</strong>: Para realizar as operações de compra e venda de criptomoedas.</li>
        <li><strong>Azure SQL Database</strong>: Para armazenamento de transações e relatórios de desempenho.</li>
        <li><strong>Azure App Service</strong>: Para hospedar o bot na nuvem e garantir escalabilidade.</li>
        <li><strong>Spring Boot</strong>: Framework utilizado para construir a aplicação de backend.</li>
        <li><strong>Binance Connector Java</strong>: Biblioteca para integração com a API da Binance.</li>
        <li><strong>JPA (Java Persistence API)</strong>: Para a persistência de dados no banco de dados SQL.</li>
        <li><strong>Jackson</strong>: Para serialização e desserialização de objetos JSON.</li>
    </ul>

    <h2>Funcionalidades Principais</h2>
    <ul>
        <li><strong>Consulta de Preços de Moedas</strong>: O bot consulta preços em tempo real das criptomoedas na Binance para realizar decisões de compra ou venda.</li>
        <li><strong>Execução de Ordens de Compra e Venda</strong>: O bot é capaz de realizar ordens de compra e venda (market orders) de criptomoedas com base em configurações predefinidas.</li>
        <li><strong>Gerenciamento de Relatórios de Ganhos e Perdas</strong>: O sistema armazena e gera relatórios detalhados de cada transação realizada, incluindo lucro ou perda.</li>
        <li><strong>Armazenamento de Transações e Relatórios</strong>: O bot registra todas as transações feitas, incluindo detalhes como símbolo da moeda, quantidade, preço de compra e venda, e lucro ou perda.</li>
    </ul>

    <h2>Banco de Dados</h2>
    <ul>
        <li><strong>Armazenamento de Transações</strong>: O sistema armazena cada transação realizada no banco de dados SQL, garantindo que os dados de cada operação (como moeda, quantidade, preço de compra, preço de venda) sejam persistidos.</li>
        <li><strong>Armazenamento de Relatórios</strong>: Relatórios detalhados sobre o desempenho do bot são armazenados para facilitar a análise de ganhos e perdas ao longo do tempo.</li>
    </ul>

    <h2>Hospedagem e Escalabilidade</h2>
    <ul>
        <li><strong>Azure App Service</strong>: O bot será hospedado na nuvem utilizando o Azure App Service, o que garante alta disponibilidade e escalabilidade, permitindo que o bot opere sem interrupções.</li>
        <li><strong>Escalabilidade</strong>: O bot foi projetado para ser escalável, utilizando os serviços do Azure para garantir que ele funcione de forma contínua, mesmo com o aumento de demanda.</li>
    </ul>

    <h2>Regras do Projeto</h2>
    <ul>
        <li><strong>Execução de Operações</strong>: As operações de compra e venda são feitas automaticamente com base em sinais predefinidos. O bot não realiza operações sem um sinal válido.</li>
        <li><strong>Relatórios de Ganhos e Perdas</strong>: Cada transação realizada é registrada, e relatórios periódicos de ganhos e perdas são gerados para monitoramento da performance.</li>
        <li><strong>Implementação na Nuvem (Azure)</strong>: O código do bot será hospedado no Azure App Service, o que garante que o bot esteja em execução contínua, conectado ao banco de dados SQL para o armazenamento das transações e relatórios.</li>
    </ul>

    <h2>Etapas para Desenvolvimento</h2>
    <h3>1. Configuração do Ambiente de Desenvolvimento:</h3>
    <ul>
        <li>Criar uma conta no Azure e configurar o Azure App Service.</li>
        <li>Criar um banco de dados SQL no Azure.</li>
        <li>Configurar a conexão com a API da Binance para consulta de preços e execução de ordens.</li>
    </ul>

    <h3>2. Desenvolvimento do Bot:</h3>
    <ul>
        <li>Implementar a lógica de execução de ordens de compra e venda utilizando os padrões de trading.</li>
        <li>Implementar a persistência de dados (transações e relatórios) no banco de dados SQL.</li>
    </ul>

    <h3>3. Teste e Validação:</h3>
    <ul>
        <li>Testar o bot em ambiente local e na nuvem para garantir que as operações de compra e venda funcionem corretamente.</li>
        <li>Validar os cálculos de ganhos e perdas nas transações realizadas.</li>
    </ul>

    <h3>4. Deploy para o Azure:</h3>
    <ul>
        <li>Realizar o deploy do código no Azure App Service.</li>
        <li>Configurar o Azure Monitor para rastrear erros e métricas de desempenho.</li>
    </ul>

    <h3>5. Documentação e Apresentação:</h3>
    <ul>
        <li>Documentar todas as funcionalidades implementadas e o processo de configuração do bot.</li>
        <li>Apresentar o funcionamento do bot e os relatórios gerados para a equipe e stakeholders.</li>
    </ul>

    <h2>Como Usar</h2>
    <h3>1. Criar um Usuário</h3>
    <p>Para criar um usuário, envie uma requisição <code>POST</code> para o endpoint <code>/users</code> com os dados do usuário (login, senha, chave de API da Binance, etc.).</p>

    <h3>2. Adicionar Configurações e Tickers</h3>
    <p>Após criar o usuário, você pode associar configurações de trading e tickers (moedas a serem monitoradas) aos usuários:</p>
    <ul>
        <li><code>POST /users/{id}/configuration</code>: Adiciona configurações de trading.</li>
        <li><code>POST /users/{id}/tracking-ticker</code>: Adiciona moedas para serem monitoradas.</li>
    </ul>

    <h3>3. Consultar Preços</h3>
    <p>Para consultar os preços das moedas que o usuário está monitorando, faça uma requisição <code>GET</code> para <code>/users/{id}/tickers</code>.</p>

    <h3>4. Enviar Ordens de Compra/Venda</h3>
    <p>Para enviar ordens de compra ou venda de criptomoedas, faça uma requisição <code>POST</code> para <code>/users/{id}/order</code> com os detalhes da ordem (símbolo, quantidade, tipo de ordem).</p>

    <h3>5. Visualizar Relatórios</h3>
    <p>Após a execução das ordens, os relatórios de transações são gerados automaticamente e armazenados. Esses relatórios podem ser acessados para monitoramento do desempenho do bot.</p>

    <h2>Executando o Projeto Localmente</h2>
    <ul>
        <li>Clone o repositório: <code>git clone &lt;URL_DO_REPOSITORIO&gt;</code></li>
        <li>Navegue até a pasta do projeto e abra no seu IDE (Exemplo: IntelliJ, Eclipse).</li>
        <li>Configure o arquivo <code>application.properties</code> com as credenciais da Binance e a conexão com o banco de dados.</li>
        <li>Execute a aplicação utilizando a classe principal <code>BinanceTradingBotApplication.java</code>: <code>mvn spring-boot:run</code></li>
        <li>A aplicação estará disponível na sua máquina local.</li>
    </ul>

</body>
</html>
