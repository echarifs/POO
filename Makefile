# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: Test

Test:
	 javac -d bin -classpath bin/gui.jar -sourcepath ./src ./src/*


# Execution:

test1:
	java -classpath bin:bin/gui.jar TestBalls

test2:
	 java -classpath bin:bin/gui.jar TestBallsSimulator

test3:
	 java -classpath bin:bin/gui.jar TestBoidsSimulator

test4:
	 java -classpath bin:bin/gui.jar TestEventManager

test5:
	 java -classpath bin:bin/gui.jar TestPlusieursBoidsSimulator

test6:
	 java -classpath bin:bin/gui.jar TestProiesPredateursSimulator
test7:
	 java -classpath bin:bin/gui.jar TestConwaySimulator
test8:
	  java -classpath bin:bin/gui.jar TestImmigrationSimulator
test9:
	  java -classpath bin:bin/gui.jar TestSchellingSimulator

clean:
	rm -rf bin/*.class
