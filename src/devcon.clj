(ns devcon
  (:require [clojure.java.io :as io]
            [clj-oauth2.ring :as oauth2-ring]))

(def file-name "config.properties")

(defn load-config
  []
  (with-open [^java.io.Reader reader (-> file-name io/resource io/reader)]
    (let [props (java.util.Properties.)]
      (.load props reader)
      (into {:scope ["public_api"]
             :grant-type "authorization_code"
             :get-state oauth2-ring/get-state-from-session
             :put-state oauth2-ring/put-state-in-session
             :get-target oauth2-ring/get-target-from-session
             :put-target oauth2-ring/put-target-in-session
             :get-oauth2-data oauth2-ring/get-oauth2-data-from-session
             :put-oauth2-data oauth2-ring/put-oauth2-data-in-session}
            (for [[k v] props] [(keyword k) (read-string v)])))))
