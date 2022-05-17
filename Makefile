
JC = javac
SOURCEPATH = src/
CLASSPATH = classes/
JARPATH = jar/
JARNAME = jeu.jar

all: jeu.jar

cls: src/mazegame/main/MainGame.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^ -cp jar/json-simple-1.1.1.jar

doc: 
	javadoc -sourcepath $(SOURCEPATH) -d docs mazegame mazegame.action mazegame.challenge mazegame.character mazegame.character.npc mazegame.character.player mazegame.generation mazegame.item mazegame.main mazegame.utils -cp jar/json-simple-1.1.1.jar

jeu.jar: cls
	cd classes/ && jar xvf ../jar/json-simple-1.1.1.jar
	jar cvfe $(JARPATH)$@ mazegame.main.MainGame -C classes ./

run: jeu.jar
	clear
	@java -jar jar/jeu.jar 1


clean:
	$(RM) -r $(CLASSPATH)
	$(RM) -r docs/
	$(RM) $(JARPATH)$(JARNAME)

.PHONY: clean doc
