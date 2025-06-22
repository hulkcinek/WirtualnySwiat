# Założenia
- ~~przez "sąsiadujące" pola na planszy rozumiemy te graniczące ze sobą krawędzią, ale nie te rogami~~
- ~~jeżeli w trakcie poruszania zwierze wylosuje pole poza planszą, nie wykonuje ono żadnego ruchu w danej turze~~
- ~~po ataku zakończonym powodzeniem organizm nie zmienia swojego położenia, tylko zostaje na polu, z którego atakował~~
- jeżeli atakowany organizm ma równą siłę temu atakującemu, wygrywa atakujący
- symbole organizmów to pierwsza litera ich nazwy (w przypadku duplikatów, kolejna)
  - mała litera oznacza roślinę
  - wielka litera oznacza zwierzę
- zapis położeń i przesunięć to odpowiednio (x, y) i [x, y]
- ruch na ukos jest liczony jako jeden "krok"
- próba wykonania ruchu zawsze kończy się przesunięciem (niemożliwy jest powrót na to samo miejsce w trakcie jednej tury)
- zasieg ruchu N oznacza, że zwierzę może przejść na jakiekolwiek pole, do którego może dostać się w maksymalnie N ruchów, czyli wykonanych może zostać mniej "kroków" niż pozwalałby na to zasięg ruchu
- szansa na udanie się próby rozprzestrzeniania roślin w trakcie akcji to 1 do 6

# TODO
- po ataku zakończonym sukcesem atakujący przesuwa się na pole zajmowane przez pokonane zwierzę
- zaimplementować metody `kolizja()` dla organizmów i rozwiązać problem potrzeby wywołania tej funkcji na obu organizmach, żeby uwzględnić wszystkie specjalne umiejętności