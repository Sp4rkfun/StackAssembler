.globl test[4] = 1 12 16 24
.globl size[1] = 4
pushi 2
pushi 2
pushi 3
pushi 4
beq relPrime
Stacksize 2
jal relPrime
pushi 2
pushi 2
relPrime: add
pushi relPrime
pushi 2
Dumpstack
Stackcontains 4 ? 2
peek $f0 test[1]
pushi 1
pop $f1
peek $f0 test[$f1]
peek $f1 size[0]
push $f1
pushi 0
loop: beq done
push $v0
peek $v0 test[$a0]
push $v0
add
pop $v0
push $f1
push $a0
pushi 1
add
peek $a0 0
pushi loop
j
done: push $v0
