# PicPay Simplificado - Desafio Back-end

Este é um projeto desenvolvido como parte do Desafio Back-end do PicPay. O objetivo é criar uma aplicação que simplifica transferências de dinheiro entre usuários comuns e lojistas.

O desafio está nesse link: https://github.com/PicPay/picpay-desafio-backend?tab=readme-ov-file

## Tecnologias Utilizadas

- Linguagem de programação: Java
- Framework: Spring Boot
- Banco de Dados: H2 (em memória)

## Pré-requisitos

- Java 11 ou superior instalado
- Maven para construção e gerenciamento de dependências

## Como Executar

1. Clone este repositório para o seu ambiente local:

   ```
   git clone https://github.com/CsBryan0/payApp
   ```

2. Navegue até o diretório do projeto:

   ```
   cd payApp
   ```

3. Compile o projeto usando o Maven:

   ```
   mvn clean package
   ```

4. Execute o arquivo JAR gerado:

   ```
   java -jar target/picpay-simplificado.jar
   ```

5. O servidor estará rodando em http://localhost:8080.

## Endpoints

### POST /transaction

Realiza uma transferência de dinheiro entre dois usuários.

#### Payload:

```
{
    "value": 100.0,
    "payer": 4,
    "payee": 15
}
```

### Modelagem de Dados

- **Usuário Comum (User):**
  - ID (identificador único)
  - Nome Completo
  - CPF
  - E-mail
  - Senha
  - Saldo na carteira

- **Lojista (Merchant):**
  - ID (identificador único)
  - Nome Completo
  - CNPJ
  - E-mail
  - Senha
  - Saldo na carteira

- **Transação (Transaction):**
  - ID (identificador único)
  - Valor da transação
  - Usuário que paga (payer)
  - Usuário que recebe (payee)

## Autor

Seu Nome - [Link do Linkedin](https://www.linkedin.com/in/bryan-souza-093b3b1b9/)
