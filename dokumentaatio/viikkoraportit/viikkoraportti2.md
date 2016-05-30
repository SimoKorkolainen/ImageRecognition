
## Viikkoraportti viikko 2

Tällä viikolla keskityin neuroverkkoihin liittyvään laskentaan. Työtä hankaloitti huomattavasti se, että tietokoneeni näytön taustavalo meni rikki, enkä pystynyt työskentelemään kunnolla kotona.

Koodasin matriisiluokan vektorifunktioiden Jakobiaanien laskemiseen. Jakobiaanilla J tarkoitetaan vektorifunktion derivaattaa, jossakin pisteessä x. Tässä pisteessä funktiota on mahdollista arvioida lokaalisti lineaarikuvauksella eli f(x + h) = f(x) + J (h - x) + O(||h||^2). Koodissani olen laskenut jakobiaanit derivointiin liittyviä matemaattisia tuloksia käyttäen.

Käytin myös paljon aikaa laskennan testaamiseen, ja löysin paljon virheitä. Miten helppoa onkaan vahingossa kirjoittaa numero yksi nollan sijaan tai tehdä jotain muuta tyhmää. Testaamista varten tein koodin, joka laskee Jakobiaanin numeerisesti käyttäen derivaatan määritelmää. Tarkemmin sanottuna käytin hyväkseni sitä tietoa, että derivaatta f'(x) on lähellä lukua (f(x + h) - f(x)) / h, kun h on itseisesti pieni.

Seuraavalla viikolla tarkoituksenani on tehdä neuroverkon opettamiseen liittyvää toiminnallisuutta. Saatan myös muuttaa ohjelman rakennetta erilaiseksi, mikäli osoittautuu, että se on aiheellista. Onko viikolla kolme tarkoitus kirjoittaa vertausarviointi vai vasta viikon kolme päätyttyä?


