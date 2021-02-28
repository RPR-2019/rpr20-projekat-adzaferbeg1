# e-INDEX
rpr20-projekat-adzaferbeg1 created by GitHub Classroom

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

![Login View](https://raw.githubusercontent.com/RPR-2019/rpr20-projekat-adzaferbeg1/master/INTERFACE/login.PNG)
<br>
Projekat rađen u sklopu predmeta **Razvoj programskih rješenja** na **Elektrotehničkom fakultetu, Univerziteta u Sarajevu**. <br> Tema aplikacije jeste elektronska evidencija studentskih ocjena, odnosno e-index.
<br><br>Aplikacija omogućava *studentu* jednostavan uvid u 
ocjene i prikupljene bodove iz svih predmeta koje je odlušao ili trenutno sluša, uvid u aktuelne aktivnosti kao što su zadaće, ispiti ili generalne obavijesti, te mogućnost pregleda 
e-mail adrese svakog od aktuelnog profesora na fakultetu. <br> <br> Korisnik kao što je *admin* ima mogućnost registracije novog studenta, novog profesora ili uposlenika studentske 
službe, novog predmeta, te pregled izvještaja svih studenata i profesora na fakultetu. <br> <br>
Korisnik *profesor* ima mogućnost upisa studenta, iz liste svih studenata, na predmet koji drži, te pregled svih studenata koji slušaju njegove određene predmete. Također, 
profesor ima mogućnost unosa bodova i konačne ocjene za svakog od upisanih studenata. <br>





### Built With

* []() Java 8
* []() IntelliJ IDEA Community Edition 2019.2.3.



<!-- GETTING STARTED -->
## Getting Started

Za pokretanje aplikacije i ispravan rad, potrebno je pratiti sljedeće korake.

### Prerequisites

Lista biblioteka koje je potrebno uključiti prije pokretanja *Main* klase, preko **File > Project Structure > Libraries** i onda odabrati **add From Maven**:
* testfx 
  ```sh
  org.testfx:testfx-junit5:4.0.15-alpha
  ```
* jasperreports 
  ```sh
  net.sf.jasperreports:jasperreports:6.11.0
  ```
* sqlite 
  ```sh
  org.xerial:sqlite-jdbc:3.21.0
  ```
<br> Također potrebno je učitati i lokalni **SDK** i podesiti **VM Options**, te kao dodatnu pomoć instalirati **DB browser for SQLite**.

* VM Options 
  ```sh
  --module-path "path-to-lib" --add-modules javafx.controls,javafx.fxml
  ```



<!-- USAGE EXAMPLES -->
## Usage

![Admin View](https://raw.githubusercontent.com/RPR-2019/rpr20-projekat-adzaferbeg1/master/INTERFACE/admin.PNG)
![Student View](https://raw.githubusercontent.com/RPR-2019/rpr20-projekat-adzaferbeg1/master/INTERFACE/student.PNG)
![Teacher View](https://raw.githubusercontent.com/RPR-2019/rpr20-projekat-adzaferbeg1/master/INTERFACE/teacher.PNG)

_Za sve ostale informacije i način korištenja pročitati [Dokumentaciju](https://github.com/RPR-2019/rpr20-projekat-adzaferbeg1/blob/master/E_index_dokumentacija.pdf)_





<!-- CONTACT -->
## Contact

*Ajla Džaferbegović* - adzaferbeg1@etf.unsa.ba
