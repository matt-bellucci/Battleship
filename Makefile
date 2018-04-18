SRCDIR=src
PKGVIEW=fr/insarouen/view
SRCTEST=src/tests
LIBDIR = lib
BINDIR = bin
TESTDIR = tests
CC = javac
EXEC = Battleship
JAVAFLAGS = -classpath $(BINDIR) -sourcepath $(SRCDIR) -d $(BINDIR)


all : $(BINDIR)/$(EXEC)

$(PKGVIEW)/%.class : $(SRCDIR)/$(PKGVIEW)/%.java
	$(CC) $(JAVAFLAGS) $<
