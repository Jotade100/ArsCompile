# ARS COMPILE :memo:
> "No one in the brief history of computing has ever written a piece of perfect software. It's unlikely that you'll be the first." 
> ~ Andy Hunt


El Proyecto del semestre segundo 2019 AD que consiste en un compilador de Decaf a MIPS. El nombre del proyecto es la unión de dos expresiones en latín que juntas significan el arte (*ars*) de la compilación (*compile*). La programación es una disciplina ,al igual que la música o la pintura, requiere práctica, técnica, ciencia y goza de gracia y belleza. 

Se escogió **Java** como el lenguaje para realizarlo debido a su capacidad multiplataforma. 

## Instalación e implementación
### Requerimientos
* JDK

### Instalación
Para instalar el archivo usar 
```
javac -d . *.java
```

### Implementación
Para correr el archivo usar 
```
java edu.arscompile.ArsCompile [option] <filename path>
```

## Contenido
### Documentación
* Documentación breve del proyecto, disponible en un archivo .pdf
* Documentación sobre las clases y elementos de código que lo componen.

### Librerías externas
* No se utilizaron librerías externas.

### Syntax highlighter para archivos `.decaf`
Se desarrolló y publicó una extensión en Visual Studio Marketplace para resaltar la gramática de archivos de decaf. Disponible en el Marketplace de Extensiones de Visual Studio Code como:
- `decaf syntax highlighter - Compi`

### Scanner
Scanner de análisis básico de Decaf. Entrega los tokens en el archivo *resultadosScanner.txt*. 


### Parser
Parser de análisis gramático de Decaf; despliega la lista de errores, y crea el Abstract Syntax Tree (AST). 

### Chequeo Semántico
Evalua los tokens entregados por el parser, para validar la coherencia lógica de los mismos, siguiendo las 18 reglas semánticas de Decaf.

### IRT
Crea el árbol de representación intermedia, para generar código posteriormente.

### Code Generation
Generación funcional de código, traducido a la arquitectura de MIPS.

## Observaciones
### Avance del proyecto
- [x] Scanner
- [x] Parser
- [x] AST
- [x] Semántica
- [x] IRT
- [ ] Optimizador
- [x] Generación de código

### Comentarios
* Los comentarios en lenguaje Decaf son de una sola línea, e inician con `// comentario`.

# Autores

**Juan Diego Sique Martínez** :musical_keyboard: *Universidad Francisco Marroquín* :notes: [Correo](juandiegosique@ufm.edu)

**Geordie Quiroa** :coffee: *Universidad Francisco Marroquín* :coffee: [Correo](gquiroa@ufm.edu)


