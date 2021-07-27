# InstaSalle2
Practica 2 PAED - Alexander Roca/Marc Céspedes

## 1 Introducció
InstaSalle és una xarxa social desenvolupada per compartir imatges en format de post. L’aplicació
permet als usuaris del sistema fer fotografies per mostrar-les a les seves amistats o seguidors. A
part de permetre compartir publicacions, també ofereix la possibilitat d’interactuar amb aquestes,
on es podran comentar publicacions d’altres usuaris i donar-li m’agrades.
Davant l’increment constant d’usuaris, InstaSalle vol reestructurar-se com una arquitectura distribuïda.
En comptes de disposar d’un únic servidor, es vol instaurar una xarxa de servidors arreu del món
on tots es troben interconnectats.
Una arquitectura distribuïda permet més capacitat de càlcul i també oferir una millor qualitat de
servei als usuaris segons la seva ubicació. Això és degut a que no és el mateix usar una aplicació
on el servidor es troba a la Xina si l’usuari està a França.
Aquest projecte implica dos problemes: 
 - Com distribuir els usuaris en els diferents servidors? 
 - Com connectar aquests servidors? Mitjançant algorismes pensats per solucionar problemes d’optimització combinatòria, s’espera trobar una resposta.
## 2 Format de les dades
Els datasets proporcionats estaran en format JSON i contindran la informació dels usuaris del sistema.
### 2.1 Usuaris
Cada usuari tindrà:
* followers: número de seguidors
* follows: número de persones a qui segueix
* activity: valor real que indica la mitjana d’hores que l’usuari es troba usant l’InstaSalle.
* connections: relacions d’un mateix contra els altres usuaris
* username: persona amb qui es relaciona
* since: des de quan el segueix. Aquesta dada estarà en format timestamp
* visits: quantes vegades ha consultat el seu perfil
* likes: quants m’agrades li ha proporcionat a les seves publicacions
* comments: quants comentaris li ha proporcionat a les seves publicacions
* posts: informació de les publicacions d’un mateix
* id: identificador del post
* liked by: usuaris que han donat m’agrada al post
* commented by: usuaris que han comentat el post
* published: quan es va publicar. Aquesta dada estarà en format timestamp
* location: coordenades des d’on s’ha publicat. Aquestes dades vindran donades per
[latitud, longitud]
* category: categoria identificativa de la publicació. Les categories que es tindran en
compte seran les següents:
    * Landscape
    * Food
    * Sports
    * Style
    * Animals
    * TV Shows
    * Fitness
    * Science & Tech
    * Music
    * Travel
    * Architecture
## 2.2 Servidors
Cada servidor disposar`a de la següent informació:
  * id: identificador numèric del servidor.
  * country: país on es troba el servidor.
  * location: coordenades del servidor. Aquestes dades vindran donades per [latitud, longitud]
  * reachable from: nodes de la xarxa que es troben directament connectats amb aquest servidor.
## 2.3 Nodes de la xarxa
Cada node de la xarxa contindrà la següent informació:
  * id: identificador numèric del node.
  * reliability: valor real entre 0 i 1 inclosos que determina la fiabilitat del node. Quant més alt
el valor, més fiable.
  * connects to: informació sobre la connexió entre nodes
  * Cada connexió entre dos nodes serà bidireccional i disposarà de la següent informació:
    * name: nom de la relació (c[id node 1][id node 2]).
    * cost: valor numèric que representa el cost d’anar d’un node a l’altre. Quant més alt, més
costós.
3
## 3 Funcionalitats
### Disponibilitat
Els diferents servidors es troben interconnectats a través d’Internet mitjançant una xarxa de nodes.
Si un usuari que es troba en un servidor A vol interactuar amb un altre usuari en un servidor B,
s’ha d’usar la xarxa per tal d’actualitzar les dades del segon usuari.
Hi ha múltiples camins possibles entre els diferents servidors, sent alguns més ràpids i/o fiables
que altres. L’objectiu és trobar 2 camins òptims entre tots els servidors. Es a dir, des de qualsevol ´
servidor concret, disposar de 2 camins per arribar a un dels altres servidors. Un dels camins haurà
de ser el més fiable possible, mentre que l’altre serà el que tingui un mínim cost.
Al ser una arquitectura distribuïda, guardar un únic camí entre cada servidor seria perillós, ja que
la caiguda d’un node d’aquest camí suposaria la pèrdua de connexió entre dos servidors així com
no disposar d’una alternativa. Es per això que es volen guardar 2 camins i no únicament un. ´
Nota: no s’ha de controlar que hi hagi nodes que formin part dels dos camins solució.
#### Càlcul de la fiabilitat d’un camí
Sent F un vector 1..n que conté el valor de fiabilitat de cada node d’un camí (probabilitat de que
no caigui), la fiabilitat d’aquest es calcula com:
  * f iabilitat camí = Yn
  * i=1
#### Distribució de càrrega
Volem distribuir els N usuaris en els M servidors disponibles. Per fer-ho, s’ha de tenir en compte
el següent i en aquest mateix ordre de prioritats:
  1. Equitativitat de càrrega: La distribució trobada ha de ser la més equitativa quan a càrrega
dels servidors. Es a dir, la diferència d’activitat entre els usuaris dels diferents servidors ha ´
de ser mínima.
  2. Proximitat servidor - usuari: Interessarà distribuir als usuaris en els servidors més propers
segons localització.
  La màxima prioritat és aconseguir equitativitat de càrrega d’activitat. Tot i això, davant la
possibilitat de trobar més d’una solució amb una diferència d’equitativitat mínima, es vol definir
un valor X de tolerància.
Es a dir, si es trobés una nova solució tal que la diferència entre l’equitativitat de la millor ´
solució trobada amb la nova fos inferior a aquesta tolerància, i la nova solució presenta una millor
distribució dels usuaris pel que fa a la localització, aquesta passarà a ser la millor. Aquesta
tolerància es definirà per l’alumne.
## 4 Càlcul equitativitat de càrrega
El càlcul de l’equitativitat de càrrega d’una solució es pot obtenir com:
  * equitativitat = servidor amb m´es c`arrega − servidor amb menys c`arrega
## 5 Implementació
L’implementació de la solució als diferents problemes s’ha de realitzar usant els algorismes vistos
durant el Bloc 2 de l’assignatura:
  1. Backtracking
  2. Branch & Bound
  3. Greedy
  4. Greedy + Backtracking
  5. Greedy + Branch & Bound\
L’objectiu és comparar les diferents tècniques segons:
  * Temps d’execució: Diferència de temps entre els algorismes.
  * Nombre de nodes visitats: Per exemple, pel cas de Backtracking seria el nombre de crides
recursives fetes.\
En el cas de Greedy respecte a la solució trobada per Backtracking i/o Branch & Bound, és
interessant comparar també la qualitat de la solució trobada i el temps d’execució. Es a dir, veure
si la relació qualitat de la solució - temps amb Greedy pot ser més interessant que les altres dues
tècniques.
Per tal d’executar les diferents opcions del programa, es recomana fer ús d’un menú on l’usuari
decideixi quina opció executar. Es podria sinó afegir la informació que es cregues oportuna com
a argument del programa. Qualsevol dels dos casos es contemplarà com a vàlid.
