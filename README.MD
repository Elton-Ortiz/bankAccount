API de simulação bancária
Esta API permite que você simule a criação de uma conta bancária, fazer depósitos, saques, transferências e extrair extratos.

Autenticação
Nenhuma autenticação é necessária para usar esta API.

Criar conta
Para criar uma nova conta, faça uma chamada POST para http://localhost:8080/account/register/create com um objeto JSON contendo as seguintes informações:

{ 
"name":"André Luiz", 
"fiscalNumber":"40718059867 "

}
O fiscalNumber pode ser um CPF ou um CNPJ!

Resposta
Número da conta: 123456789


Fazer depósito
Para fazer um depósito em uma conta, faça uma chamada POST para http://localhost:8080/account/deposit/{Número da conta} com um objeto JSON contendo a seguinte informação:
{
    "amount":"600.22"

}
amount = valor a ser depositado

Resposta
{
"balance":4201.54,
"accountNumber":"123456789"
}


Fazer saque
Para fazer um saque de uma conta, faça uma chamada POST para http://localhost:8080/account/withdraw/{Número da conta} com um objeto JSON contendo a seguinte informação:
{
    "amount":"600.22"

}
amount = valor a ser sacado

Resposta
{
"balance":4201.54,
"accountNumber":"123456789"
}


Fazer transferência
Para fazer uma transferência de uma conta para outra, faça uma chamada POST para http://localhost:8080/account/transfer/{Número da conta} com um objeto JSON contendo as seguintes informações:

{
     "relatedAccountNumber":"2664797178",
      "amount":"50.0"

}
relatedAccountNumber = conta que ira receber a transferencia
amount = valor a ser transferido

Resposta
Resposta
{
"balance":4201.54,
"accountNumber":"123456789"
}

5-)PUXANDO UM EXTRATO
Para puxar um extrato faça uma chamada GET http://localhost:8080/account/extract/{Número da conta(criada no primeiro passo)}
[
    {
        "criationDate": "Feb 12, 2023, 7:27:27 PM",
        "amount": 200.00,
        "transactionType": "DEPOSIT"
    },
    {
        "criationDate": "Feb 12, 2023, 10:35:56 PM",
        "amount": 600.00,
        "transactionType": "DEPOSIT"
    },
    {
        "criationDate": "Feb 12, 2023, 7:25:42 PM",
        "amount": 600.00,
        "transactionType": "DEPOSIT"
    },
]
