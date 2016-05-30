# Aihemäärittely

Projektin tarkoituksena on tehdä ohjelma, joka opettaa neuroverkon tunnistamaan kuvia backpropagation-algoritmin avulla. Neuroverkon opetuksessa verkon painoja muutetaan liikuttamalla niitä virhefunktion gradientin vastaiseen suuntaan, kunnes virhefunktio on minimoitunut ja neuroverkko on oppinut tunnistamaan kuvat. Derivoinnin ketjusääntöön perustuva backpropagation-algoritmi mahdollistaa gradientin nopean laskemisen.

Ohjelmassa käytetään vain eteenpäin kytkettyjä neuroverkkoja. Neuroverkko koostuu kerroksista, joissa on neuroneita. Neuroneiden aktivaatio z_k kerroksessa k lasketaan täsmälleen edellisen kerroksen aktivaatioden perusteella eli z_k = f(z_{k - 1} missä f on jokin funktio. Olkoon L neuroverkon kerroksien lukumäärä. Olkoon l_k kerroksen k = 1, ..., L neuronien lukumäärä. Jos jokainen kerroksen k neuroni on kytketty kaikkiin edellisen kerroksen solmuihin ja neuronipariin liittyvän laskennan aikavaativuus on luokkaa O(1), neuronin aktivaation laskemisen aikavaativuus on luokkaa O(l_{k - 1}). Koska kerroksessa k on l_k neuronia, koko kerrokseen liittyvän laskennan aikavaativuus on O(l_{k - 1} l_k). Ensimmäisen kerroksen eli syötekerroksen aktivaatioden asettamisen aikavaativuus on O(l_1).

Koko neuroverkon aktivaatioden laskennan aikavaativuus T on kerrosten aikavaativuuksien summa eli

![Laskennan aikavaativuus](https://latex.codecogs.com/gif.latex?T%20%3D%20O%28l_1%20%5Csum_%7Bk%20%3D%202%7D%5E%7BL%7Dl_%7Bk-1%7D%20l_k%29)

