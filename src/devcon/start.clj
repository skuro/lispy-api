(ns devcon.start
  (:use devcon
        hiccup.core)
  (:require [clojure.pprint :as pp]))

(defn api-info [[network person session]]
  (html [:html
         [:head
          [:title "The Aplfresco Public API"]
          [:meta {:charset "UTF-8"}]
          [:meta {:http-equiv "Pragma" :content "no-cache"}]
          [:meta {:htto-equiv "Expires" :content "-1"}]
          [:style "section {padding: 10px;}"]]
         [:body
          [:img {:src "http://www.alfresco.com/sites/www/themes/alfrescodotcom/img/logo.svg"
                 :alt "Alfresco"
                 :width "241px"}]
          [:h1 "Some basic information retrieved using Alfresco Public API"]
          [:section
           "Person is: " (.getFirstName person) " " (.getLastName person)
           [:br]
           "Email: " (.getEmail person)]
          [:section "CMIS root folder is " (-> session
                                               (.getRootFolder)
                                               (.getPath))]
          [:section "Network is " (.getId network)]]]))

(defn start [request]
  (let [api (get-api (fn [] (:oauth2 request)))
        network (.getHomeNetwork api)
        person (.getCurrentUser api)
        session (.getCMISSession api (.getId network))]
    (api-info [network person session])))
