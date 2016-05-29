
## Viikkoraportti viikko 2 (luonnos)

Mitä olen tehnyt tällä viikolla?
Miten ohjelma on edistynyt?
Mitä opin tällä viikolla / tänään?
Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.
Mitä teen seuraavaksi?

Tällä viikolla keskityin neuroverkkoihin liittyvään laskentaan.

Koodasin matriisiluokan vektorifunktioiden Jakobiaanien laskemiseen. Jakobiaanilla J tarkoitetaan vektorifunktion derivaattaa, jossakin pisteessä x. Tässä pisteessä funktiota on mahdollista arvioida lokaalisti lineaarikuvauksella eli f(x + h) = f(x) + J (h - x) + O(||h||^2).

Käytin myös paljon aikaa laskennan testaamiseen, ja löysin paljon virheitä. Miten helppoa onkaan vahingossa kirjoittaa numero yksi nollan sijaan. 

Koodissani olen laskenut jakobiaanit derivointiin liittyviä matemaattisia tuloksia käyttäen. Kuitenkin testaamista varten tein koodin, joka laskee Jakobiaanin numeerisesti käyttäen derivaatan määritelmää.
Tarkemmin sanottuna käytin hyväkseni sitä tietoa, että derivaatta f'(x) on lähellä lukua (f(x + h) - f(x)) / h, kun h on itseisesti pieni.

![moi](https://latex.codecogs.com/gif.latex?x^2+1)

![moii](https://latex.codecogs.com/gif.latex?\sum)
