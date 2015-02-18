pushi 10
jal relPrime

relPrime: pushi 2

loop2: push $ra
			peek $a0 2
			peek $a1 1
			jal GCD
			pop $ra
			pushi 1
			push $v0
			beq return
			pushi 1
	add
	j loop2

GCD: push $a0
	pushi 0
	beq returnb

loop: push $a1
			pushi 0
			beq returna
			push $a0
		push $a1
			slt
			pushi 1
			beq changeA

			push $a1
			push $a0
			neg
			add
			pop $a1
			j loop


changeA: push $a0
			push $a1
			neg
			add
			pop $a0
			j loop


returnb: push $a1
			pop $v0
			jr $ra


returna: push $a0
			pop $v0
			jr $ra

return: pop $v0
