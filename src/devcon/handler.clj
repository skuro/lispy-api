(ns devcon.handler
  (:use compojure.core)
  (:require [devcon]
            [devcon.start :as start]
            [compojure.handler :as handler]
            [clj-oauth2.ring :as o2-ring]
            [clj-oauth2.client :as o2]))

(defroutes app-routes
  (GET "/" [] start/start))

(def config (devcon/load-config))

(def app
  (-> app-routes
      (o2-ring/wrap-oauth2 config)
      handler/site))