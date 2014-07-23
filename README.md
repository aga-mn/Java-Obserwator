Zadanie 2 (observer)
UWAGA! W zadaniu stosuje się nomenklaturę stosowaną w książce GOF "Design Patterns"

Input

stworzyć interfejs "FileInputStreamObserver" (pełniący rolę interfejsu "Observer" (GOF))

interfejs ma umożliwić konkretnym obserwatorom uzyskanie informacji na temat typu zaistniałej w obiekcie zmiany oraz w razie potrzeby dodatkowych danych
wyróżnianie typy zaistniałych zmian:
zamknięcie strumienia
dojścia do końca strumienia
odczytanie bajtu (należy podać wartość odczytanego bajtu oraz jego indeks)
odczytaniu grupy bajtów (należy podać wartości odczytanych bajtów, ideksy pierwszego i ostatniego odczytanego bajtu)
opuszczenie bajtów (należy podać ile bajtów zostało opuszczonych i jaki indeks ma ostatni opuszczony bajt)
stworzyć klasę "ObservableFileInputStream", która rozszerza klasę "FileInputStream" (klasa pełni rolę elementu "ConcreteSubject" (GOF))

klasa musi umożliwiać rejestrację konkretnych obserwatorów dla określonych typów zmian (tzn. obserwator zarejestrowany dla zdarzeń typu "zamknięcie strumienia" i "dojście do końca strumienia" nie będzie informowany o pozostałych zdarzeniach tj. "odczytanie bajtu", "odczytanie grupy bajtów" i "opuszczenie bajtów")
klasa musi umożliwić wyrejestrowanie obserwatorów z konkretnych typów zmian
Output

stworzyć interfejs "FileOutputStreamObserver" (pełniący rolę interfejsu "Observer" (GOF))

interfejs ma umożliwić konkretnym obserwatorom uzyskanie informacji na temat typu zaistniałej w obiekcie zmiany oraz w razie potrzeby dodatkowych danych
wyróżnianie typy zaistniałych zmian:
zamknięcie strumienia
zapisanie bajtu (należy podać wartość zapisanego bajtu oraz jego indeks)
zapisanie grupy bajtów (należy podać wartości zapisanych bajtów, ideksy pierwszego i ostatniego zapisanego bajtu)
oczyszczenie bufora i zapisanie jego zawartości w pliku
stworzyć klasę "ObservableFileOutputStream", która rozszerza klasę "FileOutputStream" (klasa pełni rolę elementu "ConcreteSubject" (GOF))

klasa musi umożliwiać rejestrację konkretnych obserwatorów dla określonych typów zmian
klasa musi umożliwić wyrejestrowanie obserwatorów
stworzyć implementację interfejsu "FileOutputStreamObserver", która zapisuje w pliku szczegóły (w raz z informacją o czasie odebrania informacji o zmianie) następujących zmian: - zamknięcie strumienia - zapisanie bajtu (indeks zapisanego bajtu) - zapisanie grupy bajtów (ideksy pierwszego i ostatniego zapisanego bajtu) - oczyszczenie bufora i zapisanie jego zawartości w pliku

stworzyć implementację interfejsu "FileOutputStreamObserver", która wyświetla na konsoli informację o zamknięciu strumienia

stworzyć implementację interfejsu "FileInputStreamObserver", która

wyświetla na konsoli informację o średnio odczytywanej liczbie bajtów na sekundę (informacje użytkownik otrzymuje na bieżąco, co 1000 zapisanych bajtów)
po otrzymania informacji o dojściu do końca strumienia wyświetla użytkownikowi całkowity czas poświęcony na odczytanie pliku
po otrzymaniu informacji o zamknięciu strumienia informuje o tym fakcie użytkownika drukując odpowiedni komunikat na konsoli
pokazać działanie wzorca projektowego "Observer"
UWAGA

zmienne nie mogą być publiczne
należy wszystko dokładnie przemyśleć, dla przykładu:
proszę zagwarantować sensowną implementację metody dodającej obserwator w interfejsie "Observable"
na przykład przekazanie w parametrze tej metody "nulla" powinno się zakończyć... no właśnie czym Panie i Panowie? ;)
a gdybyśmy tak do tej metody przekazali kolejny raz tę samą instancję obserwatora to co powinna ta metoda zrobić:
wygenerować wyjątek, a może...
nie zrobić nic, a może...
dodać ten obserwator po raz kolejny (będzie on więc informowany o zdarzeniach dwukrotnie)
proszę się zastanowić, jak w klasie "ObservableFileInputStream" będzie wpływać na przekazywane obserwatorom informacje metoda "skip"
a jak będą wpływać metody "mark" i "reset"?
proszę sensownie i elegancko obsłużyć wszelkie wyjątki
