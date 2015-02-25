#pui 19
#pushi 176
#or
pushi 10
jal relPrime

relPrime: pushi 2

loop2: pop $a1
pop $a0
push $a0
push $a1
push $ra
jal GCD
pop $ra
pushi 1
push $v0
beq return
pushi 1
add
jal loop2

return: pop $v0

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
beq changea
push $a1
push $a0
neg
add
pop $a1
j loop

changea: push $a0
push $a1
neg
add
pop $a0
j loop

returna: push $a0
pop $v0
jr $ra

returnb: push $a1
pop $v0
jr $ra








