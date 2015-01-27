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
