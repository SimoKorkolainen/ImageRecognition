## Viikkoraportti viikolle 5


Tällä viikolla puuhastelin ohjelman käyttöliittymän parissa. Nyt neuroverkkoa voi opettaa graafisen käyttöliittymän välityksellä.

Koodasin ohjelmaan neuroverkkoa visualisoivan grafiikkaa. Lisäksi koodasin graafin, joka visualisoi neuroverkon suoriutumista oppimisdatassa ja testidatassa.

Nyt ohjelmassa on mahdollista syöttää neuroverkon rakenne. Esimerkiksi syötteellä:

Hidden layers: 10, 5

Activation function: softmax

Myös oppimiseen liittyviä asetuksia pystyy nyt muuttamaan käyttöliittymästä. Neuroverkon ylisovittamista voi havainnollistaa ensiksi painamalla Clear sitten valitsemalla seuraavat asetukset:

Training images: 50

Training iterations: 50

learning rate: 0.000000001

Kun opetukseen käytettäviä kuvia on vähän, neuroverkko ylisovittaa painot helposti. Toinen ongelma on se, että neuroverkon rakenteen, painojen alustuksen satunnaisuuden ja oppimisnopeuden samanaikanen valinta on vaikeaa. Valinta pitää suorittaa todella tarkasti muuten tulokset eivät ole kovinkaan hyviä. Esimerkiksi jos learning rate on liian iso neuroverkon painoista voi tulla NaN, koska eksponenttifunktion arvo pyöristyy helposti nollaan tai äärettömään, jolloin laskenta menee sekaisin.
