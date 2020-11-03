# InstaSalle2
Practica 2 PAED - Alexander Roca/Marc Céspedes

1 Introducci´o
InstaSalle ´es una xarxa social desenvolupada per compartir imatges en format de post. L’aplicaci´o
permet als usuaris del sistema fer fotografies per mostrar-les a les seves amistats o seguidors. A
part de permetre compartir publicacions, tamb´e ofereix la possibilitat d’interactuar amb aquestes,
on es podran comentar publicacions d’altres usuaris i donar-li m’agrades.
Davant l’increment constant d’usuaris, InstaSalle vol reestructurar-se com una arquitectura distribu¨ıda.
En comptes de disposar d’un ´unic servidor, es vol instaurar una xarxa de servidors arreu del m´on
on tots es troben interconnectats.
Una arquitectura distribu¨ıda permet m´es capacitat de c`alcul i tamb´e oferir una millor qualitat de
servei als usuaris segons la seva ubicaci´o. Aix`o ´es degut a que no ´es el mateix usar una aplicaci´o
on el servidor es troba a la Xina si l’usuari est`a a Fran¸ca.
Aquest projecte implica dos problemes. Com distribuir els usuaris en els diferents servidors? I com
connectar aquests servidors? Mitjan¸cant algorismes pensats per solucionar problemes d’optimitzaci´o
combinat`oria, s’espera trobar una resposta.
Figure 1: Exemple d’una arquitectura distribu¨ıda
1
2 Format de les dades
Els datasets proporcionats estaran en format JSON i contindran la informaci´o dels usuaris del
sistema.
2.1 Usuaris
Cada usuari tindr`a:
• followers: n´umero de seguidors
• follows: n´umero de persones a qui segueix
• activity: valor real que indica la mitjana d’hores que l’usuari es troba usant l’InstaSalle.
• connections: relacions d’un mateix contra els altres usuaris
– username: persona amb qui es relaciona
– since: des de quan el segueix. Aquesta dada estar`a en format timestamp
– visits: quantes vegades ha consultat el seu perfil
– likes: quants m’agrades li ha proporcionat a les seves publicacions
– comments: quants comentaris li ha proporcionat a les seves publicacions
• Posts: informaci´o de les publicacions d’un mateix
– id: identificador del post
– liked by: usuaris que han donat m’agrada al post
– commented by: usuaris que han comentat el post
– published: quan es va publicar. Aquesta dada estar`a en format timestamp
– location: coordenades des d’on s’ha publicat. Aquestes dades vindran donades per
[latitud, longitud]
– category: categoria identificativa de la publicaci´o. Les categories que es tindran en
compte seran les seg¨uents:
∗ Landscape
∗ Food
∗ Sports
∗ Style
∗ Animals
∗ TV Shows
∗ Fitness
∗ Science & Tech
∗ Music
∗ Travel
∗ Architecture
2.2 Servidors
Cada servidor disposar`a de la seg¨uent informaci´o:
• id: identificador num`eric del servidor.
2
• country: pa´ıs on es troba el servidor.
• location: coordenades del servidor. Aquestes dades vindran donades per [latitud, longitud]
• reachable from: nodes de la xarxa que es troben directament connectats amb aquest servidor.
2.3 Nodes de la xarxa
Cada node de la xarxa contindr`a la seg¨uent informaci´o:
• id: identificador num`eric del node.
• reliability: valor real entre 0 i 1 inclosos que determina la fiabilitat del node. Quant m´es alt
el valor, m´es fiable.
• connects to: informaci´o sobre la connexi´o entre nodes
Cada connexi´o entre dos nodes ser`a bidireccional i disposar`a de la seg¨uent informaci´o:
• name: nom de la relaci´o (c[id node 1][id node 2]).
• cost: valor num`eric que representa el cost d’anar d’un node a l’altre. Quant m´es alt, m´es
cost´os.
3
3 Funcionalitats
Disponibilitat
Els diferents servidors es troben interconnectats a trav´es d’Internet mitjan¸cant una xarxa de nodes.
Si un usuari que es troba en un servidor A vol interactuar amb un altre usuari en un servidor B,
s’ha d’usar la xarxa per tal d’actualitzar les dades del segon usuari.
Hi ha m´ultiples camins possibles entre els diferents servidors, sent alguns m´es r`apids i/o fiables
que altres. L’objectiu ´es trobar 2 camins `optims entre tots els servidors. Es a dir, des de qualsevol ´
servidor concret, disposar de 2 camins per arribar a un dels altres servidors. Un dels camins haur`a
de ser el m´es fiable possible, mentre que l’altre ser`a el que tingui un m´ınim cost.
Al ser una arquitectura distribu¨ıda, guardar un ´unic cam´ı entre cada servidor seria perill´os, ja que
la caiguda d’un node d’aquest cam´ı suposaria la p`erdua de connexi´o entre dos servidors aix´ı com
no disposar d’una alternativa. Es per aix`o que es volen guardar 2 camins i no ´unicament un. ´
Nota: no s’ha de controlar que hi hagi nodes que formin part dels dos camins soluci´o.
C`alcul de la fiabilitat d’un cam´ı
Sent F un vector 1..n que cont´e el valor de fiabilitat de cada node d’un cam´ı (probabilitat de que
no caigui), la fiabilitat d’aquest es calcula com:
f iabilitat cam´ı = Yn
i=1
Fi
Distribuci´o de c`arrega
Volem distribuir els N usuaris en els M servidors disponibles. Per fer-ho, s’ha de tenir en compte
el seg¨uent i en aquest mateix ordre de prioritats:
1. Equitativitat de c`arrega: La distribuci´o trobada ha de ser la m´es equitativa quan a c`arrega
dels servidors. Es a dir, la difer`encia d’activitat entre els usuaris dels diferents servidors ha ´
de ser m´ınima.
2. Proximitat servidor - usuari: Interessar`a distribuir als usuaris en els servidors m´es propers
segons localitzaci´o.
La m`axima prioritat ´es aconseguir equitativitat de c`arrega d’activitat. Tot i aix`o, davant la
possibilitat de trobar m´es d’una soluci´o amb una difer`encia d’equitativitat m´ınima, es vol definir
un valor X de toler`ancia.
Es a dir, si es trob´es una nova soluci´o tal que la difer`encia entre l’equitativitat de la millor ´
soluci´o trobada amb la nova fos inferior a aquesta toler`ancia, i la nova soluci´o presenta una millor
distribuci´o dels usuaris pel que fa a la localitzaci´o, aquesta passar`a a ser la millor. Aquesta
toler`ancia es definir`a per l’alumne.
4
C`alcul equitativitat de c`arrega
El c`alcul de l’equitativitat de c`arrega d’una soluci´o es pot obtenir com:
equitativitat = servidor amb m´es c`arrega − servidor amb menys c`arrega
4 Implementaci´o
L’implementaci´o de la soluci´o als diferents problemes s’ha de realitzar usant els algorismes vistos
durant el Bloc 2 de l’assignatura:
1. Backtracking
2. Branch & Bound
3. Greedy
4. Greedy + Backtracking
5. Greedy + Branch & Bound
L’objectiu ´es comparar les diferents t`ecniques segons:
1. Temps d’execuci´o: Difer`encia de temps entre els algorismes.
2. Nombre de nodes visitats: Per exemple, pel cas de Backtracking seria el nombre de crides
recursives fetes.
En el cas de Greedy respecte a la soluci´o trobada per Backtracking i/o Branch & Bound, ´es
interessant comparar tamb´e la qualitat de la soluci´o trobada i el temps d’execuci´o. Es a dir, veure ´
si la relaci´o qualitat de la soluci´o - temps amb Greedy pot ser m´es interessant que les altres dues
t`ecniques.
Per tal d’executar les diferents opcions del programa, es recomana fer ´us d’un men´u on l’usuari
decideixi quina opci´o executar. Es podria sin´o afegir la informaci´o que es cregues oportuna com
a argument del programa. Qualsevol dels dos casos es contemplar`a com a v`alid. En cas de
utilitzar arg
