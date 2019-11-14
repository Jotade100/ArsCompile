.data
res: .word 0
a: .word 0
.text
	jal main
	li $v0, 10
	syscall
factorial:
	# guardar la $ra para que no se pierda
	sub $sp, $sp,4
	sw $ra, ($sp)
	lw $t0, a
	# inicio de bloque
	# IfStatement
	# expr binop expr
	lw $s0, a
	sub $sp,$sp,4
	sw $s0,($sp)
	li $s0, 1
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
	beq $s0, $zero, Elseedu.arscompile.modelos.Token.6438a396
	# inicio de bloque
	li $s0, 1
	move $v0, $s0
	# pop de $ra
	lw $ra,($sp)
	addiu $sp,$sp,4
	jr $ra
	j FinIfedu.arscompile.modelos.Token.6438a396
Elseedu.arscompile.modelos.Token.6438a396:
	# inicio de bloque
	# expr binop expr
	lw $s0, a
	sub $sp,$sp,4
	sw $s0,($sp)
	# se guardan los valores antes de llamar al método
	# push de a
	lw $s0,a
	sub $sp, $sp,4
	sw $s0, ($sp)
	# mandar parámetro a a
	# resolviendo la expresión del parámetro
	# expr binop expr
	lw $s0, a
	sub $sp,$sp,4
	sw $s0,($sp)
	li $s0, 1
	move $t1, $s0
	lw $t0,($sp)
	addiu $sp,$sp,4
	sub $s0, $t0, $t1
	# Expresion
	sw $s0,a
	jal factorial
	# pop de a
	lw $s0,($sp)
	addiu $sp,$sp,4
	sw $s0,a
	move $s0, $v0
	move $t1, $s0
	lw $t0,($sp)
	addiu $sp,$sp,4
	mult $t0, $t1
	mflo $s0
	# Expresion
	move $v0, $s0
	# pop de $ra
	lw $ra,($sp)
	addiu $sp,$sp,4
	jr $ra
FinIfedu.arscompile.modelos.Token.6438a396:
main:
	# guardar la $ra para que no se pierda
	sub $sp, $sp,4
	sw $ra, ($sp)
	# inicio de bloque
	# se guardan los valores antes de llamar al método
	# push de a
	lw $s0,a
	sub $sp, $sp,4
	sw $s0, ($sp)
	# mandar parámetro a a
	# resolviendo la expresión del parámetro
	li $s0, 5
	sw $s0,a
	jal factorial
	# pop de a
	lw $s0,($sp)
	addiu $sp,$sp,4
	sw $s0,a
	move $s0, $v0
	la $t0, res
	sw $s0, res

