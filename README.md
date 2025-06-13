# 📈 Binance Trading Bot

## 👨‍💻 Desenvolvedores
- Vitor Farani  
- João Pedro Bezamat  
- César Viana
- Rodrigo Souza

---

## 🎯 Objetivo do Projeto
Este projeto tem como objetivo criar um Trading Bot que interage com a API da Binance para realizar operações automáticas de compra e venda de criptomoedas, além de gerar relatórios detalhados sobre ganhos e perdas.  
O bot será implementado e hospedado na plataforma Azure, garantindo escalabilidade e robustez.

---

## 🛠 Tecnologias Utilizadas
- **API da Binance**: Para realizar as operações de compra e venda de criptomoedas.
- **Azure SQL Database**: Para armazenamento de transações e relatórios de desempenho.
- **Azure App Service**: Para hospedar o bot na nuvem e garantir escalabilidade.
- **Spring Boot**: Framework utilizado para construir a aplicação de backend.
- **Binance Connector Java**: Biblioteca para integração com a API da Binance.
- **JPA (Java Persistence API)**: Para a persistência de dados no banco de dados SQL.
- **Jackson**: Para serialização e desserialização de objetos JSON.

---

## 🔑 Funcionalidades Principais
- **Consulta de Preços de Moedas**: Consulta preços em tempo real das criptomoedas na Binance.
- **Execução de Ordens de Compra e Venda**: Realiza ordens automáticas com base em configurações predefinidas.
- **Gerenciamento de Relatórios**: Armazena e gera relatórios detalhados de cada transação.
- **Armazenamento de Dados**: Registra operações com símbolo da moeda, quantidade, preços e lucro/prejuízo.

---

## 🗃 Banco de Dados
- **Transações**: Armazenadas no banco SQL com todos os dados necessários.
- **Relatórios**: Registrados para análises históricas de desempenho.

---

## ☁️ Hospedagem e Escalabilidade
- **Azure App Service**: Garante alta disponibilidade e operação contínua.
- **Escalabilidade**: Utiliza recursos do Azure para lidar com aumento de demanda.

---

## 📜 Regras do Projeto
- Operações automáticas com base em sinais predefinidos.
- Registro e geração de relatórios periódicos.
- Código hospedado na nuvem e conectado ao banco de dados SQL.

---

## 🧱 Etapas para Desenvolvimento

### 1. Configuração do Ambiente
- Criar conta no Azure e configurar App Service.
- Criar banco de dados SQL.
- Configurar conexão com API da Binance.

### 2. Desenvolvimento do Bot
- Implementar lógica de trading e persistência de dados.

### 3. Teste e Validação
- Testar localmente e na nuvem.
- Validar cálculos de ganhos/perdas.

### 4. Deploy para Azure
- Deploy do código via App Service.
- Configurar Azure Monitor para métricas e erros.

### 5. Documentação e Apresentação
- Documentar funcionalidades e processo.
- Apresentar funcionamento e relatórios à equipe.

---

## 🚀 Como Usar

### 1. Criar um Usuário
```http
POST /users
