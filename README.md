# A logarléc

A Logarléc egy local-multiplayer játék, amelyben a játékosoknak egy elátkozott labirintusban kell keresniük a Logarléc nevű ereklyét. A játékot mérnökhallgatókként lehet játszani, akiknek át kell navigálniuk magukat a labirintuson, miközben megpróbálják kijátszani az oktatókat.

![gameplay screenshot](https://github.com/gZsoz/cheat_sheet_dungeon_crawler/blob/main/res/images/gameplay.png)

## Letöltés
### GIT klónozás
A projektet le lehet klónozni:
```bash
git clone https://github.com/gZsoz/cheat_sheet_dungeon_crawler.git
```
### ZIP Letöltés
A projektet zip formátumban is le lehet tölteni. A zip fájlt ki kell csomagolni.

### Mappába lépés
A fordítás és futtatáshoz szükséges a repository mappájába lépés és a graphic branch-re váltás
```bash
cd cheat_sheet_dungeon_crawler

git checkout graphic
```

## Fordítás és futtatás
A program osztályait le kell fordítani, hogy futtatható legyen program:
```bash
javac -cp .;src -d bin src\controller\*.java src\model\characters\*.java src\model\environmentalfactors\*.java src\model\items\*.java src\model\items\decayingitems\*.java src\model\items\numberofusesitems\*.java src\model\items\specialitems\*.java src\model\map\*.java src\model\modelupdate\*.java src\view\utils\*.java src\view\viewcharacters\*.java src\view\viewenvironmentalfactors\*.java src\view\viewitems\*.java src\view\viewitems\viewdecayingitems\*.java src\view\viewitems\viewnumberofusesitems\*.java src\view\viewitems\viewspecialitems\*.java src\view\viewmap\*.java src\main\*.java
```
Játék futtatása:
```bash
java -cp bin main.Main
```
## Tesztelés
TesztProgram futtatása:
```bash
  java -cp bin main.Main test
```
Összes teszt futtatása:
```bash
  java -cp bin main.Main test all
```


## Közreműködők
<a href="https://github.com/gZsoz/cheat_sheet_dungeon_crawler/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=gZsoz/cheat_sheet_dungeon_crawler" />
</a> 
