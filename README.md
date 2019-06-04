# Rota de Viagem #

Um turista deseja viajar pelo mundo pagando o menor preço possível independentemente do número de conexões necessárias.
Vamos construir um programa que facilite ao nosso turista, escolher a melhor rota para sua viagem.

Para isso precisamos inserir as rotas através de um arquivo de entrada.

## Input Example ##
```csv
GRU,BRC,10
BRC,SCL,5
GRU,CDG,75
GRU,SCL,20
GRU,ORL,56
ORL,CDG,5
SCL,ORL,20
```

## Explicando ## 
Caso desejemos viajar de **GRU** para **CDG** existem as seguintes rotas:

1. GRU - BRC - SCL - ORL - CDG ao custo de **$40**
2. GRU - ORL - CGD ao custo de **$64**
3. GRU - CDG ao custo de **$75**
4. GRU - SCL - ORL - CDG ao custo de **$48**
5. GRU - BRC - CDG ao custo de **$45**

O melhor preço é da rota **4** logo, o output da consulta deve ser **CDG - SCL - ORL - CDG**.

### Execução do programa ###
A inicializacao do teste se dará por linha de comando onde o primeiro argumento é o arquivo com a lista de rotas inicial.

```shell
$ mysolution input-routes.csv
```

Duas interfaces de consulta devem ser implementadas:
- Interface de console deverá receber um input com a rota no formato "DE-PARA" e imprimir a melhor rota e seu respectivo valor.
  Exemplo:
  ```shell
  please enter the route: GRU-CGD
  best route: GRU - BRC - SCL - ORL - CDG > $40
  please enter the route: BRC-CDG
  best route: BRC - ORL > $30
  ```

- Interface Rest
    A interface Rest deverá suportar:
    - Registro de novas rotas. Essas novas rotas devem ser persistidas no arquivo csv utilizado como input(input-routes.csv),
    - Consulta de melhor rota entre dois pontos.

Também será necessária a implementação de 2 endpoints Rest, um para registro de rotas e outro para consula de melhor rota.

## Recomendações ##
Para uma melhor fluides da nossa conversa, atente-se aos seguintes pontos:

* Envie apenas o código fonte,
* Estruture sua aplicação seguindo as boas práticas de desenvolvimento,
* Evite o uso de frameworks ou bibliotecas externas à linguagem. Utilize apenas o que for necessário para a exposição do serviço,
* Implemente testes unitários seguindo as boas praticas de mercado,
* Documentação
  Em um arquivo Texto ou Markdown descreva:
  * Como executar a aplicação,
  * Estrutura dos arquivos/pacotes,
  * Explique as decisões de design adotadas para a solução,
  * Descreva sua APÌ Rest de forma simplificada.

## Como executar a aplicação ##
# API #
* Entrar na pasta da aplicação API (ShortestPathRest);
* Executar o build da API:
```shell
./gradlew build
```
* Executar a aplicação:
```shell
java -jar ./build/libs/ShortestPathRest-1.0.jar ../input-file.txt
```

# Console #
* Entrar na pasta da aplicação Console (ShortestPath);
* Executar o build da aplicação:
```shell
./gradlew build
```
* Executar a aplicação:
```shell
java -jar ./build/libs/ShortestPath.jar
```

## Estrutura de Arquivos e Pacotes ##
- ShortestPath: Console de aplicação que será utilizada pelo usuário para obter o menor caminho;
  - src: Código Fonte da Aplicação
    - main: Aplicação
      - java/br/com/bexs/shortestpath
        - Application.java: Entrada da Aplicação, responsável pela iteração com o usuário: Entrada e Saída de informações;
        - HttpUtil.java: Responsável pela comunicação com a API, encapsula método de consulta de HTTP;
        - Route.java: POJO responsável pela estrutura de dados retorno do menor caminho;
        - Service.java: Realiza a consulta da API via HTTP (HttpUtil) e mapeia o resultado de Json para Objeto (Route).
    - test: Testes Unitários
- ShortestPathRest: Aplicação SpringBoot com a API Rest para encontrar o menor caminho e gerenciar as rotas;
  - src: Código Fonte da Aplicação API
    - main/java/br/com/bexs/shortestPathRest
      - ShortestPathRestApplication.java: Entrada da aplicação SpringBoot;
        - controller
          - Route.java: POJO responsável pela estrutura de dados retorno do menor caminho;
          - RoutesController.java: Responsável por expor os métodos de iteração com as rotas (inclusão, exclusão e obtenção);
          - ShortetestPathController.java: Responsável por expor o método de consulta de menor caminho;
        - database
          - City.java: POJO responsável pela estrutura de Cidades, utilizado para executar o algoritmo de Dijkstra;
          - DataBase.java: Mantém a estrutura de Cidades que será utilizada pelo algortimo de Dijkstra;
          - Graph.java: Responsável pela implementação do algortimo de menor caminho, baseado no ;
          - Route.java: POJO responsável pela estrutura de Rotas unindo os pontos de origem e destino, utilizado para executar o algoritmo de Dijkstra;
          - TextManagement.java: Realiza a iteração com o arquivo texto para leitura e gravação das informações;

## Decisão de Arquitetura ##
# Console #
- Criação de uma estrutura de chamada de HTTP (HttpUtil) encapsulando as chamadas HTTP, podendo ser extendido para futuras chamadas POST, DELETE, PUT, etc.;
- Como o console apenas realiza a integração com o menor caminho, foi criado um único serviço (Service), em caso de multiplas chamadas o intúito é criar serviços específico para cada uso, podendo segmentar futuramente em "packages" isolados;
- A aplicação está sendo executada na classe Application por ser extremamente enxuta, se houver necessidade de segmentação de funções, por exemplo, incorporar funcionalidade de manuntenção de rotas, então seria ideal criar um estrutura segmentada através de "packages", por exemplo: console.shortestPath, console.routes, etc. .

# API #
- Criada estrutura para Controller, que receberá todos os Endpoints expostos para consumo via REST;
- Inicialmente a lógica está ocorrendo na integração entre Controller e Graph, mas o ideal seria criar Services para cada utilização e DAO's na utilização de integração com Banco de Dados.

## Detalhamento da API ##
- /route
  - Método GET: Retorna todas as rotas disponíveis;
  - Método PUT: Inclui uma nova rota com a inclusão de um Json no formato abaixo:
  ```json
  {
    "origin": "SCL",
    "destination": "CDG",
    "distance": 50
  }
  ```
  - Método DELETE: Excluir uma rota previamente cadastrada
- /shortestPath
  - Método GET: Calcula e retorna o menor caminho entre dois pontos, se houver.