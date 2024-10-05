# **Trabalho de Desenvolvimento Web**

### Integrantes

* Bernardo Estevam Krzyuy
* João Marcos Ressetti

## **Introdução**

Neste trabalho foi desenvolvido uma API em Java utilizando o framework Spring e padrão arquitetural MVC. O objetivo da API é permitir gerenciar pagamentos de mensalistas em jogos de futebol ao prover uma forma de gravar e consultar jogadores e pagamentos.

## **Descrição da API**

**URL:** localhost:8000/trabalho

### Jogador

|Método    |URL                         |Ação                          |
|:---------|:---------------------------|:-----------------------------|
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/jogador/**                      | Lista todos os jogadores |
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/jogador/?nome=[keyword]**       | Lista todos os jogadores cujo o nome contém a palavra-chave (keyword) |
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/jogador/?email=[keyword]**      | Lista todos os jogadores cujo o e-mail contém a palavra-chave (keyword) |
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/jogador/?nome=[k1]&email=[k2]** | Lista todos os jogadores cujo o nome e e-mail contêm as palvavras-chave (k1 e k2, respectivamente) |
| <div style="background-color: blue; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">POST</div>     | **/jogador/**                      | Adiciona um novo jogador |
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/jogador/:id**                   | Retorna o jogador por :id e os pagamentos associados ao jogador |
| <div style="background-color: orange; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">PUT</div>      | **/jogador/:id**                   | Atualiza um jogador por :id |
| <div style="background-color: red; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">DELETE</div>   | **/jogador/:id**                   | Deleta um jogador por :id e todos os seus pagamentos |
| <div style="background-color: red; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">DELETE</div>   | **/jogador/**                      | Deleta todos os jogadores e todos os seus respectivos pagamentos |

**Exemplo de Jogador**

```json
{
    "nome" : "Nome do Jogador",
    "email" : "email.do@jogador",
    "datanasc" : "2000-01-01"
}
```

Validações para cadastrar novos jogadores:

* `nome` deve ter entre 2 e 60 caracteres;
* `email` deve ter um formato de e-mail válido com até 60 caracteres;
* `datanasc` está no formato `aaaa-mm-dd`;
* O jogador deve ter pelo menos 16 anos de idade.

### Pagamento

|Método    |URL                                   |Ação                          |
|:---------|:-------------------------------------|:-----------------------------|
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/pagamento/**                      | Lista todos os pagamentos |
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/pagamento/?ano=[ano]**            | Lista todos os pagamentos do ano especificado |
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/pagamento/?mes=[mes]**            | Lista todos os pagamentos do mês especificado |
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/pagamento/?ano=[ano]&mes=[mes]**  | Lista todos os pagamentos do ano e mês especificados |
| <div style="background-color: blue; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">POST</div>     | **/pagamento/**                      | Adiciona um novo pagamento |
| <div style="background-color: green; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">GET</div>      | **/pagamento/:id**                   | Retorna o pagamento por :id |
| <div style="background-color: orange; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">PUT</div>      | **/pagamento/:id**                   | Atualiza o pagamento por :id |
| <div style="background-color: red; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">DELETE</div>   | **/pagamento/:id**                   | Deleta um pagamento por :id |
| <div style="background-color: red; color: white; font-weight: bold; padding: 10px; text-align: center; border-radius: 5px;">DELETE</div>   | **/pagamento/**                      | Deleta todos os pagamentos |

**Exemplo de Pagamento**

```json
{
    "ano" : 2000,
    "mes" : 1,
    "valor" : 1000,
    "jogador" : 1
}
```

Validações para cadastrar novos pagamentos:

* `ano` deve ser superior ou igual a 1900;
* `mes` deve ser um valor entre 1 e 12 (correspondente ao número do mês);
* `valor` deve ser um número positivo maior que 0;
* `jogador` deve ser um ID válido de um jogador existente.
