## Viikkoraportti viikolle 4

Tällä viikolla sain neuroverkon oppimisen toimimaan. Tällä hetkellä neuroverkko vaikuttaisi oppivan tunnistamaan testausdatan kuvan oikean luokan 30% tarkuudella. Tulos ei ole välttämättä paras mahdollinen. Tätä on vaikea arvioida, koska oppimistulos riippuu paljon käytetyn datan määrästä ja neuroverkon hyperparametreista, kuten oppimisnopeutta säätelevästä parametrista. Neuroverkko ylisovittaa hieman opetusdataan, koska pitkän opetussession jälkeen neuroverkko luokitteli opetusdatan kuvat 70% tarkuudella, mutta testausdatan kuvat vain 30% tarkuudella.

Huomasin, että koodissani luotiin matriiseja turhan usein, mikä hidasti ohjelman toimintaa. Muokkasin koodia kuitenkin siten, että koodi on nyt paljon nopeampaa verrattuna aikasempaan. Tietenkin edelleen ohjelman ajaminen suurilla syötteillä vie paljon aikaa ja muistia. 

Dokumentaatiosta en ole aivan varma mitä kaikkea testejä testausdokumenttiin tulisi sisällyttää. Esim. pitääkö noista PIT-raportin testeistä selitää jotain? Enkä myöskään varma testausdokumentaation halutusta tarkuudesta.

Tällä viikolla jäi vähän harmittamaan, ettei kukaan tehnyt minulle hartaasti odottaani koodikatselmointia. Toivottavasti seuraavalla viikolla edes joku tekisi koodikatselmoinnin. Sitä odotellessani seuraavalla viikolla aion räpeltää kaikkea mitä ehdin tehdä. 
