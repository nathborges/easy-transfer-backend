# EasyTransferBackend
EasyTransfer é uma aplicação de demonstração de um serviço que facilita transferências de valores para contas.

## Pré-requisitos
Antes de começar, certifique-se de ter o seguinte instalado:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versão 11)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)

## Como executar
- Clone este repositório e para buildar o projeto, utilize:
  ```
  mvn spring-boot:run
  ```

# Funcionalidades
O usuário poderá agendar a transferência do valor desejado e, de acordo com a data escolhida, o valor da taxa do serviço pode variar, de acordo com a seguinte tabela:


<img width="558" alt="image" src="https://github.com/nathborges/easy-transfer-backend/assets/84536972/53e90687-ddaa-4fde-b686-3a55a5d3e615">
  
# Como testar o EasyTransfer
- Só ocorrerá a transfêrencia entre contas que existem, para criar uma conta use o comando:
```
curl --request POST \
  --url http://localhost:8080/account \
  --header 'Content-Type: application/json' \
  --data '{
	"firstName": "Nathália",
	"cpf": "48923456798"
}'
```

Atenção: Não é possível criar dois usuários com o mesmo CPF ou com um CPF maior que 11 caracteres.

- Crie outra conta
```
curl --request POST \
  --url http://localhost:8080/account \
  --header 'Content-Type: application/json' \
  --data '{
	"firstName": "Sheilla",
	"cpf": "48923456798"
}'
```

- É possível listar todas as contas existentes com o comando:
```
curl --request GET \
  --url http://localhost:8080/account \
  --header 'User-Agent: insomnia/8.2.0'
```

Selecione o número da conta que você deseja que seja o rementente e o número que você deseja que seja o destinatário;


- Adicione um valor no saldo na conta que será o remetente da transferência e não se esqueça de colocar o accountNumber correto (vide endpoint anterior):
```
curl --request PUT \
  --url http://localhost:8080/account/deposit \
  --header 'Content-Type: application/json' \
  --data '{
	"accountNumber": "3767238",
	"value": 2000
}'
```

- Efetue a transferência utilizando o comando:
```
curl --request POST \
  --url http://localhost:8080/transfer \
  --header 'Content-Type: application/json' \
  --data '{
	"senderAccountNumber": "6947120",
	"recipientAccountNumber": "9707543",
	"amount": 100,
	"scheduledDate": "2024-06-07"
}'
```
Não se esque;ca de trocar o senderAccountNumber e recipientAccountNumber por números de contas já criados.
Se por acaso, o valor de transferência + taxa for maior que o valor disponível da conta do rementente, não será possível efetuar a transação.

- Todas as tranferências podem ser vistas com o seguinte comando:
```
curl --request GET \
  --url http://localhost:8080/transfer \
  --header 'User-Agent: insomnia/8.2.0'
```
  
# Database
Este projeto utiliza o banco de dados H2 como uma opção de banco de dados embutido para facilitar o desenvolvimento e testes. O H2 é um banco de dados relacional escrito em Java, que opera em memória. 
## Entidades
O projeto está dividido entre duas entidades, a entidade Account (Conta) que conta com os dados da conta do usuário e Transfer (tranferência), que conta com os dados da transferência em si.
### Account
Tabela: tb_account

| Campo             | Descrição                                         |
|-------------------|---------------------------------------------------|
| id                | Identificador único da conta (chave primária)     |
| firstName         | Primeiro nome do usuário                          |
| cpf               | CPF do usuário (chave única com limite de 11 caracteres)                      |
| accountNumber     | Número da conta (chave única com limite de 7 caracteres)                     |
| balance           | Saldo da conta                                    |
| createdAt         | Data e hora de criação da conta                   |

### Transfer
Tabela: tb_transfer
| Campo             | Descrição                                         |
|-------------------|---------------------------------------------------|
| id                | Identificador único da transferência (chave primária) |
| sender            | Conta do remetente (relacionamento muitos-para-um com a entidade AccountEntity) |
| recipient         | Conta do destinatário (relacionamento muitos-para-um com a entidade AccountEntity) |
| amount            | Valor da transferência                            |
| scheduledAt       | Data agendada para a transferência                |
| status            | Status da transferência (Enumeração)              |
| tax               | Taxa da transferência                             |
| createdAt         | Data e hora de criação da transferência           |


