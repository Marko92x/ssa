(ns mare.parser
  (:require
    [clostache.parser :as clostache]
    [mare.model.kompanije :as kompanije-model]
    [mare.model.oglasi :as oglasi-model]
    [mare.model.hr :as hr-model]
    ))

(defn read-clostache [template-name]
  (slurp (clojure.java.io/resource
           (str "views/" template-name ".mustache"))))

(defn read-html [template-name]
  (slurp (clojure.java.io/resource
           (str "views/" template-name ".html"))))

(defn render-template-html [template-file params]
  (clostache/render (read-html template-file) params))

(defn render-template-clostache [template-file params]
  (clostache/render (read-clostache template-file) params))


(defn index []
  (render-template-html "index" {}))

(defn oglasi[]
  (render-template-clostache "oglasi" {:oglasi (oglasi-model/vratiOglase)
                                       :kompanije (kompanije-model/vratiKompanije)})
  )
(defn dodajOglas[]
  (render-template-clostache "dodajoglas" {:oglasi (oglasi-model/vratiOglase)
                                       :kompanije (kompanije-model/vratiKompanije)})
  )
(defn kompanije []
  (render-template-clostache "kompanije" {:kompanije (kompanije-model/vratiKompanije)

                                }))
(defn dodajKompaniju[]
  (render-template-clostache "dodajkompaniju" {:oglasi (oglasi-model/vratiOglase)
                                            :kompanije (kompanije-model/vratiKompanije)})

  )
(defn dodajHr []
  (render-template-clostache "dodajhr" {:hr (hr-model/vratiHr)
                                               :kompanije (kompanije-model/vratiKompanije)})
  )
(defn hr []
  (render-template-clostache "hr" {:hr (hr-model/vratiHr)
                                   :kompanije (kompanije-model/vratiKompanije)
                                          }))


(defn updateHr [id]
  (render-template-clostache "updatehr" {:hr (hr-model/get id)
                                         :kompanije (kompanije-model/vratiKompanije)
                                     }))
(defn updateKompanije [id]
  (render-template-clostache "updatekompanije" {
                                     :kompanije (kompanije-model/get id)
                                     }))
(defn updateOglasi [id]
  (render-template-clostache "updateoglasi" {:oglasi (oglasi-model/get id)
                                     :kompanije (kompanije-model/vratiKompanije)
                                     }))
