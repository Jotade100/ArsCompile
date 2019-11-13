.data
a: .word 0
edu.arscompile.modelos.Token.1ed6993a: .asciiz "Sí es"
edu.arscompile.modelos.Token.7e32c033: .asciiz "No es"
.text
	jal main
	li $v0, 10
	syscall
si:
	# guardar la $ra para que no se pierda
	sub $sp, $sp,4
	sw $ra, ($sp)
	lw $t0, a
	# inicio de bloque
	# IfStatement
	# expr binop expr
	# (expr)
	# expr binop expr
	lw $s0, a
	sub $sp,$sp,4
	sw $s0,($sp)
	li $s0, 2
	move $t1, $s0
	lw $t0,($sp)
	addiu $sp,$sp,4
	div $t0, $t1
	mfhi $s0
	# Expresion
	# en $s0 queda el resultado de la expresión en paréntesis
	# Expresion
	sub $sp,$sp,4
	sw $s0,($sp)
	li $s0, 0
	move $t1, $s0
	lw $t0,($sp)
	addiu $sp,$sp,4
	slt $s2, $t0, $t1
	slt $s0, $t1, $t0
	or $s0, $s0, $s2
	# Expresion
	beq $s0, $zero, Elseedu.arscompile.modelos.Token.7ab2bfe1
	# inicio de bloque
	li	$v0, 4
	la	$a0, edu.arscompile.modelos.Token.1ed6993a
	syscall
	j FinIfedu.arscompile.modelos.Token.7ab2bfe1
Elseedu.arscompile.modelos.Token.7ab2bfe1:
	# inicio de bloque
	li	$v0, 4
	la	$a0, edu.arscompile.modelos.Token.7e32c033
	syscall
FinIfedu.arscompile.modelos.Token.7ab2bfe1:
	# pop de $ra
	lw $ra,($sp)
	addiu $sp,$sp,4
	jr $ra
main:
	# guardar la $ra para que no se pierda
	sub $sp, $sp,4
	sw $ra, ($sp)
	# inicio de bloque
	li $s0, 3
	la $t0, a
	sw $s0, a
	# se guardan los valores antes de llamar al método
	# push de a
	lw $s0,a
	sub $sp, $sp,4
	sw $s0, ($sp)
	# mandar parámetro a a
	# resolviendo la expresión del parámetro
	lw $s0, a
	sw $s0,a
	jal si
	# pop de a
	lw $s0,($sp)
	addiu $sp,$sp,4
	sw $s0,a
	move $s0, $v0
	# pop de $ra
	lw $ra,($sp)
	addiu $sp,$sp,4
	jr $ra

