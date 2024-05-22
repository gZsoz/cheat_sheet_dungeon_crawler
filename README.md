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

## Fordítás és futtatás
A program osztályait le kell fordítani, majd ez után futtatható lesz:
```bash
javac -cp .;src -d bin src/Model/EnvironmentalFactors/*.java src/Model/Items/DecayingItems/*.java src/Model/Items/NumberOfUsesItems/*.java src/Model/Items/SpecialItems/*.java src/Model/Characters/*.java src/Model/Map/*.java src/Model/Time/*.java src/Controller/*.java src/View/Utils/*.java src/View/ViewCharacter/*.java src/View/ViewEnvironmentalFactor/*.java src/View/ViewItem/ViewDecayingItems/*.java src/View/ViewItem/ViewNumberOfUsesItems/*.java src/View/ViewItem/ViewSpecialItems/*.java src/View/ViewMap/*.java src/Main/*.java

java -cp bin Main.Main
```
## Tesztelés
TesztProgram futtatása
```bash
  java -cp bin Main.Main test
```
Összes teszt futtatása:
```bash
  java -cp bin Main.Main test all
  ```

## Közreműködők
<a href="https://github.com/gZsoz/cheat_sheet_dungeon_crawler/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=gZsoz/cheat_sheet_dungeon_crawler" />
</a> 
