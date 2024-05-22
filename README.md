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
A fordítás és futtatáshoz szükséges a src mappába lépés
```bash
cd cheat_sheet_dungeon_crawler\src
```

## Fordítás és futtatás
A program osztályait le kell fordítani, majd ez után futtatható lesz:
```bash
javac Character\*.java EnvironmentalFactor\*.java Items\*.java Map\*.java Time\*.java View\Controller\*.java View\Utils\*.java View\ViewCharacter\*.java View\ViewEnvironmentalFactor\*.java View\ViewItem\*.java View\ViewMap\*.java Main\Main.java

## Tesztelés
java Main.Main
```
TesztProgram futtatása
```bash
  java Main.Main test
```
Összes teszt futtatása:
```bash
  java Main.Main test all
  ```

## Közreműködők
<a href="https://github.com/gZsoz/cheat_sheet_dungeon_crawler/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=gZsoz/cheat_sheet_dungeon_crawler" />
</a> 
