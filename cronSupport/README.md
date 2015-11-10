# SIS_grupo04 #

Proyecto SIS para la integración de entidades médicas y pacientes.


## ¿De qué se trata? ###

* Proyecto para validacion de tácticas y estílos de arquitectura para la clase de ARQUITECTURAS ÁGILES DE SOFTWARE
* Experimentos para validación de tacticas de latencia, concurrencia y disponibilidad

### Instalación y dependencias ###

* Versiones:
	* Java 1.8 (Java SE Runtime Environment (build 1.8.0_60-b27))
	* PostgreSQL 9.4.5 **sitio de descarga** http://www.enterprisedb.com/products-services-training/pgdownload#windows
	* Play Activator (activator-1.3.6-minimal)

###Creación de la base de datos###

>**NOTA:** Este usuario de base de datos debe tener todos los permisos asignados para poder crear las tablas y sus demás dependencias. y se asume que el comando psql esta configurado en las variables de entorno.

- Desde la consola de comandos, ubicarse dentro de la carpeta **setup** de este proyecto y ejecute el siguiente comando. (es posible que le pida la clave más de 1 vez)

```
psql -U postgres -W -f sis-db-install.sql
```

> **NOTA:** La aplicación utilizará la siguiente base de datos: jdbc:postgresql://localhost:5432/sis

###Eliminación de la base de datos

- Desde la consola de comandos, ubicarse dentro de la carpeta **setup** de este proyecto y ejecute el siguiente comando. (es posible que le pida la clave más de 1 vez)

```
psql -U postgres -W -f sis-db-uninstall.sql
```

##Autores
* Sebastian Gamba Pinilla
* German Dario Bernal
* German Leonardo Rojas
* Diego Fernando Romero
