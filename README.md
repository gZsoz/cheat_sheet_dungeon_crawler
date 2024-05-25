# A logarléc - PROTO

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
javac -cp .;src -d bin src/Character/*.java src/EnvironmentalFactor/*.java src/Items/*.java src/Map/*.java src/Time/*.java src/ProtoUtil/ProtoUtil.java
```
TesztProgram futtatása
```bash
  java -cp bin ProtoUtil.ProtoUtil
```
Összes teszt futtatása:
```bash
  java -cp bin ProtoUtil.ProtoUtil test
```

## Közreműködők
<a href="https://github.com/gZsoz/cheat_sheet_dungeon_crawler/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=gZsoz/cheat_sheet_dungeon_crawler" />
</a>
