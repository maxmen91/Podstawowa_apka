---

<h2 id="podstawowa-aplikacja-z-autoryzacją-oraz-serwerem-tokenów">Podstawowa aplikacja z autoryzacją oraz serwerem tokenów</h2>
<h2 id="działanie">Działanie</h2>
<p><img src="https://i.imgur.com/VHOh5wG.gif" alt="enter image description here"></p>
<p>Aplikacja zapisuje token(Zwrócony z serwera) w pamięci SHARED_PREFS przy logowaniu i przy starcie sprawdza czy owy token jest zapisany. Jeśli tak to od razu loguje, w przeciwnym wypadku trzeba się zalogować.</p>
<h2 id="rejestracja">Rejestracja</h2>
<p><img src="https://i.imgur.com/ALA0Tcq.gif" alt="enter image description here"><br>
Wbudowany w rejestracje jest Walidator hasła, Miernik siły hasła w czasie rzeczywistym. Również serwer przy próbie zarejestrowania zwraca response Forbidden jeśli już istnieje taki użytkownik lub adres email w bazie.<br>
TODO - streaming muzyki dla zalogowanych użytkowników</p>
<h2 id="darkmode">Darkmode</h2>
<p><img src="https://i.imgur.com/fjIVL03.gif" alt="enter image description here"><br>
Zaprogramowałem również dark mode, który uruchamiany jest przy przytrzymaniu layoutu. przy kliknięciu wyświetla się hint, który podpowiada aby przytrzymać layout.</p>
<h2 id="ogólne">Ogólne</h2>
<p>Do napisania aplikacji wykorzystałem:<br>
-Android Studio (Cała aplikacja napisana jest w języku Java) API V. 25 (Od Androida 7.1 wzwyż)<br>
-Intellij z nakładką Tomcat Smart i oczywiście serwerem TomEE<br>
-Wbudowany w Android Studio Emulator</p>

