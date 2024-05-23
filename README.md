# A logarléc

A Logarléc egy local-multiplayer játék, amelyben a játékosoknak egy elátkozott labirintusban kell keresniük a Logarléc nevű ereklyét. A játékot mérnökhallgatókként lehet játszani, akiknek át kell navigálniuk magukat a labirintuson, miközben megpróbálják kijátszani az oktatókat.

## Letöltés
### GIT klónozás
A projektet le lehet klónozni:
```bash
git clone https://github.com/gZsoz/cheat_sheet_dungeon_crawler.git
```
### ZIP Letöltés
A projektet zip formátumban is le lehet tölteni. A zip fájlt ki kell csomagolni.

### Mappába lépés
A fordítás és futtatáshoz szükséges a src mappába lépés
```bash
cd cheat_sheet_dungeon_crawler\src
```

## Fordítás és futtatás
A program osztályait le kell fordítani, majd ez után futtatható lesz:
```bash
javac controller\*.java model\characters\*.java model\environmentalfactors\*.java model\items\*.java model\items\decayingitems\*.java model\items\numberofusesitems\*.java model\items\specialitems\*.java model\map\*.java model\modelupdate\*.java view\utils\*.java view\viewcharacters\*.java view\viewenvironmentalfactors\*.java view\viewitems\*.java view\viewitems\viewdecayingitems*.java view\viewitems\viewnumberofusesitems*.java view\viewitems\viewspecialitems*.java view\viewmap\*.java main\*.java

java main.Main
```
TesztProgram futtatása
```bash
  java main.Main test
```
Összes teszt futtatása:
```bash
  java main.Main test all
  ```

## Közreműködők
<a href="https://github.com/gZsoz/cheat_sheet_dungeon_crawler/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=gZsoz/cheat_sheet_dungeon_crawler" />
</a>
