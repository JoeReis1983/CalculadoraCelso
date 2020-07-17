# CalculadoraCelso
Calculadora com gradle
Realize o pull de todos os arquivos e proceda com a instalação do Gradle e dependencias.
Para o funcionamento do banco de dados é necessario alterar a linha 32 do arquivo src\main\java\controller\ConexaoBd.java com o 
caminho do banco e criar o database conforme abaixo: 

create database Galende;
use Galende;
create table UsuariosCalculadora 
(Id int(5) auto_increment primary key,
Usuario varchar(75) ,
Senha varchar(20)
);
use Galende;
create table LogCalculadora 
(
Usuario varchar(75),
Calculo varchar(20),
Tempo varchar(30)
);

insert into UsuariosCalculadora
values
(00001,"celso","celso");
insert into UsuariosCalculadora
values
(00002,"admin","admin");
insert into UsuariosCalculadora
values
(00003,"galende","galende");


Execute o gradle build e após o gradle AppRun
