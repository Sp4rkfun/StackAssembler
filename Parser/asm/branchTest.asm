# pushi/push/pop test
#pushi 10
#pushi 7
#pushi 4000
#pushi 1234
#pop $f0
#pop $f1
#pop $v0
#push $f0
#push $f1
#push $v0
#pop $a0
#pop $a1
#pop $a2

# branch
pushi 40
pushi 50
pushi 11
pushi 10
beq skip
pushi 30
skip: add
beq skip2
pushi 20
skip2: pushi 1
