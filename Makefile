
JC = javac
SOURCEPATH = src/
CLASSPATH = classes/
JARNAME = mazegame.jar

all: jar doc

classes: mazegame generation character npc player item main utils

mazegame: src/mazegame/*.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^

generation: src/mazegame/generation/*.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^

character: src/mazegame/character/*.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^

npc: src/mazegame/character/npc/*.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^

player: src/mazegame/character/player/*.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^

item: src/mazegame/item/*.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^

main: src/mazegame/main/*.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^

utils: src/mazegame/utils/*.java
	$(JC) -sourcepath $(SOURCEPATH) -d $(CLASSPATH) $^


doc: 
	javadoc -sourcepath src -d docs -subpackages mazegame

jar: classes
	jar cvfe $(JARNAME) mazegame.main.MainGame -C classes mazegame

run: jar
	java -jar $(JARNAME)

clean:
	$(RM) -r $(CLASSPATH)
	$(RM) -r docs/
	$(RM) $(JARNAME)