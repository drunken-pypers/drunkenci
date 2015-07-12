(ns drunkenci.routes.home
  (:require [drunkenci.layout :as layout]
            [drunkenci.repohandler :as repohandler]
            [drunkenci.buildrunner :as buildrunner]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]
            [ring.util.response :as r])
  (:use [clojure.java.shell :only [sh]]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))


(defn about-page []
  (layout/render "about.html"))


(defn build-repo
  [params]
  (let [
        repourl (params "repourl")
        repo-location (repohandler/clone-repo repourl)
        build-response (buildrunner/run-build repo-location)]
    (r/response build-response)))


(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/build-repo" {params :query-params} (build-repo params))
  (GET "/about" [] (about-page)))
