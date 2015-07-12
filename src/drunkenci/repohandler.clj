(ns drunkenci.repohandler
  (:require [clj-jgit.porcelain :as git]
            [clojure.java.io :as io]
            [environ.core :refer [env]]))

(defn repo-name-form-url [repo-url]
  (if-let [match (re-find #"/([a-zA-Z\.\-_]+).git$" repo-url)]
          (nth match 1)
          (throw (Exception. (str "Cant get repo name for " repo-url)))))

(defn clone-repo [repo-url]
  (let [repo-location (io/file (env :workspace)
                               (repo-name-form-url repo-url))]
    (git/git-clone-full repo-url repo-location)
    (str repo-location)
    )
  )
