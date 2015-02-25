# push, pushi and pop
pushi 20
pop $f0
push $f0
 
#add, negate, peek and slt
pushi 6
pushi 10
pushi 5
neg
peek $f0 0
peek $f1 2
add
slt

#slt should be 0
 
#and or
 
pushi 10
pushi 9
or
pushi 15
and
 
#or should be 11
 
#and should be 11
 
#beq
 
pushi 40
pushi 50
pushi 10
pushi 10
beq skip
pushi 30
skip: add
 
#jump
 
pushi 10
pushi 10
j skip
pushi 80
skip: add
 
#jal
 
pushi 10
pushi 10
jal skip
pushi 80
skip: add
push $ra