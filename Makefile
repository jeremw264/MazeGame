
JC = javac
SOURCEPATH = src/
CLASSPATH = classes/
JARNAME = jeu.jar

all: jar

cls: mazegame 

mazegame: src/mazegame/main/MainGame.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^ -cp jar/json-simple-1.1.1.jar

doc: 
	javadoc -sourcepath src -d docs -subpackages mazegame 

jar: cls
	cd classes/ && jar xvf ../jar/json-simple-1.1.1.jar
	jar cvfe $(JARNAME) mazegame.main.MainGame -C classes ./


clean:
	$(RM) -r $(CLASSPATH)
	$(RM) -r docs/
	$(RM) $(JARNAME)

.PHONY: clean doc