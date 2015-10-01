(ns mare.model.hr
  (:refer-clojure :exclude [get])
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

(defn vratiHr []
  (jdbc/query mysql-db
              ["SELECT k.ime AS komp, h.id, h.ime, h.email FROM hr h JOIN kompanija k ON (h.id_kompanije = k.id)"]))
(defn dodaj
  [params]
  (jdbc/insert! mysql-db :hr params))


(defn obrisi [id]
  (jdbc/delete! mysql-db :hr (sql/where {:id id})))

(defn get [id]
  (first (jdbc/query mysql-db
                     (sql/select * :hr (sql/where {:id id})))))

(defn update [id params]
  (jdbc/update! mysql-db :hr params (sql/where {:id id})))

