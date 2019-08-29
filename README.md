# ARS COMPILE :mag:
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
java edu.arscompile.ArsCompile [option] <filename>
```

## Contenido
### Librerías externas
* Automaton (Evaluador de expresiones regulares)

### Scanner
* Scanner de análisis básico de Decaf. Entrega los tokens en el archivo *resultadosScanner.txt*. 


## Observaciones
### Avance del proyecto
- [x] Scanner
- [ ] Parser
- [ ] AST
- [ ] Semántica
- [ ] IRT
- [ ] Optimizador
- [ ] Generación de código

### Comentarios
* No posee tolerancia a comentarios

# Autor

**Juan Diego Sique Martínez** :musical_keyboard: *Universidad Francisco Marroquín* :notes: [Correo](juandiegosique@ufm.edu)


