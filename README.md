# **_**Challenge ONE | Java | Back-end | Hotel Alura**_**

<p align="center" >
     <img width="300" heigth="300" src="https://user-images.githubusercontent.com/91544872/189419040-c093db78-c970-4960-8aca-ffcc11f7ffaf.png">
</p>

---

## 🚧 Proyecto

Challenge Hotel Alura, realizar un sistema de gestion de reservas y personas para un Hotel.

---

## ⚠️ Importante! ⚠️

☕ Use Java versión 11 o superior para compatibilidad. </br></br>

---

## 🖥️ Tecnologías Utilizadas:

* Java 11
* Programación Orientada Objetos
* Clases
* Herencia, Polimorfismo.
* Data Factory
* Lambda
* DAO (Data Access Object)
* MVC (Model - View - Controller)
* Java Swing
* Sobrescritura de métodos
* Creación de base de datos
* Consultas SQL


---
##### ***Data Base Connector MySQL***

> **Creacion de Base de Datos**
> 
> create database Hotel_alura;
> 
> create table Huespedes( id int auto_increment, nombre varchar(50) not null, apellido varchar(50) not null,
FechaNacimiento date not null,nacionalidad varchar(50) not null,telefono varchar(15) not null,IdReserva int, primary key(id) )engine = InnoDB;
>
>create table Reservas( id int auto_increment, FechaEntrada date not null,
FechaSalida date not null,Valor varchar(250),FormaPago varchar(250) not null, primary key(id) )engine = InnoDB;
>
>ALTER table Huespedes ADD foreign key (IDRESERVA) REFERENCES RESERVAS(ID);
>
>select * from Reservas;
>select * FROM HUESPEDES;

---

<p align="center" >
     <img width="300" heigth="300" src="./src/imagenes/screen/Screenshot_1.png">
</p>

---

<p align="center" >
     <img width="300" heigth="300" src="./src/imagenes/screen/Screenshot_2.png">
</p>

---

<p align="center" >
     <img width="300" heigth="300" src="./src/imagenes/screen/Screenshot_3.png">
</p>

---

<p align="center" >
     <img width="300" heigth="300" src="./src/imagenes/screen/Screenshot_4.png">
</p>

---

<p align="center" >
     <img width="300" heigth="300" src="./src/imagenes/screen/Screenshot_5.png">
</p>

---

<p align="center" >
     <img width="300" heigth="300" src="./src/imagenes/screen/Screenshot_6.png">
</p>

---

<p align="center" >
     <img width="300" heigth="300" src="./src/imagenes/screen/Screenshot_7.png">
</p>