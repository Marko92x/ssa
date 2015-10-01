(ns mare.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.basic-authentication :refer :all]
            [ring.util.response :as resp]
            [mare.parser :as parser]
            [mare.model.oglasi :as oglasi-model]
            [mare.model.kompanije :as kompanije-model]
            [mare.model.hr :as hr-model]

            )
  )

(defroutes public-routes
           ;GLAVE RUTE
           (GET "/" [] (parser/index))
           (route/resources "/")
           (GET "/index" [] (parser/index))
           (route/resources "/")
           (GET "/oglasi" [] (parser/oglasi))
           (route/resources "/")
           (GET "/kompanije" [] (parser/kompanije))
           (route/resources "/")
           (GET "/hr" [] (parser/hr))
           (route/resources "/")
           (GET "/oglasi/dodajoglasi" [] (parser/dodajOglas))
           (route/resources "/")
           (GET "/kompanije/dodajkompaniju" [] (parser/dodajKompaniju))
           (route/resources "/")
           (GET "/hr/dodajhr" [] (parser/dodajHr))
           (route/resources "/")



      ;POST ZAHTEVI ZA INSERT
      (POST "/hr/dodaj" [& params]
      (do (hr-model/dodaj params)
          (resp/redirect "/hr")
          )
      )
           (POST "/oglasi/dodaj" [& params]
                 (do (oglasi-model/dodaj params)
                     (resp/redirect "/oglasi")))
           (POST "/kompanije/dodaj" [& params]
                 (do (kompanije-model/dodaj params)
                     (resp/redirect "/kompanije")
                     ))


           ;GET ZAHTEVI ZA BRISANJE
           (GET "/kompanije/:id/obrisi" [id]
                (do (kompanije-model/obrisi id)
                    (resp/redirect "/kompanije")))
           (GET "/oglasi/:id/obrisi" [id]
                   (do (oglasi-model/obrisi id)
                       (resp/redirect "/oglasi")))
           (GET "/hr/:id/obrisi" [id]
                   (do (hr-model/obrisi id)
                       (resp/redirect "/hr")))


           ;VODI NA STRANICU ZA UPDATE
           (GET "/hr/:id/izmeni" [id] (parser/updateHr id))
           (route/resources "/")
           (GET "/kompanije/:id/izmeni" [id] (parser/updateKompanije id))
           (route/resources "/")
           (GET "/oglasi/:id/izmeni" [id] (parser/updateOglasi id))
           (route/resources "/")


           ;POST ZAHTEVI ZA UPDATE
           (POST "/hr/:id/izmeni" [& params]
                 (do (hr-model/update (:id params) params)
                     (resp/redirect "/hr")))
           (POST "/kompanije/:id/izmeni" [& params]
                 (do (kompanije-model/update (:id params) params)
                     (resp/redirect "/kompanije")))
           (POST "/oglasi/:id/izmeni" [& params]
                 (do (oglasi-model/update (:id params) params)
                     (resp/redirect "/oglasi")))

          )

(defroutes app-routes
           public-routes
           (route/not-found "404 Not Found")
           )
(def app
  (handler/site app-routes)
  )
