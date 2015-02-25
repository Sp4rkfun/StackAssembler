# add, neg, slt, peek
# pushi 6
#pushi 10
#pushi 5
#neg
#peek $f0 0
#peek $f1 2
#add
#slt

# or, and
#pushi 10
#pushi 9
#or
#pushi 15
#and

# All
pushi 1
pushi 2
add
pop $f0
pushi 1
neg
pop $f0
pushi -1
neg
pop $f1
pushi 1
pushi 1
and
pop $f0
pushi 1
pushi 0
and
pop $f1
pushi 1
pushi 1
or
pop $f0
pushi 1
pushi 0
or
pop $f1
pushi 0
pushi 1
slt
pop $f0
pushi 1
pushi 0
slt
pop $f1
pushi 2
sll 4
pop $f0
