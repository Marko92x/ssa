(ns mare.model.oglasi (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]
            )
  )

(def mysql-db {
               :subprotocol "mysql"
               :subname "//localhost:3306/hashfon"
               :user "root"
               :password ""
               :zeroDateTimeBehaviour "convertToNull"
               })

(defn vratiOglase []
  (jdbc/query mysql-db
              ["SELECT k.ime, o.id, o.oglas, o.naziv FROM oglas_kompanije o JOIN kompanija k on (o.id_kompanije = k.id)"]))

(defn obrisi [id]
  (jdbc/delete! mysql-db :oglas_kompanije (sql/where {:id id})))


(defn dodaj
  [params]
  (jdbc/insert! mysql-db :oglas_kompanije params))

(defn get [id]
  (first (jdbc/query mysql-db
                     (sql/select * :oglas_kompanije (sql/where {:id id})))))

(defn update [id params]
  (jdbc/update! mysql-db :oglas_kompanije params (sql/where {:id id})))