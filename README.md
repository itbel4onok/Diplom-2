# Diplom-2

<h2>What is this repository for?</h2>
<ul>
<li> Automation testing project for the next service: <a href="https://stellarburgers.nomoreparties.site/"> https://stellarburgers.nomoreparties.site/ </a>
</li>
</ul>

<h2>Technologies</h2>
<ul>
<li> Java: 11
</li>
<li> JUnit: 4.13.2
</li>
<li> Maven: 3.8.1
</li>
<li> Selenium: 3.141.59
</li>
<li> Allure: 2.21.0
</li>
</ul>

<h2>Project structure</h2>
Main/java/ contains package
<ul>
<li> "api" -> classes for sending api request (create/remove test-user) </li> 
<li> "base" -> classes for base actions/ base test/ base setting </li>
<li> "constants" -> classes with constants values such text/ correct values/ URL links </li> 
<li> "generation" -> class for generating random data for test-user </li>
<li> "objects" -> classes with page objects for elements on different pages </li> 
<li> "resources" -> classes for UserData structure </li>
</ul>

test/java/ contains tests
<ul>
<li> Registration process</li>
<li> Login/Logout process </li>
<li> Opening personal account</li> 
<li> Constructor opening and works with selectors</li>
</ul>
