# DOM
PRACTICA 4: 

Desde el departamento de recursos humanos de Umbrella Corp, nos han pedido que
diseñemos una pequeña aplicación que les ayude a realizar la gestión de sus empleados.
Esta aplicación debe almacenar la siguiente información de cada uno de los empleados:

● NIF → 9 caracteres (8 números y 1 letra)

● Nombre → 10 caracteres

● Apellidos → 10 caracteres

● Salario → double

La aplicación debe permitir la inserción, consulta, modificación y borrado de empleados
haciendo uso de ficheros binarios aleatorios.

● Consulta → Solicitará al usuario el NIF del empleado a consultar:

  ○ Si existe mostrará su información, en caso contrario indicará que el empleado
solicitado no existe.

● Inserción → Solicitará por teclado la información del nuevo empleado. Antes de
insertar comprobará si el nuevo NIF ya existe:
  ○ Si existe se debe informar al usuario.
  ○ En caso contrario se insertará.
● Modificación → Se solicitará por teclado el NIF del empleado y el nuevo importe de
su salario.

○ En caso de no existir se informará al usuario.
○ En caso contrario se realiza la modificación.
● Borrado → Se solicitará el NIF del empleado a borrar por teclado. Haremos un borrado
lógico situando su NIF a -1.
● Listado general → Muestra todos los empleados (No borrados) existentes en el
fichero.


PRACTICA 5:

En esta práctica vamos a modificar el código de la práctica 4 para, manteniendo lo más
inalterados posible el modelo y la vista, pasemos de trabajar con ficheros binarios a hacerlo
con ficheros XML a través del protocolo DOM.
1. Diseña los métodos necesarios para insertar, modificar, borrar y consultar empleados
en un fichero XML haciendo uso de DOM.
