# API Atacado dos Presentes

## Descrição do Projeto
A API Atacado dos Presentes é o serviço RESTful oficial projetado para ser consumido pelo Portal Web e aplicativo móvel Atacado dos Presentes. Desenvolvida usando o framework Spring Boot, esta API fornece um conjunto de endpoints que facilitam a comunicação entre os componentes do sistema e os clientes que usam esses serviços.

## Funcionalidades
A API abrange uma variedade de funcionalidades, incluindo, mas não se limitando a:

- **Gerenciamento de Produtos**: Criar, recuperar, atualizar e deletar informações de produtos.
- **Gerenciamento de Categorias**: Organizar produtos em categorias e gerenciar essas categorias.
- **Processamento de Pedidos**: Realizar pedidos, atualizar status do pedido e gerenciar detalhes do pedido.
- **Gerenciamento de Clientes**: Registrar clientes, gerenciar informações do cliente e recuperar dados do cliente.
- **Interação com Fornecedores**: Gerenciar informações e interações com fornecedores.
- **Autenticação e Autorização de Usuários**: Lidar com o cadastro de usuários, login e controle de acesso.
- **Consulta de Endereço**: Recuperar informações de endereço com base em códigos postais.
- **Avaliações de Produtos**: Permitir que os clientes postem e gerenciem avaliações de produtos.

## Configuração do Ambiente
Para configurar seu ambiente de desenvolvimento e iniciar a API em sua máquina local, siga estas etapas:

1. Clone o repositório:

https://github.com/thiagoregueira/atacados_dos_presentes_Java_SpringBoot_API

2. Navegue até o diretório:

cd atacado-presentes-api

3. Instale as dependências:

mvn instalar

4. Configure as variáveis de ambiente, incluindo as configurações de conexão com o banco de dados.
5. Inicie a aplicação:

MVN spring-boot:executar

## Estrutura do Banco de Dados
A API depende de uma estrutura de banco de dados robusta para armazenar e gerenciar dados. O diretório `src/main/resources/db/migration` contém arquivos de migração SQL que detalham a evolução do esquema do banco de dados, incluindo a criação e modificação de tabelas.


## Agradecimentos
Este projeto foi desenvolvido durante o curso Java Experience na Treina Recife.

<div align="center">
<p>Obrigado pelo seu interesse na API Atacado dos Presentes!</p>
</div>

