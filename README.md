# RemoteOK App
> Este projeto visa a criação do aplicativo RemoteOK, voltado para desktops da plataforma Windows. O programa mostrará as oportunidades de trabalho remoto anunciados no site https://remoteok.io/, sendo atualizado  sempre que houver uma nova oportunidade anunciada, sendo possível favoritar e acompanhar as oportunidades que mais lhe agradarem.

![JAVA Version][java-image] ![miglayout-image] ![lombok-image] ![unirest-image]

## Requisitos funcionais

* :heavy_check_mark: Deverá ser criada uma aplicação desktop que mostre as oportunidades de trabalhos remotos em formato de lista;

* :heavy_check_mark: Quando o usuário encontrar uma oportunidade interessante, deve poder compartilhar via e-mail;

* :heavy_check_mark: O aplicativo será composto dos seguintes components: Duas abas: Lista de todos os jobs / Favoritos;

* :heavy_check_mark: O usuário poderá favoritar ou desfavoritar uma aplicação clicando no ícone de coração;

* :heavy_check_mark:Quando uma oportunidade da lista estiver favoritada o icone de coração deverá ter a cor vermelha sólida, do contrário deverá ser vazada, ou seja, sem preenchimento;

* :heavy_check_mark: A lista de favoritos deve ser salva em arquivo para que possa ser recuperado cada vez que o usuário entrar no aplicativo;

* :heavy_check_mark: Colocar a aplicação para funcionar no Systray do SO.
 


## Instalação

### Requisitos

* Java 8 *JDK 1.8*
* Git
* IDE (Netbeans)

### Netbeans

* Faça um clone do repositório com o comando `git clone https://github.com/JhonatanMedeiros/remote-ok-desktop.git` 
 na pasta que desejar;
* Abra o projeto na IDE em Arquivo -> Abrir projeto... -> remote-ok-desktop -> Abrir projeto;
* Você terá que adicionar as bibliotecas externas para poder rodar o projeto, para isso clique com o botão direito no projeto e vá em propriedades -> Bibliotecas -> Adicionar JAR/Pasta -> vá até a pasta do projeto e entre na pasta Libs -> selecione todos os arquivos desta pasta e clique Abrir e então clique em Ok;
* Pronto, está tudo configurado, apenas aperte no botão de rodar o projeto no Netbeans.

##### Login
Nome de usuário: remoteok
Senha: remoteok


## Exemplo de uso

Ache os jobs que procura

Adicione jobs nos favoritos para que possa encontrá-los com mais facilidade mais tarde

Compartilhe os jobs por e-mail

Filtre os jobs utilizando a caixa de texto no topo e apertando enter.

##  Licença

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](/LICENSE) para mais detalhes.



[java-image]: https://img.shields.io/badge/java-8.0-orange.svg
[miglayout-image]: https://img.shields.io/badge/MigLayout-3.5.5-red.svg
[lombok-image]: https://img.shields.io/badge/Lombok--brightgreen.svg
[jackson-image]: https://img.shields.io/badge/Jackson-2.9.8-lightblue.svg
[unirest-image]: https://img.shields.io/badge/Unirest-1.4.9-blue.svg