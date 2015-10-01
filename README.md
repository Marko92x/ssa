# Clojure web application for publishing companies advertisement

The primary idea is to make a common place where all advertisements from all participating companies can be seen and shared. 
In this way you can find all useful informations about selling and trading in this one website page.

The whole idea is based on Bootstrap and JQuery on front and Clojure code on backend.

All database communication is built with CRUD operations, using WAMP and MySql jdbc. 

Application is deployed on url - http://localhost:3000/


All design is done with HTML5 and CSS3, precisly with Javascript template called mustache which is supported by Clostache clojure library.


The following example represents actuall combination of HTML, Bootstrap and mustache on front and Clojure and clostache on backend.
With {{#hr}} scriplets, we bind data we get from server.


```html
<table class="table table-bordered table-hover">
    <tr>
        <th>Id kompanije</th>
        <th>Id</th>
        <th>Ime</th>
        <th>Email</th>
        <th>Edit</th>
        <th>Remove</th>
        {{#hr}}
            <tr class="success">
                <th>{{komp}}</th>
                <th>{{id}}</th>
                <th>{{ime}} {{prezime}}</th>
                <th>{{email}} </th>

                <td><a href="/hr/{{id}}/izmeni" class="btn btn-info">Izmeni</a></td>
                <td><a href="/hr/{{id}}/obrisi" class="btn btn-danger">Obrisi</a></td>
            </tr>
        {{/hr}}
    </table>
```



Next example shows extraction of database using complex query with JOIN keyword. This is how we get all data needed for setting table above.



```clojure
(defn vratiOglase []
  (jdbc/query mysql-db
              ["SELECT k.ime, o.id, o.oglas, o.naziv FROM oglas_kompanije o JOIN kompanija k on (o.id_kompanije = k.id)"]))
```



This web application uses Ring library that is responsible for building Jetty java server on top. Beside that, Compojure is responsible for routing http responses and requests in application. 



Tabela oglasi :
![Alt text](resources/public/img/2.png?raw=true "Tabela oglasi")

Dodavanja oglasa :
![Alt text](resources/public/img/1.png?raw=true "Dodavanja oglasa")

Update hr menadzera :
![Alt text](resources/public/img/3.png?raw=true "Update hr menadzera")



A Clojure project by Marko Barackov 2015, Belgrade

## Usage

Type following command in you cmd:

```
lein ring server
```

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
