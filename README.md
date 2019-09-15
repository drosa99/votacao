**Para o desenvolvimento desta API, foi utilizado:**
- Java com Springboot;
- Lombok - para substituir getters, setters e builders;
- Foi criada uma classe de Exceção _ExpectedException_ para lançar os erros da aplicação;
- Um arquivo de mensagens dentro da pasta resources, contendo todas as mensagens de erro e de validações utilizadas na aplicação;
- Foi configurado um Swagger, no qual todas as requisições estão funcionando ao subir a aplicação em: localhost:8080 ;
- Foram feitos testes unitários utilizando jUnit;
- Maven;
- O desenvolvimento foi feito utilizando a IDE Intellij;


**Instruções para rodar a aplicação:**
- O banco de dados utilizado foi Oracle, dispnibilizado em uma imagem Docker (https://hub.docker.com/r/deepdiver/docker-oracle-xe-11g);
- Para rodar esta imagem: docker run -d -p 49160:22 -p 49161:1521 deepdiver/docker-oracle-xe-11g;
- Após rodar a imagem, não é necessária mais nenhuma ação relacionada ao banco de dados, e já é possível subir a aplicação; 
- Para subir a aplicação, importar este projeto Maven.

**Escolhas feitas no desenvolvimento:**
- Para garantir que um associado votasse apenas uma vez em uma pauta, decidi criar uma chave primária _VotoPK_ com o cpf do associado e o id da pauta;
- Para o requisito "Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo
                    determinado na chamada de abertura ou 1 minuto por default);" escolhi fazer o controle do fechamento da sala pela hora do sistema, isto é, 
                    ao ser feita a requisição de iniciar votação, o atributo _Fechamento_ de tipo _LocalDateTime_ recebe a data e hora atual acrescida do tempo recebido na requisição ou 1 minuto. 
                    E então, no cadastro de Voto, é feita uma verificação se esta pauta está aberta (_Fechamento_ != null) e se a data/hora atual é menor do que a do atributo _Fechamento_.
- Para a tarefa bônus 1, foi utilizada a classe RestTemplate para consumir a API externa.


**Tarefa bonus 4**
- Para versionar a minha api, eu criaria um diretorio _v2_ dentro de _api_ e criaria os arquivos de api que fossem necessarios, caso houvesse mudança no tipo de alguma requisicao, 
ou houvesse adicao de algum atributo obrigatorio na requisicao ou no _pathVariable_, criando estes métodos e mantendo todos os métodos já existentes.
                 
