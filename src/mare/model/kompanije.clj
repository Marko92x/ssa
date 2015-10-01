(ns mare.model.kompanije (:refer-clojure :exclude [get])
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

(defn vratiKompanije []
  (jdbc/query mysql-db
              ["SELECT * FROM kompanija k"]))

(defn dodaj
  [params]
  (jdbc/insert! mysql-db :kompanija params))

(defn obrisi [id]
  (jdbc/delete! mysql-db :kompanija (sql/where {:id id})))

(defn get [id]
  (first (jdbc/query mysql-db
                     (sql/select * :kompanija (sql/where {:id id})))))

(defn update [id params]
  (jdbc/update! mysql-db :kompanija params (sql/where {:id id})))
