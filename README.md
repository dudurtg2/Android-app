# Android MySQL App
Descrição:
 - Este é um aplicativo Android desenvolvido em Java que utiliza o MySQL como banco de dados. O objetivo deste projeto é demonstrar como conectar um aplicativo Android a um banco de dados MySQL para realizar operações CRUD (Create, Read, Update, Delete).

## **Funcionalidades**
 - Conexão com o MySQL: Estabelece uma conexão com um banco de dados MySQL remoto.
 - Operações CRUD*
   - Create: Adiciona novos registros ao banco de dados.
   - Read: Recupera registros do banco de dados.
   - Update: Atualiza registros existentes no banco de dados.
   - Delete: Remove registros do banco de dados.
- *Interface de Usuário*
  - Interface simples para interação com o banco de dados.

## **Estrutura do Projeto**
 - src/: Contém o código-fonte Java do aplicativo.
 - res/: Contém recursos como layouts XML, strings e outros recursos de UI.
 - lib/: Bibliotecas necessárias para a conexão com o MySQL.
 - AndroidManifest.xml: Arquivo de manifesto do Android.
 - build.gradle: Arquivo de configuração do Gradle para o projeto.

## **Requisitos**
 - Android Studio: IDE para desenvolver aplicativos Android.
 - JDK: Java Development Kit.
 - MySQL Server: Servidor MySQL configurado e em execução.
 - Bibliotecas de Conexão: Bibliotecas JDBC para conectar-se ao MySQL.
   
## **Configuração**
-  Clone o Repositório
```
  git clone https://github.com/dudurtg2/android-mysql-app.git
  cd android-mysql-app
```
### Configuração do Banco de Dados
-  Crie um banco de dados MySQL e configure suas tabelas conforme necessário.
Atualize as credenciais de conexão (host, username, password, database) no código fonte do aplicativo.
Importe o Projeto no Android Studio

### Abra o Android Studio.
-  Selecione Open an existing Android Studio project.
-  Navegue até o diretório do projeto clonado e abra-o.
### Adicionar Bibliotecas JDBC
-  Adicione a dependência no arquivo build.gradle.
 ```
dependencies {
    implementation 'mysql:mysql-connector-java:5.1.49'
}
```
-  Executar o Aplicativo


### Conecte um dispositivo Android ou utilize um emulador.
- Clique em Run no Android Studio para compilar e executar o aplicativo.
### Uso
-  Tela de Login/Registro: Permite que o usuário se registre e faça login.
Dashboard: Após o login, o usuário pode visualizar, adicionar, atualizar e excluir registros no banco de dados MySQL.
