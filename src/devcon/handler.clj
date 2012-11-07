(ns devcon.handler
  (:use compojure.core)
  (:require [devcon]
            [devcon.start :as start]
            [compojure.handler :as handler]
            [clj-oauth2.ring :as o2-ring]
            [clj-oauth2.client :as o2]
            [ring.middleware.session :as session]))

(defroutes app-routes
  (GET "/" request (start/start request)))

(def config (devcon/load-config))

(def app
  (-> app-routes
      session/wrap-session
      (o2-ring/wrap-oauth2 config)
      handler/site))