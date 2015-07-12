(ns drunkenci.buildrunner
  (:use [clojure.java.shell :only [sh]]))


(defn clean-build
  [repo-location]
  (sh "rm" "-rf" repo-location)
  )


(defn run-build
  [repo-location]
  (let [
        build-response (sh "lein" "test" :dir repo-location)]
    (clean-build repo-location)
    build-response
    )
  )
