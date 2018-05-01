SRCDIR=src
PKG=fr/insarouen/battleship
SRCTEST=src/tests
LIBDIR = lib
BINDIR = bin
TESTDIR = tests
CC = javac
EXEC = Battleship
JAVAFLAGS = -classpath $(BINDIR) -sourcepath $(SRCDIR) -d $(BINDIR)


all :  
	$(CC) $(JAVAFLAGS) $(SRCDIR)/$(PKG)/*.java

%.class : $(SRCDIR)/$(PKG)/%.java
	$(CC) $(JAVAFLAGS) $<

view/%.class : $(SRCDIR)/$(PKG)/view/%.java
	$(CC) $(JAVAFLAGS) $<

model/%.class : $(SRCDIR)/$(PKG)/model/%.java
	$(CC) $(JAVAFLAGS) $<

controller/%.class : $(SRCDIR)/$(PKG)/controller/%.java
	$(CC) $(JAVAFLAGS) $<

net/%.class : $(SRCDIR)/$(PKG)/net/%.java
	$(CC) $(JAVAFLAGS) $<
