(defproject clojure-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.1"]
                 [tk.skuro/clj-oauth2 "0.5.1"]
                 [org.alfresco.cmis.client/alfresco-opencmis-extension "0.4"]
                 [org.springframework.social/spring-social-alfresco "0.2.7-RELEASE"]
                 [hiccup "1.0.1"]
                 [ring/ring-jetty-adapter "1.1.0"]]
  :plugins [[lein-ring "0.7.3"]]
  :ring {:handler devcon.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
