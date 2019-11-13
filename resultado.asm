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
	# guardar la $ra para que no se pierda
	sub $sp, $sp,4
	sw $ra, ($sp)
	lw $t0, a
	lw $t0, b
	# inicio de bloque
	li $s0, 1
	la $t3,array
	move $t2, $s0
	add $t2, $t2, $t2
	add $t2, $t2, $t2
	add $t1, $t2, $t3
	# en $s0 se guarda el valor a retornar de la expresión
	sub $sp,$sp,4
	sw $t1,($sp)
	li $s0, 1
	lw $t1,($sp)
	addiu $sp,$sp,4
	sw $s0, 0($t1)
	li $s0, 3
	la $t0, a
	sw $s0, a
	li $s0, 3
	move $v0, $s0
	# pop de $ra
	lw $ra,($sp)
	addiu $sp,$sp,4
	jr $ra
main:
	# guardar la $ra para que no se pierda
	sub $sp, $sp,4
	sw $ra, ($sp)
	# inicio de bloque
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
	bne $s0, $zero, FinForedu.arscompile.modelos.Token.7637f22
InicioForedu.arscompile.modelos.Token.7637f22:
	# inicio de bloque
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
	beq $s0, $zero, Elseedu.arscompile.modelos.Token.4926097b
	# inicio de bloque
	# (expr)
	li $s0, 5
	# en $s0 queda el resultado de la expresión en paréntesis
	# Expresion
	la $t0, i
	sw $s0, i
	li $s0, 1
	la $t3,array
	move $t2, $s0
	add $t2, $t2, $t2
	add $t2, $t2, $t2
	add $t1, $t2, $t3
	# en $s0 se guarda el valor a retornar de la expresión
	sub $sp,$sp,4
	sw $t1,($sp)
	li $s0, 1
	lw $t1,($sp)
	addiu $sp,$sp,4
	sw $s0, 0($t1)
	li $s0, 1
	la $t0, j
	sw $s0, j
	# se guardan los valores antes de llamar al método
	# push de a
	lw $s0,a
	sub $sp, $sp,4
	sw $s0, ($sp)
	# mandar parámetro a a
	# resolviendo la expresión del parámetro
	li $s0, 1
	sw $s0,a
	# push de b
	lw $s0,b
	sub $sp, $sp,4
	sw $s0, ($sp)
	# mandar parámetro a b
	# resolviendo la expresión del parámetro
	li $s0, 2
	sw $s0,b
	jal pruebita
	move $s0, $v0
	j FinForedu.arscompile.modelos.Token.7637f22
	j FinIfedu.arscompile.modelos.Token.4926097b
Elseedu.arscompile.modelos.Token.4926097b:
	# inicio de bloque
FinIfedu.arscompile.modelos.Token.4926097b:
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
	beq $s0, $zero, InicioForedu.arscompile.modelos.Token.7637f22
FinForedu.arscompile.modelos.Token.7637f22:
	# pop de $ra
	lw $ra,($sp)
	addiu $sp,$sp,4
	jr $ra

