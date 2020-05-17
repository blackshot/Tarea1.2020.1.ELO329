# README

En este repositorio se desarrolla la tarea 1 de la asignatura ELO329.
Aquí encontrará cada carpeta perteneciente a cada respectiva etapa, estas contienen un archivo makefile para poder compilar y ejecutar el respectivo código y el código necesario para implementar dicha etapa.

#### Como correr y compilar
Para cada etapa se debe posicionar en la respectiva carpeta. Desde ahí usted puede:
`$ make` : con esto compilara los debidos objetos y clases.
`$ make run` : correrá el programa, haciendo uso del archivo de entrada "DroneCommands.csv" y produciendo un archivo de salida "DroneTrayectory.csv".
`$ make clean` : limpia el entorno de trabajo de archivos .class, y de ser necesario puede modificar el makefile para eliminar "DroneTrayectory.csv" también.

#### Clases en la tarea
**Actionable:** Interfaz que contiene el método takeAction.
**Drone:** clase del objeto drone, este contiene su posicion, velocidad y archivo de escritura.
**DroneState:** clase enum que determina los estados del dron.
**InputDevice:** clase abstracta que configura las acciones que debe realizar un dispositivo de entrada.
**Joystick:** clase del mando analogo que se controla.
**Joysticks:** clase tipo de *InputDevice*, esta utiliza archivos de entrada.
**Keyboard:** clase tipo de *InputDevice*, esta utiliza el teclado.
**Operator:** clase que simula las acciones de un operador real, mientras obtiene la informacion de un archivo de entrada.
**SkyController:** clase del controlador del dron, este le envía la informacion que ejecuta el operador.
**StageXTest:** clase main de cada etapa.

#### Trabajo en GIT
- Para empezar a trabajar en este repositorio usted debe ingresar los siguientes códigos mediante el uso de CLI:  
`git clone https://github.com/Rokscript08/Tarea1.2020.1.ELO329.git`  
`cd Tarea1.2020.1`  

- Descargar actualizaciones:  
`$ git pull`  

- No olvide agregar sus archivos con:  
`$ git add (agrega al indice)`  
`$ git commit (agrega al stack)`  
`$ git push -u origin master (envio de archivos o cambios)`  

- Para revisar lo que otros han hecho:  
`$ git log (muestra los commits realizados)`  

- Revisar su flujo de trabajo:  
`$ git status`  

- Además usted puede:  
`$ git show _#commit_ (ver un commit especifico efectuado)`  
`$ git diff / $ git diff _archivo_ (comparar cambios realizados)`  
`$ git checkout file/branch (cambia de rama o archivo, opcion -b crea una rama nueva)`
##### END
