---


---

<h2 id="podstawowa-aplikacja-z-autoryzacją-oraz-serwerem-tokenów">Podstawowa aplikacja z autoryzacją oraz serwerem tokenów</h2>
<h2 id="działanie">Działanie</h2>
<p><img src="https://media.discordapp.net/attachments/679062963453165589/692044278284943370/rekordostateczny.gif?width=441&amp;height=938" alt="Główne działanie"></p>
<p>Aplikacja zapisuje token(Zwrócony z serwera) w pamięci SHARED_PREFS przy logowaniu i przy starcie sprawdza czy owy token jest zapisany. Jeśli tak to od razu loguje, w przeciwnym wypadku trzeba się zalogować.</p>
<h2 id="rejestracja">Rejestracja</h2>
<p><img src="https://media.discordapp.net/attachments/679062963453165589/692045640133246996/sila_haslaorazzajeteemaileinazwy.gif?width=441&amp;height=938" alt="enter image description here"><br>
Wbudowany w rejestracje jest Walidator hasła, Miernik siły hasła w czasie rzeczywistym. Również serwer przy próbie zarejestrowania zwraca response Forbidden jeśli już istnieje taki użytkownik lub adres email w bazie.<br>
TODO - streaming muzyki dla zalogowanych użytkowników</p>
<h2 id="darkmode">Darkmode</h2>
<p><img src="https://media.discordapp.net/attachments/679062963453165589/692046040567644250/darkmode.gif?width=441&amp;height=938" alt="enter image description here"><br>
Zaprogramowałem również dark mode, który uruchamiany jest przy przytrzymaniu layoutu. przy kliknięciu wyświetla się hint, który podpowiada aby przytrzymać layout.</p>
<h2 id="serwer">Serwer</h2>
<p>Załączam również link do serwera jax-rs <a href="https://github.com/maxmen91/Serwikjwt">https://github.com/maxmen91/Serwikjwt</a><br>
Pozdrawiam Piotr Wójcik 4T</p>

