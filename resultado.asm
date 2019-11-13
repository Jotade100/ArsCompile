.data
array: .word 0, 0, 0, 0, 0
a: .word 0
b: .word 0
i: .word 0
j: .word 0
array2: .word 0
.text
jal main
li $v0, 10
syscall
pruebita:
lw $t0, a
lw $t0, b
#inicio de bloque
move $v0, $s0
jr $ra
main:
#inicio de bloque
lw $t0, i
lw $t0, j
# ForStatement
# For id = expr
li $s0, 0
sw $s0, b
# {}
# expr binop expr
lw $s0, i
sub $sp,$sp,4
sw $s0,($sp)
li $s0, 4
move $t1, $s0
lw $t0,($sp)
addiu $sp,$sp,4
slt $s0, $t1, $t0
# Expresion
bne $s0, $zero, FinForedu.arscompile.modelos.Token.ee7d9f1
InicioForedu.arscompile.modelos.Token.ee7d9f1:
#inicio de bloque
lw $t0, array2
# IfStatement
# expr binop expr
lw $s0, j
sub $sp,$sp,4
sw $s0,($sp)
li $s0, 0
move $t1, $s0
lw $t0,($sp)
addiu $sp,$sp,4
slt $s2, $t0, $t1
slt $s0, $t1, $t0
or $s0, $s0, $s2
addi $s0, $s0, -1
li $s3, -1
mult $s0, $s3
mflo $s0
# Expresion
beq $s0, $zero, Elseedu.arscompile.modelos.Token.15615099
#inicio de bloque
# (expr)
li $s0, 5
# en $s0 queda el resultado de la expresión en paréntesis
# Expresion
la $t0, i
sw $s0, i
lw $s0, i
la $t3,array
move $t2, $s0
add $t2, $t2, $t2
add $t2, $t2, $t2
add $t1, $t2, $t3
# en $s0 se guarda el valor a retornar de la expresión
sub $sp,$sp,4
sw $t1,($sp)
lw $s0, i
lw $t1,($sp)
addiu $sp,$sp,4
sw $s0, 0($t1)
li $s0, 1
la $t0, j
sw $s0, j
j FinForedu.arscompile.modelos.Token.ee7d9f1
j FinIfedu.arscompile.modelos.Token.15615099
Elseedu.arscompile.modelos.Token.15615099:
#inicio de bloque
FinIfedu.arscompile.modelos.Token.15615099:
# expr binop expr
lw $s0, i
sub $sp,$sp,4
sw $s0,($sp)
li $s0, 4
move $t1, $s0
lw $t0,($sp)
addiu $sp,$sp,4
slt $s0, $t1, $t0
# Expresion
beq $s0, $zero, InicioForedu.arscompile.modelos.Token.ee7d9f1
FinForedu.arscompile.modelos.Token.ee7d9f1:
jr $ra

